package com.example;
/**
 * * int size
* TaskNode front
* TaskNode rear
* Enqueue(TaskNode node)
* TaskNode Dequeue()
 */
public class TaskQueue {
    int size;
    TaskNode front;
    TaskNode rear;
    public TaskQueue(){
        this.size = 0;
        this.front = null;
        this.rear = null;
    }
    public void Enqueue(TaskNode node){
        node.next = null;
        if (this.size == 0){
            this.front = node;
            this.rear = node;
            }
        else {
            this.rear.next = node;
            this.rear = node;
            }
        this.size++;
    }
    public TaskNode Dequeue(){
        if (this.front == null){
            return null;
        }
        TaskNode returnVal = this.front;
        if (this.size == 1){
            this.front = null;
            this.rear = null;
        }
        else {
            this.front = this.front.next;
        }
        this.size--;
        returnVal.next = null;
        return returnVal;
    }
}
