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
        TaskNode sectorTask = new TaskNode(sectorID, taskID, desc);
        TaskNode queueTask = new TaskNode(sectorID, taskID, desc);
        TaskNode stackTask = new TaskNode(sectorID, taskID, desc);
        sector.tasks.Insert(sectorTask);
        deployment.Enqueue(queueTask);
        undoLog.Push(stackTask);
    }
    public void processNextTask(){
        TaskNode current = deployment.Dequeue();
        if (current == null) return;
        AVLNode sector = sectors.Search(current.sectorID);
        if (sector == null) return;
        TaskNode task = sector.tasks.Search(current.taskID);
        if (task == null) return;
        TaskNode archive = new TaskNode(task.sectorID, task.taskID,task.description);
        System.out.println("[Deployment] Drone dispatched for Task "+task.taskID+ "("+task.description+").");
        task.description ="Completed";

        history.Append(archive);
        System.out.println("[Archive] "+ task.taskID +" Saved to Deployment History.");
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
        System.out.println("[Taks] "+last.taskID+ " has been removed from sector "+ last.sectorID+".");
    }
    public void systemAudit(){
        System.out.println("[Audit] Printing Global Sector Index: \n");
        sectors.Traverse();
    }
    public void printDeploymentHistory(){
        history.Traverse();
    }
    public void searchSector(int sectorID){
        AVLNode sector = sectors.Search(sectorID);
        if(sector == null) System.out.println("[Search] Sector "+sectorID+" not found.");
        else {
            System.out.println("[Search] Sector " + sectorID + " found.");
            System.out.println(sector.tasks);
        }
        System.out.println("[Analytics] N (Total Sectors) = " + sectors.size + " | Comparisons = " + sectors.comparisons);
    }
    public String toString(){

        return "NEOM_Core: " +
    
               "Sectors=" + sectors.size +
    
               ", PendingTasks=" + deployment.size +
    
               ", HistorySize=" + history.size;
    
    }
}
