package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.contacts.components.ImageSelector;
import com.example.contacts.components.MaterialInput;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.CreateOrUpdateContactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrUpdateContactActivity extends BaseActivity implements CreateOrUpdateContactPresenter.MVPView {
    CreateOrUpdateContactPresenter presenter;
    ImageSelector imageSelector;
    MaterialInput nameInput;
    MaterialInput phoneNumberInput;
    MaterialInput emailInput;
    String currentPhotoPath = "";
    private static int TAKE_PICTURE = 2;
    private static int PICK_IMAGE = 1;
    String tag = "CreateOrUpdateContactActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"onCreate: Start -------------------------------------");

        presenter = new CreateOrUpdateContactPresenter(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        presenter.loadContact(id);

        imageSelector = new ImageSelector(this, () -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Choose image")
                    .setItems(new CharSequence[]{"From Camera", "From Photos"}, (view, i) -> {
                        if (i == 0) {
                            presenter.handleTakePicturePress();
                        } else {
                            presenter.handleSelectPicturePress();
                        }
                    })
                    .show();
        });

        nameInput = new MaterialInput(this, "Name");
        phoneNumberInput = new MaterialInput(this, "Phone Number");
        emailInput = new MaterialInput(this, "Email");


        // save button
        MaterialButton saveButton = new MaterialButton(this, null, R.attr.materialButtonStyle);
        saveButton.setText("Save");
        saveButton.setOnClickListener(view -> {
            presenter.saveContact(
                    nameInput.getText().toString(),
                    phoneNumberInput.getText().toString(),
                    emailInput.getText().toString(),
                    imageSelector.getImageUri()
            );
        });

        // cancel button
        MaterialButton cancelButton = new MaterialButton(this, null, R.attr.borderlessButtonStyle);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener(view -> {
            presenter.handleCancelPress();
        });

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setPadding(48,0,48,0);
        buttonLayout.setGravity(Gravity.END | Gravity.BOTTOM);
        buttonLayout.addView(cancelButton);
        buttonLayout.addView(saveButton);

        LinearLayout newContactLayout = new LinearLayout(this);
        newContactLayout.setOrientation(LinearLayout.VERTICAL);

        newContactLayout.addView(imageSelector);
        newContactLayout.addView(nameInput);
        newContactLayout.addView(phoneNumberInput);
        newContactLayout.addView(emailInput);
        newContactLayout.addView(buttonLayout);

        setContentView(newContactLayout);
    }

    @Override
    public void goBackToPreviousPage(Contact newContact) {
        Log.d(tag,"goBackToContactsPage: Start -------------------------------------");
        if (newContact == null){
            setResult(Activity.RESULT_CANCELED, null);
        }else{
            Intent intent = new Intent();
            intent.putExtra("result", newContact);
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public void goToPhotos() {
        Log.d(tag, "goToPhotos: Start -----------------------");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void displayImage(String uri) {
        Log.d(tag, "displayImage: Start -----------------------");
        imageSelector.setImageUri(uri);
    }

    @Override
    public void renderContactForm(Contact contact) {
        Log.d(tag, "renderContactForm: Start -----------------------");
        runOnUiThread(() ->{
            imageSelector.setImageUri(contact.pictureUri);
            nameInput.setText(contact.name);
            phoneNumberInput.setText(contact.phoneNumber);
            emailInput.setText(contact.email);
        });

    }

    @Override
    public void displayNameError() {
        Log.d(tag, "displayNameError: Start -----------------------");
        runOnUiThread(() -> {
            Snackbar.make(nameInput, "Name cannot be blank", Snackbar.LENGTH_SHORT).show();
            nameInput.setErrorEnabled(true);
            nameInput.setError("Name cannot be blank");
        });
    }

    @Override
    public void displayPhoneNumberError() {
        Log.d(tag, "displayPhoneNumberError: Start -----------------------");
        runOnUiThread(() -> {
            Snackbar.make(nameInput, "Phone number cannot be blank", Snackbar.LENGTH_SHORT).show();
            phoneNumberInput.setErrorEnabled(true);
            phoneNumberInput.setError("Phone number cannot be blank");
        });
    }

    @Override
    public void displayEmailError() {
        Log.d(tag, "displayEmailError: Start -----------------------");
        runOnUiThread(() -> {
            Snackbar.make(nameInput, "Not a valid email", Snackbar.LENGTH_SHORT).show();
            emailInput.setErrorEnabled(true);
            emailInput.setError("Not a valid email");
        });
    }

    @Override
    public void takePicture() {
        Log.d(tag, "takePicture: Start -----------------------");

        // get unique file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";

        // create file
        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageFileName);
        currentPhotoPath = imageFile.getAbsolutePath();

        // get location to sent to camera
        Uri photoUri = FileProvider.getUriForFile(this, "com.example.contacts.fileprovider", imageFile);

        // sent location of file to camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, TAKE_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(tag, "onActivityResult: Start -----------------------");
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){
            String uri = data.getData().toString();
            presenter.handlePictureSelected(uri);
        }
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            presenter.handlePictureSelected(currentPhotoPath);
        }

    }
}
