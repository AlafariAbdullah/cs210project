package com.example;

public class AVLNode {
    int sectorID;
    int height;
    SList tasks;
    AVLNode left;
    AVLNode right;
    public AVLNode(){ // Required
        this.sectorID = 0;
        this.height = 0;
        this.tasks = new SList();
        this.left = null;
        this.right = null;
    }

}
