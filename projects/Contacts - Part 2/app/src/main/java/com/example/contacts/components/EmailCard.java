package com.example.contacts.components;

import android.content.Context;
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

public class EmailCard extends MaterialCardView {
    private Contact contact;
    OnClickListener emailOnclick;
    String tag = "EmailCard";

    public EmailCard(Context context, Contact contact, OnClickListener onClickListener) {
        super(context);
        Log.d(tag, "EmailCard: constructed ----------");
        this.contact = contact;
        this.emailOnclick = onClickListener;
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

        // email button
        FloatingActionButton callButton = new FloatingActionButton(getContext());
        callButton.setImageResource(R.drawable.ic_baseline_email_24);
        FrameLayout.LayoutParams callButtonParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        callButtonParams.setMargins(48,24,24,24);
        callButtonParams.gravity = (Gravity.LEFT);
        callButton.setLayoutParams(callButtonParams);
        callButton.setOnClickListener(emailOnclick);
        contentsView.addView(callButton);

        // email
        MaterialTextView phoneNumberView = new MaterialTextView(getContext());
        phoneNumberView.setText(contact.email);
        phoneNumberView.setTextSize(24);
        LinearLayout.LayoutParams numberParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        numberParams.setMargins(32,48,0,0);
        phoneNumberView.setLayoutParams(numberParams);
        contentsView.addView(phoneNumberView);
    }
}
