package com.example;

/**
 * 
 *
 *
 */
public class TaskNode 
{
    int sectorID;
    String taskID;
    String description;
    TaskNode next;

    public TaskNode(int sectorID, String taskID, String description) {
        this.sectorID = sectorID;
        this.taskID = taskID;
        this.description = description;
        this.next = null;
    }
}
