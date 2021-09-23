package com.example.calculator.components;

import android.content.Context;
import android.view.Gravity;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class CalculatorPanel extends AppCompatTextView {
    public CalculatorPanel(Context context) {
        super(context);
        GridLayout.LayoutParams panelParams = new GridLayout.LayoutParams();
        panelParams.rowSpec = GridLayout.spec(0,1,1);
        panelParams.columnSpec = GridLayout.spec(0,3,1);
        setGravity(Gravity.CENTER);
        setTextSize(18);
        setLayoutParams(panelParams);
    }
}
