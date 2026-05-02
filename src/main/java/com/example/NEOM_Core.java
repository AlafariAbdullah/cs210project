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
        undoLog.Push(task);
    }
    public void processNextTask(){
        TaskNode current = deployment.Dequeue();
        if (current == null) return;
        AVLNode sector = sectors.Search(current.sectorID);
        if (sector == null) return;
        TaskNode task = sector.tasks.Search(current.taskID);
        if (task == null) return;
        task.description = task.description + " (Completed)";
        TaskNode archive = new TaskNode(task.sectorID, task.taskID,task.description);
        history.Append(archive);
    }
    public void undoLastAction(){
        TaskNode last = undoLog.Pop();
        if (last == null) return;
        else{
            AVLNode sector = sectors.Search(last.sectorID);
            if(sector != null){
                sector.tasks.Remove(last);
                if (sector.tasks.size == 0){
                    sectors.Remove(last.sectorID);
                }
            }
            int initialSize = deployment.size;
            for (int i = 0; i < initialSize; i++) {
                TaskNode current = deployment.Dequeue();
                if(current == null) return;
                if (!current.taskID.equals(last.taskID)) deployment.Enqueue(current);
            }
        }
    }
    public void systemAudit(){
        sectors.Traverse();
    }
    public void printDeploymentHistory(){
        history.Traverse();
    }
    public void searchSector(int sectorID){
        AVLNode sector = sectors.Search(sectorID);
        if(sector == null) System.out.println("Sector "+sectorID+" not found.");
        else {
            System.out.println("Sector " + sectorID + " found.");
            System.out.println(sector.tasks);
        }
    }
    public String toString(){

        return "NEOM_Core: " +
    
               "Sectors=" + sectors.size +
    
               ", PendingTasks=" + deployment.size +
    
               ", HistorySize=" + history.size;
    
    }
}
