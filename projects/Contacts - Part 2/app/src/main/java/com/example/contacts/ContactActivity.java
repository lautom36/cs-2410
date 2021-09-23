package com.example.contacts;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;

import com.example.contacts.components.CircleDisplay;
import com.example.contacts.components.DefaultPictureDisplay;
import com.example.contacts.components.EmailCard;
import com.example.contacts.components.PhoneNumberCard;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class ContactActivity extends BaseActivity implements ContactPresenter.MVPView {
    String tag = "----- ContactActivity";
    ContactPresenter presenter;
    LinearLayout mainLayout;
    private Contact contact;
    int id;
    private final int UPDATE_POST = 1;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private final int REQUEST_SMS_PERMISSIONS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(tag, "onCreate: start ---------------------------");
        super.onCreate(savedInstanceState);
        presenter = new ContactPresenter(this);
        int id = getIntent().getIntExtra("id", 1);
        this.id = id;
        Log.d(tag, "id:" + id);
        mainLayout = new LinearLayout(this);
        presenter.renderContact(id);
        setContentView(mainLayout);
    }

    @Override
    public void renderContact(Contact contact) {
        Log.d(tag , "renderContact: start ----------------------------");

        runOnUiThread(()->{
            FrameLayout frameLayout = new FrameLayout(this);
            LinearLayout.LayoutParams frameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(frameParams);

            // wraps sub layers
            LinearLayout wrapper = new LinearLayout(this);
            wrapper.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            wrapper.setLayoutParams(wrapperParams);
            frameLayout.addView(wrapper);

            // image view

            // if there is a assigned pic
            if(!contact.pictureUri.equals("")){
                AppCompatImageView image = new AppCompatImageView(this);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1060);
                image.setLayoutParams(imageParams);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                image.setImageURI(Uri.parse(contact.pictureUri));
                wrapper.addView(image);
            }
            else{
                DefaultPictureDisplay image = new DefaultPictureDisplay(this, String.valueOf(contact.name.charAt(0)));
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1060);
                image.setLayoutParams(imageParams);

                wrapper.addView(image);
            }

            MaterialTextView nameView = new MaterialTextView(this, null, R.attr.textAppearanceHeadline6);
            FrameLayout.LayoutParams nameParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            nameParams.gravity = (Gravity.LEFT | Gravity.CENTER);
            nameParams.setMargins(24,180,0,0);
            nameView.setLayoutParams(nameParams);
            nameView.setText(contact.name);
            Log.d(tag, contact.name);
            nameView.setTextSize(36);
            nameView.setTextColor(getResources().getColor(android.R.color.white, null));

            frameLayout.addView(nameView);

            // add the phone number card
            PhoneNumberCard phoneNumberCard = new PhoneNumberCard(
                    this,
                    contact,
                    (view) -> { presenter.handleCallPressed(contact.phoneNumber); },
                    (view) -> { presenter.handleTextPressed(contact.phoneNumber); });
            wrapper.addView(phoneNumberCard);

            // if there is an email, add the email card
            if (!contact.email.equals("")){
                EmailCard emailCard = new EmailCard(
                        this,
                        contact,
                        (view) -> { presenter.handleEmailPressed(contact.email); });
                wrapper.addView(emailCard);
            }

            // more button
            FloatingActionButton more = new FloatingActionButton(this);
            FrameLayout.LayoutParams moreParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            moreParams.setMargins(0,24,24,0);
            moreParams.gravity = (Gravity.RIGHT | Gravity.TOP);
            more.setImageResource(R.drawable.ic_baseline_more_vert_24);
            more.setLayoutParams(moreParams);
            frameLayout.addView(more);

            PopupMenu popupMenu = new PopupMenu(this, more);
            popupMenu.getMenu().add("Edit");
            popupMenu.getMenu().add("Delete");

            more.setOnClickListener(view -> {
                popupMenu.show();
            });
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getTitle().toString().equals("Edit")){
                        presenter.handleEditClick();
                    } else {
                        presenter.handleDeletePressed();
                    }
                    return false;
                }
            });

            mainLayout.addView(frameLayout);
        });
    }

    @Override
    public void goToContactPage(int id) {
        Log.d(tag, "goToContactPage: start ---------------------------");

    }

    @Override
    public void goBackToContactsPage(Contact contact, boolean didDelete, boolean didUpdate) {
        Log.d(tag, "goBackToContactsPage: start ---------------------------");
        Intent intent = new Intent();
        if(didDelete){
            Log.d(tag, "goBackToContactsPage: didDelete ---------------------------");
            intent.putExtra("result", contact);
            setResult(MainActivity.DELETED_RESULT, intent);
        } else if (didUpdate){
            Log.d(tag, "goBackToContactsPage: didUpdate ---------------------------");
            intent.putExtra("result", contact);
            setResult(MainActivity.UPDATED_RESULT, intent);
        } else {
            Log.d(tag, "goBackToContactsPage: canceled ---------------------------");
            intent.putExtra("result", contact);
            setResult(Activity.RESULT_CANCELED, null);
        }
        finish();
    }

    @Override
    public void goToEditContactPage(Contact contact) {
        Log.d(tag, "goToEditContactPage: start ---------------------------");
        Intent intent = new Intent(this, CreateOrUpdateContactActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, UPDATE_POST);
    }

    @Override
    public void displayDeleteConfirmation() {
        Log.d(tag, "displayDeleteConfirmation: start ---------------------------");
        new MaterialAlertDialogBuilder(this)
                .setTitle("Are you sure you want to delete contact?")
                .setPositiveButton("Delete", (view, i) -> {
                    presenter.deleteContact();
                })
                .setNeutralButton("Cancel", (view, i) -> {
                    view.dismiss();
                })
                .show();

    }

    @Override
    public void makePhoneCall(String number) {
        Log.d(tag, "makePhoneCall: start ---------------------------");

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_PERMISSIONS);
        }

    }

    @Override
    public void sendText(String number) {
        Log.d(tag, "sendText: start ---------------------------");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
            Intent textIntent = new Intent(Intent.ACTION_VIEW);
            textIntent.setData(Uri.parse("sms:" + number));
            startActivity(textIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSIONS);
        }
    }

    @Override
    public void sendEmail(String address) {
        Log.d(tag, "sendEmail: start ---------------------------");
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + address));
        startActivity(emailIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                presenter.handleCallPressed(contact.phoneNumber);
            }
        }
        if (requestCode == REQUEST_SMS_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                presenter.handleTextPressed(contact.phoneNumber);
            }
        }
    }

    // TODO: update is not working, sends the right data, but right data is not rendered
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(tag, "onActivityResult: start ---------------------------");
        if (requestCode == UPDATE_POST && resultCode == Activity.RESULT_OK){
            Contact contact = (Contact) data.getSerializableExtra("result");
//            Log.d(tag, "name: "+ contact.name +" ---------------------------");
            presenter.handleContactUpdated(contact);
        }
    }

    @Override
    public void onBackPressed() {
        presenter.handleBackPressed();
    }
}
