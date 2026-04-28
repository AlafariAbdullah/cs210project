package com.example;
/**
 */
public class SList {
    int size;
    TaskNode head;
    public SList(){
        this.size = 0;
        this.head = null;
    }
    // public SList(TaskNode node){
    //     this.size = 1;
    //     this.head = node;
    //     node.next = null;
    // } 
    public void Insert(TaskNode node){
        if(this.head == null){
            this.head = node;
            this.size++;
        }
        else{
            TaskNode current = this.head;
            while(current.next != null){
                current = current.next;
            }
            current.next = node;
            this.size++;
        }
    }
    public void Remove(TaskNode node){
        if(this.head == null){
            return;
        }
        if(this.head.equals(node)){
            this.head = this.head.next;
            this.size--;
            return;
        }
        TaskNode prev = this.head;
        TaskNode current = prev.next;
        while(current != null){
            if(current.equals(node)){
            prev.next = current.next;
            size--;
            return;
        }
        prev = current;
        current = current.next;
    }

    }
    public TaskNode Search(String taskID){
        TaskNode current = this.head;
        while(current != null){
            if(current.taskID.equals(taskID)){
                return current;
            }
            current = current.next;
        }
        return null;
    }
    public String toString(){
        String result = "";
        TaskNode current = this.head;
        while(current != null){
            result += current.taskID + " " + current.description + "\n";
            current = current.next;
        }
        return result;
    }
}
