package com.example.contacts.presenters;

import android.util.Log;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

public class ContactPresenter {
    MVPView view;
    private Contact contact;
    AppDatabase database;

    public interface MVPView extends BaseMVPView{
        public void renderContact(Contact contact);
        public void goToContactPage(int id);
        public void goBackToContactsPage(Contact contact);
    }

    public ContactPresenter (MVPView view) {
        Log.d("TAG", "ContactPresenter: here ---------------------");
        this.view = view;
        database = view.getContextDatabase();
//        new Thread(() -> {
//            contact = database.getContactDao().getContactFromId(id);
//            view.renderContact(contact);
//        }).start();
    }

    public void goToContactPage(int id){
        Log.d("TAG", "goToContactPage: here -----------------");
        view.goToContactPage(id);
    }

    public void renderContact(int id) {
        new Thread(() -> {
            Contact contact = database.getContactDao().getContactFromId(id);
            view.renderContact(contact);
        }).start();
    }


}
