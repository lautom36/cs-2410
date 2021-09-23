package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.calculator.components.CalculatorButton;
import com.example.calculator.components.CalculatorPanel;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

//    int operatorButton = getResources().getColor(R.color.calcOperator, null);
//    int numberButton = getResources().getColor(R.color.calcNumber, null);

    private ArrayList<ButtonConfig> buttonData = new ArrayList<ButtonConfig>(){
        {
            add(new ButtonConfig("C", 0, 3, 1, ButtonConfig.ButtonType.Clear));

            add(new ButtonConfig("1", 1, 0, 1));
            add(new ButtonConfig("2", 1, 1, 1));
            add(new ButtonConfig("3", 1, 2, 1));

            add(new ButtonConfig(" / ", 1, 3, 1, ButtonConfig.ButtonType.Operator));

            add(new ButtonConfig("4", 2, 0, 1));
            add(new ButtonConfig("5", 2, 1, 1));
            add(new ButtonConfig("6", 2, 2, 1));

            add(new ButtonConfig(" * ", 2, 3, 1, ButtonConfig.ButtonType.Operator));

            add(new ButtonConfig("7", 3, 0, 1));
            add(new ButtonConfig("8", 3, 1, 1));
            add(new ButtonConfig("9", 3, 2, 1));

            add(new ButtonConfig(" - ", 3, 3, 1, ButtonConfig.ButtonType.Operator));

            add(new ButtonConfig("0", 4, 0, 2));

            add(new ButtonConfig(".", 4, 2, 1, ButtonConfig.ButtonType.Operator));
            add(new ButtonConfig(" + ", 4, 3, 1, ButtonConfig.ButtonType.Operator));

            add(new ButtonConfig("=", 5, 0, 4, ButtonConfig.ButtonType.Equals));
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createLayout();
        createCalcPanel();
        addButtons();




//        String expression = "";
//        Scanner scanner = new Scanner((Readable) calculatorPanel);
//        while (!expression.equals("stop")) {
//            expression = scanner.nextLine().trim();
//            if (!expression.equals("stop")) {
//                double result = Calculator.evaluate(expression);
//                System.out.println("" + result);
//            }
//        }

    }

    private  void createLayout() {
        GridLayout mainLayout = new GridLayout(this);
        mainLayout.setBackgroundColor(getResources().getColor(R.color.calcBackground, null));
        mainLayout.setColumnCount(4);
        mainLayout.setId(R.id.mainLayout);
        setContentView(mainLayout);
    }

    private void createCalcPanel(){
        CalculatorPanel calculatorPanel = new CalculatorPanel(this);
        calculatorPanel.setId(R.id.calculatorPanel);
        GridLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(calculatorPanel);

        calculatorPanel.setTextColor(getResources().getColor(R.color.text, null));

    }

    private void addButtons() {
        GridLayout mainLayout = findViewById(R.id.mainLayout);
        CalculatorPanel calculatorPanel = findViewById(R.id.calculatorPanel);
        buttonData.forEach(data -> {
            CalculatorButton button = new CalculatorButton(
                    this,
                    data,
                    (view) -> {

                        // user hits =
                        if (data.getType() == ButtonConfig.ButtonType.Equals) {
                            String expression = (String) calculatorPanel.getText();

                            Double result =  Calculator.evaluate(expression);
                            calculatorPanel.setText(result.toString());

                        }
                        else if (data.getType() == ButtonConfig.ButtonType.Clear){
                            calculatorPanel.setText("");

                        } else {
                            calculatorPanel.setText(
                                    calculatorPanel.getText().toString() + data.getButtonText());
                        }

                    }
            );
            mainLayout.addView(button);
        });
    }
}