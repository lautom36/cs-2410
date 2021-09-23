package com.example.contacts.presenters;

import android.util.Log;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

public class NewContactPresenter {
    MVPView view;
    AppDatabase database;
    public interface MVPView extends BaseMVPView{
        public void goBackToContactsPage(Contact contact);

    }

    public NewContactPresenter(MVPView view) {
        Log.d("NewContactPresenter", "NewContactPresenter: ------------------------------");
        this.view = view;
        database = view.getContextDatabase();
    }

    public void createContact(String name, String phoneNumber, String email){
        Log.d(" NewContactPresenter", "createContact: -----------------------");

        new Thread(() -> {
            Contact contact = new Contact();
            contact.name = name;
            contact.phoneNumber = phoneNumber;
            contact.email = email;
            int id = database.getContactDao().getDataCount();
            contact.id = id + 1;
            database.getContactDao().insert(contact);

            view.goBackToContactsPage(contact);
        }).start();

    }
}
