package com.example.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.contacts.components.ContactsView;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactPresenter;
import com.example.contacts.presenters.ContactsPresenter;

import java.util.ArrayList;
// have an activity with a button at the top to add a new contact and have contacts displayed there
// when you press the button open new activity that asks for a name, phone number, and email. have a button to save that takes you back to home page
// if a contact is clicked, open a new activity that displays the contact data

public class MainActivity extends BaseActivity implements ContactsPresenter.MVPView {
    private final int CREATE_NEW_CONTACT = 1;
    ContactPresenter contactPresenter;
    ContactsPresenter presenter;
    LinearLayout mainLayout;
    LinearLayout contactsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactsPresenter(this);

        mainLayout = new LinearLayout(this);
        contactsLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);

        mainLayout.setOrientation(LinearLayout.VERTICAL);
        contactsLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatButton createNewContactButton = new AppCompatButton(this);
        createNewContactButton.setText("+ Create New Contact");
        createNewContactButton.setOnClickListener(view -> {
            Log.d("MainActivity", "OnCreate: new contact button onClick--------------------------------");
            presenter.goToNewContactsPage();
        });

        mainLayout.addView(createNewContactButton);
        mainLayout.addView(contactsLayout);
        scrollView.addView(mainLayout);


        setContentView(scrollView);
    }

    // draw a new contact
    @Override
    public void renderContact(Contact contact) {
        runOnUiThread(() -> {
                ContactsView  contactsView = new ContactsView(this, contact, presenter);
                contactsLayout.addView(contactsView);
        });
    }

    // go to a contact activity
    @Override
    public void goToContactPage(int id) {
        Log.d(" MainActivity", "goToContactPage: here --------------");
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, id);
    }

    // go to the new contact activity
    @Override
    public void goToNewContactsPage() {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivityForResult(intent, CREATE_NEW_CONTACT);
    }

    // update from new contact
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_NEW_CONTACT && resultCode == Activity.RESULT_OK) {
            Contact newContact = (Contact) data.getSerializableExtra("result");
            presenter.onNewContactCreated(newContact);
        }
    }
}