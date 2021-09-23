package com.example.contacts.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.R;

public class DefaultPictureDisplay extends View {
    Paint paint = new Paint();
    String letter;
    int width = 40;
    float density;
    String tag = "DefaultPictureDisplay.java";

    public DefaultPictureDisplay(Context context, String letter) {
        super(context);
        float density = getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int)(width*density) , (int)(width*density));
        this.letter = letter.toUpperCase();
        setLayoutParams(params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(-getWidth()/4, -getHeight()/4);
        int halfWidth = width / 2;
        paint.setColor(getResources().getColor(R.color.colorPrimaryDark, null));
        float density = getResources().getDisplayMetrics().density;
        canvas.drawCircle(800, 800, 800, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(1000);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(letter, (800), (1100), paint);
    }
}
