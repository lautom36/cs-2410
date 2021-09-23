package com.example.drawingtrees;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class DrawActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Canvas drawCanvas = new Canvas();
//        DrawView drawView = new DrawView(this);

        LinearLayout mainlayout = new LinearLayout(this);
        GradientDrawable canvasBackground = new GradientDrawable();

        int color1 = getResources().getColor(R.color.colorPrimary, null);
        int color2 = getResources().getColor(R.color.colorAccent, null);
        int[] colors = new int[2];
        colors[0] = color1;
        colors[1] = color2;

        canvasBackground.setColors(colors);

        mainlayout.setBackground(canvasBackground);
        ;

        DrawView drawView = new DrawView(this, drawCanvas);


        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String maxLength = intent.getStringExtra("maxLength");
        String minLength = intent.getStringExtra("minLength");
        String maxAngle = intent.getStringExtra("maxAngle");
        String minAngle = intent.getStringExtra("minAngle");
        String maxTrunkWidth = intent.getStringExtra("maxTrunkWidth");
        String minTrunkWidth = intent.getStringExtra("minTrunkWidth");
        int rootStartX = drawView.getStartRootX();
        int rootStartY = drawView.getStartRootY();

        // generate tree
        Tree tree = new Tree(
                maxLength, minLength,
                maxAngle, minAngle,
                maxTrunkWidth, minTrunkWidth,
                rootStartX, rootStartY

        );
        // create tree
        int maxDepth = 3;
        tree.generate(maxDepth);
        tree.findLeaves(tree.root);
        drawTree(tree.root, drawView, drawCanvas);




        mainlayout.addView(drawView);
        setContentView(mainlayout);
    }
    private void drawTree(Tree.TreeNode node, DrawView drawView, Canvas canvas){
//        if(node == null) { return;}
////        drawView.addLine(new CustomLine(node.startX, node.startY, node.endX, node.endY, node.width));
//
//        canvas.save();
////        canvas.translate(100, 100000);
//        drawView.addLine(new CustomLine(node.startX, node.startY, node.width, node.angle, node.length, node.isLeaf,node.color));
//        drawTree(node.left, drawView, canvas);
//        // need to restore canvas
////        canvas.restore();
//        drawTree(node.right, drawView,canvas);
        if(node != null) {
//            if (node.length == 0){
////                node.length = 200;
//                node.color = Color.YELLOW;
//            }
//            if (node.length != 0){
//                drawView.addLine(new CustomLine(node.startX, node.startY, node.width, node.angle, node.length, node.isLeaf,node.color));
//            }
            drawView.addLine(new CustomLine(node.startX, node.startY, node.width, node.angle, node.length, node.isLeaf,node.color, node.height));
            drawTree(node.left, drawView, canvas);
            drawTree(node.right, drawView,canvas);
        }
    }
}

