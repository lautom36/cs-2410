package com.example.drawingtrees;

import android.graphics.Color;

public class Tree {
    TreeNode root = new TreeNode();
    int maxLength;
    int minLength;
    int maxAngle;
    int minAngle;
    int maxTrunkWidth;
    int minTrunkWidth;
    int rootStartX;
    int rootStartY;

    // tree constructor
    public Tree(
            String maxLength, String minLength,
            String maxAngle, String minAngle,
            String maxTrunkWidth, String minTrunkWidth,
            int rootStartX, int rootStartY
            )
    {
        if (maxLength.equals("")){ this.maxLength = 200;}
        else{ this.maxLength = Integer.parseInt(maxLength);}

        if (minLength.equals("")){ this.minLength = 200;}
        else{ this.minLength = Integer.parseInt(minLength);}

        if (maxAngle.equals("")){ this.maxAngle = 50;}
        else{ this.maxAngle = Integer.parseInt(maxAngle);}

        if (minAngle.equals("")){ this.minAngle = -50;}
        else{ this.minAngle = Integer.parseInt(minAngle);}

        if (maxTrunkWidth.equals("")){ this.maxTrunkWidth = 50;}
        else{ this.maxTrunkWidth = Integer.parseInt(maxTrunkWidth);}

        if (minTrunkWidth.equals("")){ this.minTrunkWidth = 50;}
        else{ this.minTrunkWidth = Integer.parseInt(minTrunkWidth);}


        this.rootStartX = rootStartX;
        this.rootStartY = rootStartY;
    }

    // tree node constructor
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        int height;
        int startX;
        int endX;
        int startY;
        int endY;
        int angle;
        int width;
        int length;
        boolean isLeaf;
        int color;
    }

    public void generate(int maxDepth) {
        root.angle = 0;
        root.startX = rootStartX;
        root.startY = rootStartY;
        root.width = random(minTrunkWidth, maxTrunkWidth);
        generate(root, 0, maxDepth);
    }

    // recursive helper
    private void generate(TreeNode node, int height, int maxDepth){
        if (height >= maxDepth) {return;}
        node.height = height;

        // find angle
        if (node != root){
            node.angle = random(minAngle, maxAngle);
        }

        // find the start point
        if (node != root) {
            node.startX = 0;
            node.startY = node.parent.length;
        }

        // find the length and the end point
        node.length = random(minLength, maxLength);

//        if (node == root){
//            node.endX = node.startX;
//            node.endY = node.startY + node.length;
//        }
//        else {
//            node.endX = (int) ((node.length * Math.cos(node.angle)) + node.startX) * -1;
//            node.endY = (int) (node.length * Math.sin(node.angle)) + node.startY;
//        }


        // find width
        if (node != root) {
            node.width = node.parent.width / 2;
        }

        node.left = new TreeNode();
        node.left.parent = node;

        node.right = new TreeNode();
        node.right.parent = node;

        node.color = Color.BLACK;

        generate(node.right, height + 1, maxDepth);
        generate(node.left, height + 1, maxDepth);
//        findLeaves(root);
    }

    public void findLeaves(TreeNode node){
        if(node == null) {return;}
        if (node.right == null && node.left == null){
            node.isLeaf = true;
            return;
        }
        findLeaves(node.right);
        findLeaves(node.left);

    }

    // get a random number between two values
    private int random(int low, int high){
        int range = high - low + 1;
        return (int)(Math.random() * range) + low;
    }
}
