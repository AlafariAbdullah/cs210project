public class ActionStack {
    int size;
    TaskNode top;
    public ActionStack(){
        size = 0;
        top = null;
    }
    public void Push(TaskNode node){
        node.next = top;
        top = node;   
        size++;
    }
    public TaskNode Pop(){
        TaskNode current = top;
        top = top.next;
        size--;
        current.next = null;
        return current;
    }
}