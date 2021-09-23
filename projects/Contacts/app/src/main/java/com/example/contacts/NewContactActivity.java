package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.contacts.components.LabelledInput;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.NewContactPresenter;

public class NewContactActivity extends BaseActivity implements NewContactPresenter.MVPView {
    NewContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NewContactPresenter(this);

        LabelledInput nameInput = new LabelledInput(this, "Name");
        LabelledInput numberInput = new LabelledInput(this, "Phone Number");
        LabelledInput emailInput = new LabelledInput(this, "Email");

        AppCompatButton button = new AppCompatButton(this);
        button.setText("Save");
        button.setOnClickListener(view -> {
            presenter.createContact(nameInput.getText().toString(), numberInput.getText().toString(), emailInput.getText().toString());
        });

        LinearLayout newContactLayout = new LinearLayout(this);
        newContactLayout.setOrientation(LinearLayout.VERTICAL);

        newContactLayout.addView(nameInput);
        newContactLayout.addView(numberInput);
        newContactLayout.addView(emailInput);

        newContactLayout.addView(button);

        setContentView(newContactLayout);
    }

    @Override
    public void goBackToContactsPage(Contact newContact) {
        Intent intent = new Intent();
        intent.putExtra("result", newContact);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
