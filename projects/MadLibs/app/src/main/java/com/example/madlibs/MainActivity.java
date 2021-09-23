package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // layout parameters
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // text1 - name
        final AppCompatTextView text1 = new AppCompatTextView(this);
        text1.setText("Enter a name");

        // text2 - adjective
        final AppCompatTextView text2 = new AppCompatTextView(this);
        text2.setText("Enter an adjective");

        // text3 - place
        final AppCompatTextView text3 = new AppCompatTextView(this);
        text3.setText("Enter a place");

        // text4 - place
        final AppCompatTextView text4 = new AppCompatTextView(this);
        text4.setText("Enter a place");

        // text5 - activity
        final AppCompatTextView text5 = new AppCompatTextView(this);
        text5.setText("Enter an activity");

        // editText1
        final AppCompatEditText edit1 = new AppCompatEditText(this);
        edit1.setLayoutParams(params);

        // editText2
        final AppCompatEditText edit2 = new AppCompatEditText(this);
        edit2.setLayoutParams(params);

        // editText3
        final AppCompatEditText edit3 = new AppCompatEditText(this);
        edit3.setLayoutParams(params);

        // editText4
        final AppCompatEditText edit4 = new AppCompatEditText(this);
        edit4.setLayoutParams(params);

        // editText5
        final AppCompatEditText edit5 = new AppCompatEditText(this);
        edit5.setLayoutParams(params);

        // MadLibView
        final AppCompatTextView MadLibView = new AppCompatTextView(this);

        // button
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Create MadLib");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get strings
                String name = edit1.getText().toString();
                String adjective = edit2.getText().toString();
                String place1 = edit3.getText().toString();
                String place2 = edit4.getText().toString();
                String activity = edit5.getText().toString();

                // madlib
                String MadLib = name + " went to a " + adjective + " " + place1 + " and had a drink." +
                        " They then went to " + place2 + " to do " + activity + ".";

                MadLibView.setText(MadLib);
            }
        });

        // layout
        LinearLayout layout = new LinearLayout(this);

        layout.addView(text1);
        layout.addView(edit1);

        layout.addView(text2);
        layout.addView(edit2);

        layout.addView(text3);
        layout.addView(edit3);

        layout.addView(text4);
        layout.addView(edit4);

        layout.addView(text5);
        layout.addView(edit5);

        layout.addView(button);
        layout.addView(MadLibView);
        layout.setOrientation(LinearLayout.VERTICAL);

        setContentView(layout);
    }
}