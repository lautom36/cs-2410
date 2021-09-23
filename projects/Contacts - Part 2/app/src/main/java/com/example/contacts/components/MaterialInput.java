package com.example.contacts.components;

import android.content.Context;
import android.text.Editable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MaterialInput extends TextInputLayout {
    private TextInputEditText editText;

    public MaterialInput(Context context, String hint){
        this(context, hint, false);
    }

    public MaterialInput(Context context, String hint, boolean isMultiLine){
        super(context);
        setHint(hint);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        editText = new TextInputEditText(getContext());
        if (isMultiLine){
            editText.setLines(4);
            editText.setGravity(Gravity.START);
        } else {
            editText.setSingleLine(true);
        }
        addView(editText);
    }

    public Editable getText() {
        return editText.getText();
    }

    public void setText(String text){
        editText.setText(text);
    }
}
