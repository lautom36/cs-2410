package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactPresenter;

public class ContactActivity extends BaseActivity implements ContactPresenter.MVPView {
    ContactPresenter presenter;
    LinearLayout mainLayout;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactPresenter(this);
        int id = getIntent().getIntExtra("id", 1);

        Log.d("TAG", "onCreate: here ---------------------------");
        Log.d("TAG", "id:" + id);

        mainLayout = new LinearLayout(this);
        presenter.renderContact(id);
        setContentView(mainLayout);
    }

    @Override
    public void renderContact(Contact contact) {

        Log.d("ContactActivity", "renderContact: here ----------------------------");

        AppCompatTextView nameView = new AppCompatTextView(this);
        nameView.setText(contact.name);
        nameView.setGravity(Gravity.CENTER);
        nameView.setTextSize(36);

        AppCompatTextView phoneNumberView = new AppCompatTextView(this);
        phoneNumberView.setText("Call: " + contact.phoneNumber);
        phoneNumberView.setTextSize(24);

        AppCompatTextView emailView = new AppCompatTextView(this);
        emailView.setText("Email: " + contact.email);
        emailView.setTextSize(24);

        LinearLayout contactLayout = new LinearLayout(this);
        contactLayout.setOrientation(LinearLayout.VERTICAL);

        contactLayout.addView(nameView);
        contactLayout.addView(phoneNumberView);
        contactLayout.addView(emailView);

        mainLayout.addView(contactLayout);
    }

    @Override
    public void goToContactPage(int id) {

    }

    @Override
    public void goBackToContactsPage(Contact contact) {
        Intent intent = new Intent();
        intent.putExtra("result", contact);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
