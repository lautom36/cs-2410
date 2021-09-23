package com.example.contacts.components;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactPresenter;
import com.example.contacts.presenters.ContactsPresenter;

public class ContactsView extends LinearLayout {
    private String name;
    private String phoneNumber;
    private String email;
    private int id;

    public ContactsView(Context context, Contact contact, ContactsPresenter presenter) {
        super(context);

        Log.d(" ContactsView", "ContactsView: here ---------------------------------");

        AppCompatTextView name = new AppCompatTextView(context);

        name.setText(contact.name);
        name.setTextSize(24);
        name.setOnClickListener(view -> {
            Log.d(" ContactsView", "ContactsView: onClick ---------------------------------");
            presenter.goToContactPage(contact.id);

        });

        addView(name);

        ClickableLabel label = new ClickableLabel(context, contact);
        addView(label);


    }

    @Override
    public int getId() {
        return id;
    }
}
