package com.example.contacts.presenters;

import android.util.Log;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

public class CreateOrUpdateContactPresenter {
    String tag = "----- CreateOrUpdateContactPresenter.java";
    MVPView view;
    AppDatabase database;
    Contact contact;
    public static final int DEFAULT_ID = -1;

    public interface MVPView extends BaseMVPView{
        void goBackToPreviousPage(Contact contact);
        void goToPhotos();
        void displayImage(String uri);
        void renderContactForm(Contact contact);
        void displayNameError();
        void displayPhoneNumberError();
        void displayEmailError();
        void takePicture();
    }

    public CreateOrUpdateContactPresenter(MVPView view) {
        Log.d(tag, "CreateOrUpdateContactPresenter: Start ------------------------------");
        this.view = view;
        database = view.getContextDatabase();
    }

    public void saveContact(String name, String phoneNumber, String email, String uri){
        Log.d(tag, "saveContact: Start -----------------------");

        new Thread(() -> {
            // check for errors in input
            if (name.equals("") || phoneNumber.equals("") || (!email.equals("") && !email.contains("@"))){
                if(name.equals("")) { view.displayNameError(); }
                if (phoneNumber.equals("")) { view.displayPhoneNumberError(); }
                if ((!email.equals("") && !email.contains("@"))) { view.displayEmailError(); }
                return;
            }

            Contact contactToSave;

            // update existing
            if (contact != null){
                contactToSave = contact;
            }
            // save new contact
            else{
                contactToSave = new Contact();
            }

            contactToSave.name = name;
            contactToSave.phoneNumber = phoneNumber;
            contactToSave.email = email;
            contactToSave.pictureUri = uri;

            // update contact
            if (contact != null) {
                database.getContactDao().update(contactToSave);
            }

            // save new contact
            else {
                int id = database.getContactDao().getDataCount();
                contactToSave.id = id + 1;
                database.getContactDao().insert(contactToSave);
            }

            view.goBackToPreviousPage(contactToSave);
        }).start();

    }

    public void loadContact(int id) {
        Log.d(tag, "loadContact: Start -----------------------");
        if (id != DEFAULT_ID) {
            new Thread(() ->{
                contact = database.getContactDao().getContactFromId(id);
                view.renderContactForm(contact);
            }).start();
        }
    }

    public void handleTakePicturePress(){
        Log.d(tag, "handleTakePicturePress: Start -----------------------");
        view.takePicture();
    }

    public void handlePictureSelected(String uri) {
        Log.d(tag, "handlePictureSelected: Start -----------------------");
        view.displayImage(uri);
    }

    public void handleCancelPress() {
        Log.d(tag, "handleCancelPress: Start -----------------------");
        view.goBackToPreviousPage(null);
    }

    public void handleSelectPicturePress() {
        Log.d(tag, "handleSelectPicturePress: Start -----------------------");
        view.goToPhotos();
    }
}
