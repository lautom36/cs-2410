package com.example.contacts;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.contacts.components.ContactCard;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactPresenter;
import com.example.contacts.presenters.ContactsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// TODO: make contact page refresh when edited

public class MainActivity extends BaseActivity implements ContactsPresenter.MVPView {
    private final int CREATE_NEW_CONTACT = 1;
    private final int MODIFY_CONTACT = 2;
    public final static int DELETED_RESULT = 1;
    public final static int UPDATED_RESULT = 2;
    ContactPresenter contactPresenter;
    ContactsPresenter presenter;
    LinearLayout contactsLayout;
    String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactsPresenter(this);

        FrameLayout mainLayout = new FrameLayout(this);

        ScrollView scrollView = new ScrollView(this);
        contactsLayout = new LinearLayout(this);
        contactsLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(contactsLayout);
        mainLayout.addView(scrollView);

        FloatingActionButton createNewContactButton = new FloatingActionButton(this);
        createNewContactButton.setImageResource(R.drawable.ic_baseline_add_24);
        createNewContactButton.setOnClickListener(view -> {
            Log.d(tag, "OnCreate: new contact button onClick--------------------------------");
            presenter.goToNewContactsPage();
        });
        FrameLayout.LayoutParams fabParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fabParams.setMargins(0,0,48,48);
        fabParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        createNewContactButton.setLayoutParams(fabParams);
        mainLayout.addView(createNewContactButton);

        setContentView(mainLayout);
    }

    // draw a new contact
    @Override
    public void renderContact(Contact contact) {
        runOnUiThread(() -> {
                    ContactCard card = new ContactCard(this,
                    contact,
                    ()->{ presenter.handleContactSelected(contact); }
                    );
            contactsLayout.addView(card);
        });
    }

    // go to a contact activity
    @Override
    public void goToContactPage(int id) {
        Log.d(tag, "goToContactPage: here --------------");
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, MODIFY_CONTACT);
    }

    // go to the new contact activity
    @Override
    public void goToNewContactsPage() {
        Log.d(tag, "goToNewContactPage: here --------------");
        Intent intent = new Intent(this, CreateOrUpdateContactActivity.class);
        startActivityForResult(intent, CREATE_NEW_CONTACT);
    }

    // update from new contact
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(tag, "onActivityResult: start --------------");
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_NEW_CONTACT && resultCode == Activity.RESULT_OK) {
            Contact newContact = (Contact) data.getSerializableExtra("result");
            presenter.onNewContactCreated(newContact);
        }
        if (requestCode == MODIFY_CONTACT && resultCode == DELETED_RESULT){
            Log.d(tag, "onActivityResult: delete ---------------------");
            Contact contact = (Contact) data.getSerializableExtra("result");;
            presenter.handelContactDeleted(contact.id);
        }
        if (requestCode == MODIFY_CONTACT && resultCode == UPDATED_RESULT){
            Log.d(tag, "onActivityResult: update ---------------------");
            Contact contact = (Contact) data.getSerializableExtra("result");;
            presenter.handelContactUpdated(contact);
        }
    }

    @Override
    public void removeContactView(int id) {
        Log.d(tag, "removeContactView: start ---------------------");
        View view = contactsLayout.findViewWithTag(id);
        contactsLayout.removeView(view);
    }

    @Override
    public void updateContactView(Contact contact) {
        Log.d(tag, "updateContactView: start --------------");
        ContactCard view = contactsLayout.findViewWithTag(contact.id);
        view.setContact(contact);

    }
}