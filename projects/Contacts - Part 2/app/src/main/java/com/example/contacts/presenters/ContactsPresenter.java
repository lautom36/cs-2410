package com.example.contacts.presenters;

import android.util.Log;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import java.util.ArrayList;

public class ContactsPresenter {
    private MVPView view;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private AppDatabase database;
    String tag = "ContactsPresenter";

    public interface MVPView extends BaseMVPView{
        void renderContact(Contact contact);
        void goToNewContactsPage();
        void goToContactPage(int id);
        void removeContactView(int id);
        void updateContactView(Contact contact);
    }

    public ContactsPresenter(MVPView view) {
        Log.d(tag, "ContactsPresenter: start ---------------------");
        this.view = view;
        database = view.getContextDatabase();
        refreshContacts();
    }

    public void goToNewContactsPage() {
        Log.d(tag, "goToNewContactsPage: start ---------------------");
        view.goToNewContactsPage();
    }

    public void refreshContacts() {
        Log.d(tag, "refreshContacts: start ---------------------");
        new Thread(() -> {
            contacts = (ArrayList<Contact>) database.getContactDao().getAllContacts();
            renderContacts();
        }).start();
    }

    public void onNewContactCreated(Contact contact){
        Log.d(tag, "onNewContactCreated: start ---------------------");
        contacts.add(contact);
        view.renderContact(contact);
    }

    private void renderContacts() {
        Log.d(tag, "renderContacts: start ---------------------");
        contacts.forEach(contact -> {
            view.renderContact(contact);
        });
    }

    public void goToContactPage(int id) {
        Log.d(tag, "goToContactPage: start ---------------------");
        view.goToContactPage(id);
    }

    public void handleContactSelected(Contact contact) {
        Log.d(tag, "handleContactSelected: start ---------------------");
        view.goToContactPage(contact.id);
    }

    public void handelContactDeleted(int id){
        Log.d(tag, "handleContactDeleted: start ---------------------");
        contacts.removeIf(contact -> {
            return contact.id == id;
        });
        view.removeContactView(id);
    }

    public void handelContactUpdated(Contact contact) {
        Log.d(tag, "handleContactUpdated: start ---------------------");
        for (int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).id == contact.id){
                contacts.set(i, contact);
            }
        }
        view.updateContactView(contact);
    }
}
