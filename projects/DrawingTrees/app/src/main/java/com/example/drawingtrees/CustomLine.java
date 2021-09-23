package com.example.drawingtrees;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class CustomLine {
    int startX;
    int startY;
    int endX;
    int endY;
    int width;
    int angle;
    int length;
    boolean isLeaf;
    int color;
    int height;
//    public CustomLine(int startX, int startY, int endX, int endY, int width){
//        this.startX = startX;
//        this.startY = startY;
//        this.endX = endX;
//        this.endY = endY;
//        this.width = width;
//    }

    // custom line constructor
    public CustomLine(int startX, int startY, int width, int angle, int length, boolean isLeaf, int color, int height ) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.angle = angle;
        this.length = length;
        this.isLeaf = isLeaf;
        this.color = color;
        this.height = height;
    }

    // draw a custom line with math
//    public void drawLine(Canvas canvas, Paint paint, Resources resources) {
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(width);
//        canvas.drawLine(startX, startY, endX,endY, paint);
//    }

    // draw a custom line without math
    public void drawLine(Canvas canvas, Paint paint, Resources resources) {
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(width);


//        canvas.translate(startX, startY);
//        canvas.rotate(angle);

        canvas.drawLine(0, 0, 0, length, paint);

    }
}
