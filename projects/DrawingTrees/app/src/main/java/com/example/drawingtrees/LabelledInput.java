package com.example.drawingtrees;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout {
    private AppCompatEditText input;
    public LabelledInput(Context context, String labelText) {
        super(context);
        AppCompatTextView label = new AppCompatTextView(context);
        label.setTextSize(20);
        label.setText(labelText);
        this.input = new AppCompatEditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.weight = 1;
        input.setLayoutParams(params);

        AppCompatTextView label2 = new AppCompatTextView(context);
        label2.setText(labelText);
        label2.setTextSize(18);
        addView(label);
        addView(input);
//        addView(label2);
//        label2.setGravity(Gravity.RIGHT);
//        label2.setBackgroundColor(Color.GREEN);
    }

    public Editable getText() {
        return input.getText();
    }
}
