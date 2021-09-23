package com.example.contacts.components;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.contacts.R;
import com.example.contacts.models.Contact;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class PhoneNumberCard extends MaterialCardView {
    private Contact contact;
    OnClickListener callOnclick;
    OnClickListener textOnclick;
    String tag = "PhoneNumberCard";

    public PhoneNumberCard(Context context, Contact contact, OnClickListener callOnclick, OnClickListener textOnclick){
        super(context);
        Log.d(tag, "PhoneNumberCard: constructed ----------");
        this.contact = contact;
        this.callOnclick = callOnclick;
        this.textOnclick = textOnclick;
        setTag(contact.id);
        createViews();
    }

    private void createViews() {
        Log.d(tag, "createViews: started ----------");
        removeAllViews();
        LinearLayout contentsView = new LinearLayout(getContext());
        contentsView.setPadding(0,24,0,24);
        addView(contentsView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(48,24,48,24);
        setLayoutParams(params);

        // call button
        FloatingActionButton callButton = new FloatingActionButton(getContext());
        callButton.setImageResource(R.drawable.ic_baseline_call_24);
        callButton.setBackgroundColor(Color.WHITE);
        FrameLayout.LayoutParams callButtonParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        callButtonParams.setMargins(48,24,24,24);
        callButtonParams.gravity = (Gravity.LEFT);
        callButton.setLayoutParams(callButtonParams);
        callButton.setOnClickListener(callOnclick);
        contentsView.addView(callButton);

        // phone number
        MaterialTextView phoneNumberView = new MaterialTextView(getContext());
        phoneNumberView.setText(contact.phoneNumber);
        phoneNumberView.setTextSize(24);
        LinearLayout.LayoutParams numberParams = new LinearLayout.LayoutParams(550, ViewGroup.LayoutParams.WRAP_CONTENT);
        numberParams.setMargins(32,48,0,0);
        phoneNumberView.setLayoutParams(numberParams);
        contentsView.addView(phoneNumberView);

        // text button
        FloatingActionButton textButton = new FloatingActionButton(getContext());
        textButton.setImageResource(R.drawable.ic_baseline_message_24);
        FrameLayout.LayoutParams textButtonParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textButtonParams.setMargins(0,24,24,0);
        textButtonParams.gravity = (Gravity.RIGHT);
        textButton.setLayoutParams(textButtonParams);
        textButton.setOnClickListener(textOnclick);
        contentsView.addView(textButton);


    }
}
