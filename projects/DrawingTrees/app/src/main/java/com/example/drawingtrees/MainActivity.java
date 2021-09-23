package com.example.drawingtrees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> lables = new ArrayList<String>(){
        {
            add("Max Length");
            add("Min Length");
            add("Max Angle");
            add("Min Angle");
            add("Max trunk width");
            add("Min trunk Width");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // first view for edit text boxes: max length, min length, max angle, min angle, max trunk width, min trunk width, and a button to draw the tree
        // want to draw with lines
//        addLables();



        // Main Layout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // lablled inputs
        final LabelledInput maxLengthInput = new LabelledInput(this, "Max Length");
        mainLayout.addView(maxLengthInput);

        LabelledInput minLengthInput = new LabelledInput(this, "Min Length");
        mainLayout.addView(minLengthInput);

        LabelledInput maxAngleInput = new LabelledInput(this, "Max Angle");
        mainLayout.addView(maxAngleInput);

        LabelledInput minAngleInput = new LabelledInput(this, "Min Angle");
        mainLayout.addView(minAngleInput);

        LabelledInput maxTrunkWidthInput = new LabelledInput(this, "Max Trunk Width");
        mainLayout.addView(maxTrunkWidthInput);

        LabelledInput minTrunkWidthInput = new LabelledInput(this, "Min Trunk Width");
        mainLayout.addView(minTrunkWidthInput);

//        lables.forEach(data -> {
//            LabelledInput input = new LabelledInput(this, data);
//            mainLayout.addView(input);
//        });

        // draw tree button
        AppCompatButton drawButton = new AppCompatButton(this);
        drawButton.setText("Draw Tree");
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                String maxLength = maxLengthInput.getText().toString();
                intent.putExtra("maxLength", maxLength);

                String minLength = minLengthInput.getText().toString();
                intent.putExtra("minLength", minLength);

                String maxAngle = maxAngleInput.getText().toString();
                intent.putExtra("maxAngle", maxAngle);

                String minAngle = minAngleInput.getText().toString();
                intent.putExtra("minAngle", minAngle);

                String maxTrunkWidth = maxTrunkWidthInput.getText().toString();
                intent.putExtra("maxTrunkWidth", maxTrunkWidth);

                String minTrunkWidth = minTrunkWidthInput.getText().toString();
                intent.putExtra("minTrunkWidth", minTrunkWidth);

                startActivity(intent);
            }
        });
        mainLayout.addView(drawButton);


        // Draw View
        DrawView drawingview = new DrawView(this);
        drawingview.setOnClickListener(view -> {
            // go to canvas
        });


        setContentView(mainLayout);
    }

//    private void addLables(){
//        LinearLayout mainLayout = findViewById(R.id.mainLayout);
//        lables.forEach(data -> {
//            AppCompatTextView textView = new AppCompatTextView(this);
//            textView.setText(data);
//            AppCompatEditText editTextView = new AppCompatEditText(this);
//            mainLayout.addView(textView);
//            mainLayout.addView(editTextView);
//        });
//    }
}

