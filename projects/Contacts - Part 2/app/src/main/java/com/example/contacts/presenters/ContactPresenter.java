package com.example.contacts.presenters;

import android.util.Log;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

public class ContactPresenter {
    String tag = "----- ContactPresenter";
    MVPView view;
    AppDatabase database;
    private Contact contact;
    boolean didUpdate = false;

    public interface MVPView extends BaseMVPView{
        void renderContact(Contact contact);
        void goToContactPage(int id);
        void goBackToContactsPage(Contact contact, boolean didDelete, boolean didUpdate);
        void goToEditContactPage(Contact contact);
        void displayDeleteConfirmation();
        void makePhoneCall(String number);
        void sendText(String number);
        void sendEmail(String address);
    }

    public ContactPresenter (MVPView view) {
        Log.d(tag, "ContactPresenter: start ---------------------");
        this.view = view;
        database = view.getContextDatabase();
    }

    public void goToContactPage(int id){
        Log.d(tag, "goToContactPage: start -----------------");
        view.goToContactPage(id);
    }

    public void renderContact(int id) {
        Log.d(tag, "renderContact: start ---------------------------");
        new Thread(() -> {
            contact = database.getContactDao().getContactFromId(id);
            view.renderContact(contact);
        }).start();
    }

    public void deleteContact(){
        Log.d(tag, "deleteContact: start ---------------------------");
        new Thread(() -> {
            database.getContactDao().delete(contact);
            view.goBackToContactsPage(contact,true, didUpdate);
        }).start();
    }

    public void handleEditClick() {
        Log.d(tag, "handleEditClick: start ---------------------------");
        view.goToEditContactPage(contact);
    }

    public void handleContactUpdated(Contact contact) {
        Log.d(tag, "handleContactUpdated: start ---------------------------");
        didUpdate = true;
        this.contact = contact;
        view.renderContact(contact);
    }

    public void handleBackPressed() {
        Log.d(tag, "handleBackPressed: start ---------------------------");
        view.goBackToContactsPage(contact, false, didUpdate);
    }

    public void handleDeletePressed() {
        Log.d(tag, "handleDeletePressed: start ---------------------------");
        view.displayDeleteConfirmation();
    }

    public void handleCallPressed(String phoneNumber) {
        Log.d(tag, "handleDeletePressed: start ---------------------------");
        view.makePhoneCall(phoneNumber);
    }

    public void handleTextPressed(String phoneNumber) {
        Log.d(tag, "handleTextPressed: start ---------------------------");
        view.sendText(phoneNumber);
    }

    public void handleEmailPressed(String email) {
        Log.d(tag, "handleEmailPressed: start ---------------------------");
        view.sendEmail(email);
    }


}
