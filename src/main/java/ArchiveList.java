public class ArchiveList {
    int size;
    TaskNode head;
    TaskNode tail;
    public ArchiveList(){
        size = 0;
        head = null;
        tail = null;
    }
    public void Append(TaskNode node){
        node.next = null;
        if(size == 0){
            head = node;
            tail = node;
        }
        else{
            tail.next = node;
            tail = node;
        }
        size++;
    }
    public void Traverse(){
        TaskNode current = head;
        while(current != null){
            System.out.println(current.taskID + " "+ current.description);
            current = current.next;
        }
    }
}
