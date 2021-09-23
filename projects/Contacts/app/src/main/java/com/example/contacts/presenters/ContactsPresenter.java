package com.example.contacts.presenters;

import android.util.Log;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import java.util.ArrayList;

public class ContactsPresenter {
    private MVPView view;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private AppDatabase database;

    public void goToContactPage(int id) {
        Log.d(" ContactsPresenter", "goToContactPage: here ---------------------");
        view.goToContactPage(id);
    }

    public interface MVPView extends BaseMVPView{
        public void renderContact(Contact contact);
        public void goToNewContactsPage();
        public void goToContactPage(int id);
    }
    public ContactsPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        refreshContacts();
    }

    public void goToNewContactsPage() {
        view.goToNewContactsPage();
    }

//    public void goToContactPage(int id) {view.goToContactPage(id);}

    public void refreshContacts() {
        new Thread(() -> {
            contacts = (ArrayList<Contact>) database.getContactDao().getAllContacts();
            renderContacts();
        }).start();
    }

    public void onNewContactCreated(Contact contact){
        contacts.add(contact);
        view.renderContact(contact);
    }

    private void renderContacts() {
        contacts.forEach(contact -> {
            view.renderContact(contact);
        });
    }
}
