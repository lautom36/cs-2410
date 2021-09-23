package com.example.calculator;

public class ButtonConfig {
    public enum ButtonType {
        Number,
        Operator,
        Equals,
        Clear
    }
    private String buttonText;
    private int row;
    private int column;
    private int size;
    private ButtonType type;



    public ButtonConfig(String buttonText, int row, int column, int size, ButtonType type) {
        this.buttonText = buttonText;
        this.row = row;
        this.column = column;
        this.size = size;
        this.type = type;

    }

    public ButtonConfig(String buttonText, int row, int column, int size){
        this.buttonText = buttonText;
        this.row = row;
        this.column = column;
        this.size = size;
        this.type = ButtonType.Number;

    }

    public String getButtonText(){ return buttonText; }

    public int getRow() { return row; }

    public int getColumn() { return column; }

    public int getSize() { return size; }

    public ButtonType getType() { return type; }

}
