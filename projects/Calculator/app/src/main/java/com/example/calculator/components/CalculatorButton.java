package com.example.calculator.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.example.calculator.ButtonConfig;
import com.example.calculator.R;


public class CalculatorButton extends AppCompatButton {
    public CalculatorButton(Context context, ButtonConfig data, View.OnClickListener onClick) {
        super(context);
        setText(data.getButtonText());
        setTextColor(getResources().getColor(R.color.text, null));
        setTextSize(24);
        setOnClickListener(onClick);

        if (data.getType() == ButtonConfig.ButtonType.Number){
            GradientDrawable backgroundNumber = new GradientDrawable();
            backgroundNumber.setColor(getResources().getColor(R.color.calcNumber, null));
            backgroundNumber.setCornerRadius(100);
            setBackground(backgroundNumber);
        } else {
            GradientDrawable backgroundOperator = new GradientDrawable();
            backgroundOperator.setColor(getResources().getColor(R.color.calcOperator, null));
            backgroundOperator.setCornerRadius(100);
            setBackground(backgroundOperator);
        }


        GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
        gridParams.rowSpec = GridLayout.spec(data.getRow(), data.getSize(),1);
        gridParams.columnSpec = GridLayout.spec(data.getColumn(), data.getSize(),1);
        gridParams.setMargins(12,12,12,12);

        setLayoutParams(gridParams);
    }
}
