package com.example.contacts.components;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import com.example.contacts.R;
import com.example.contacts.models.Contact;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

public class ContactCard extends MaterialCardView {
    private Contact contact;
    ContactSelectedEventListener listener;
    String tag = "ContactCard";

    public interface ContactSelectedEventListener{
        public void onSelect();
    }

    public ContactCard(Context context, Contact contact, ContactSelectedEventListener listener){
        super(context);
        Log.d(tag, "ContactCard: constructed ---------------------");
        this.contact = contact;
        this.listener = listener;
        setTag(contact.id);
        createViews();
    }

    public int getContactId() {
        return contact.id;
    }

    private void createViews() {
        Log.d(tag, "createViews: constructed ---------------------");
        removeAllViews();
        LinearLayout contentsView = new LinearLayout(getContext());
        contentsView.setPadding(48,0,0,24);
        addView(contentsView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48,24,48,24);
        setLayoutParams(params);
        View image;

        if (!contact.pictureUri.equals("")){
            float density = getResources().getDisplayMetrics().density;
            AppCompatImageView imageView = new AppCompatImageView(getContext());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams((int) (40 * density), (int) (40 * density));
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageURI(Uri.parse(contact.pictureUri));

            CardView cardView = new CardView(getContext());
            cardView.setRadius(20 * density);
            cardView.addView(imageView);
            image = cardView;


        } else {
            Log.d(tag, "tried to add a circleDisplay ---------------------------------");
            image = new CircleDisplay(getContext(), String.valueOf(contact.name.charAt(0)));
        }

        contentsView.addView(image);

        MaterialTextView name = new MaterialTextView(getContext(), null, R.attr.textAppearanceHeadline6);
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameParams.setMargins(32,12,0,0);
        name.setLayoutParams(nameParams);
        name.setOnClickListener(view -> {
            listener.onSelect();
        });
        name.setText(contact.name);


        contentsView.addView(name);
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        createViews();
    }
}
