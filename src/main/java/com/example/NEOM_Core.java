package com.example;

public class NEOM_Core {
    AVLTree sectors;
    TaskQueue deployment;
    ActionStack undoLog;
    ArchiveList history;
    public NEOM_Core(){
        this.sectors = new AVLTree();
        this.deployment = new TaskQueue();
        this.undoLog = new ActionStack();
        this.history = new ArchiveList();
    }
    public void addTask(int sectorID, String taskID, String desc){
        sectors.Insert(sectorID);
        AVLNode sector = sectors.Search(sectorID);
        if (sector == null) return;
        TaskNode task = new TaskNode(sectorID, taskID, desc);
        sector.tasks.Insert(task);
        deployment.Enqueue(task);
        undoLog.push(task);
    }
    public void processNextTask(){}
    public void undoLastAction(){}
    public void systemAudit(){}
    public void printDeploymentHistory(){}
    public void searchSector(int sectorID){}
    public String toString(){
        return "String";
    }




}
