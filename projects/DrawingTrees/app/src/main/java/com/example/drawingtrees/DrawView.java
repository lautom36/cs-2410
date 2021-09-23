package com.example.drawingtrees;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static android.content.ContentValues.TAG;


public class DrawView extends View {
    Paint paint = new Paint();
    private ArrayList<CustomLine> lines = new ArrayList<>();
    private CustomLine currentLine;



    // draw view construct
    public  DrawView(Context context){
        super(context);
        // need to set the background
        GradientDrawable canvasBackground = new GradientDrawable();
        canvasBackground.setColor(getResources().getColor(R.color.colorPrimary, null));
    }

    public  DrawView(Context context, Canvas canvas){
        super(context);
        // need to set the background
        GradientDrawable canvasBackground = new GradientDrawable();
        canvasBackground.setColor(getResources().getColor(R.color.colorPrimary, null));
    }

    public void addLine(CustomLine line) {
        lines.add(line);
        currentLine = line;
//        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        GradientDrawable canvasBackground = new GradientDrawable();
        canvasBackground.setColor(getResources().getColor(R.color.colorPrimary, null));
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight()); // set origin to bottom middle of the screen
        canvas.rotate(180); // rotate 180 degrees

        // draw trunk
        CustomLine trunk = lines.get(0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(trunk.width);
        paint.setColor(trunk.color);
        canvas.translate(trunk.startX,trunk.startY);
        canvas.rotate(trunk.angle);
        trunk.drawLine(canvas,paint,getResources());
        lines.remove(0);




        // print the line
//        lines.forEach(line ->{
//
//            if(!line.isLeaf) {
//                Log.d(TAG, "----------------------------------------------------------- save");
//                canvas.save();
//            }
//
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(line.width);
//            paint.setColor(line.color);
//            canvas.translate(line.startX,line.startY);
//            canvas.rotate(line.angle);
//            line.drawLine(canvas,paint,getResources());
//
//            if (line.isLeaf) {
//                Log.d(TAG, "----------------------------------------------------------- restore");
//                canvas.restore();
//            }
////            canvas.restore();
//        });
        int saveCt = 0;
        Log.d(TAG, "red: " + Color.RED);
        Log.d(TAG, "green: " + Color.GREEN);
        Log.d(TAG, "blue " + Color.BLUE);
        Log.d(TAG, "lines length " + lines.size());
        Log.d(TAG, "savect: " + saveCt);
        Log.d(TAG, "----------------------------------------------------------- start loop");
        for (CustomLine line : lines){

            if (saveCt == 0){
                Log.d(TAG, "save root");
                canvas.save();
                saveCt++;
            }

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(line.width);
            paint.setColor(line.color);
            canvas.translate(line.startX,line.startY);
            canvas.rotate(line.angle);
            line.drawLine(canvas,paint,getResources());
            Log.d(TAG, "-line drawn " + "savect: " + saveCt + " " + line.height);

            if (line.height > 1) {
                Log.d(TAG, "restore");
                canvas.restore();
                saveCt--;
            } else if (line.height != 0){
                Log.d(TAG, "save else " + line.height);
                canvas.save();
                saveCt++;
            }
        }
        Log.d(TAG, "----------------------------------------------------------- end loop");
        saveCt = 0;

        // testing
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(50);
//        canvas.rotate(0);
//        canvas.save();
//        canvas.drawLine(0,0,0,200, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(0,200);
//        canvas.rotate(-45);
//        paint.setColor(Color.RED);
//        canvas.drawLine(0,0,0,200, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(0,200);
//        canvas.rotate(45);
//        paint.setColor(Color.GRAY);
//        canvas.drawLine(0,0,0, 200, paint);
//        canvas.restore();

    }

    // drawTree method
    // drawTree recusive helper
    public int getStartRootX(){
        return getWidth() / 2;
    }
    public int getStartRootY(){
        return getHeight();
    }

}
