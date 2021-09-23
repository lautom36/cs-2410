package com.example.contacts.components;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout{
    AppCompatTextView labelTxt;
    AppCompatEditText editText;
    public LabelledInput(Context context, String label){
        super(context);
        setOrientation(VERTICAL);

        labelTxt = new AppCompatTextView(context);

        labelTxt.setText(label);
        editText = new AppCompatEditText(context);

        addView(labelTxt);
        addView(editText);
    }

    public Editable getText() {
        return editText.getText();
    }

}
