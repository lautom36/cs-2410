package com.example.contacts.components;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.example.contacts.models.Contact;

public class ClickableLabel extends LinearLayout {

    public ClickableLabel(Context context, Contact contact) {
        super(context);
        AppCompatButton clickableLable = new AppCompatButton(context);
        clickableLable.setText(contact.name);


    }
}
