public class ActionStack {
    int size;
    TaskNode top;
    public ActionStack(){
        size = 0;
        top = null;
    }
    public void Push(TaskNode node){
        node.next = top;
        this.top = node;   
        this.size++;
    }
    public TaskNode Pop(){
        if (this.top == null) return null;
        TaskNode current = this.top;
        this.top = this.top.next;
        this.size--;
        current.next = null;
        return current;
    }
}