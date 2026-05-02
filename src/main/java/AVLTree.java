public class AVLTree { //open for any attributes or methods
    AVLNode root;
    int size;
    int comparisons;
    // Left is smaller, right is bigger
    public AVLTree(){
        this.root = null;
        this.size = 0;
    }
        public void Insert(int sectorID){
        this.root = InsertRecursive(this.root, sectorID);
    }
    private AVLNode InsertRecursive(AVLNode node, int sectorID){
        if(node == null){
            node = new AVLNode();
            node.sectorID = sectorID;
            this.size++;
            return node;
        }
        if (sectorID == node.sectorID){
            return node;
        }
        else if(sectorID > node.sectorID){
            node.right = InsertRecursive(node.right, sectorID);
        }
        else{
            node.left = InsertRecursive(node.left, sectorID);
        }
        node.height = 1 + max(height(node.left),height(node.right));
        node = balance(node);
        return node;
    }
    private int height(AVLNode node){
        if (node == null) return -1;
        return node.height;
    }
    private int max(int a, int b){
        if (a>=b) return a;
        else return b;
    }
    public void Remove(int sectorID){
        this.root = RemoveRecursive(this.root, sectorID);
    }
    private AVLNode RemoveRecursive(AVLNode node,int sectorID){
        if(node == null){
            return null;
        }

        if(node.sectorID == sectorID){
            if(node.left == null && node.right == null){ // No children
                this.size--;
                return null;
            } else if (node.left == null){ // One Child -> Right
                this.size --;
                return node.right;
            } else if (node.right == null){ // One Child -> Left
                this.size--;
                return node.left;
            }
            else{ // Two children : Minimum Value of Right Subtree
                AVLNode current = node.right; // choosing successor
                while (current.left != null){
                    current = current.left;
                }
                node.sectorID = current.sectorID;
                node.tasks = current.tasks;
                node.right= RemoveRecursive(node.right, current.sectorID);
                 // deleting the successor from the position below will decrement, no size--
            }
        }
        else if(sectorID > node.sectorID){
            node.right = RemoveRecursive(node.right, sectorID);
        }
        else{
            node.left = RemoveRecursive(node.left, sectorID);
        }
        node.height = 1 + max(height(node.left), height(node.right));
        node = balance(node);
        return node;
    }
    public AVLNode Search(int sectorID){
        this.comparisons = 0;
        if (this.root == null){
            return null;
        }
        AVLNode current = this.root;
        while(current != null){
            this.comparisons++;
            if(current.sectorID == sectorID){
                return current;
            } else if (sectorID > current.sectorID){
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }
    public void Traverse(){ // print all using in-order
        TraverseRecursive(this.root);
    }
    private void TraverseRecursive(AVLNode node){
        if (node == null) return;
        TraverseRecursive(node.left);
        System.out.println(
            "-- Sector " + node.sectorID +": "+
            node.tasks.toString()
        );
        TraverseRecursive(node.right);
    }
    
    private AVLNode balance(AVLNode node){
        if(node == null) return null;
        int factor = height(node.left) - height(node.right);
        if (factor >= -1 && factor <= 1) return node;
        //if height is equal it is counted as same side as side A, thats why we have = on first
        if(factor > 1) { //Left
            if (height(node.left.left) >= height(node.left.right)) return rightRotation(node); // Left-Left
            else{ //Left-Right
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        } else if(factor < -1){ // Right
            if(height(node.right.left)> height(node.right.right)){ //Right-Left
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
            else return leftRotation(node); // Right-Right
        }
        return node;

    }
    private AVLNode leftRotation(AVLNode oldRoot){
        AVLNode newRoot = oldRoot.right; // Right child of newRoot is unchanged no need to store. Left of oldRoot unchanged
        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;
        oldRoot.height = 1 + max(height(oldRoot.left), height(oldRoot.right));
        newRoot.height = 1 + max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }
    private AVLNode rightRotation(AVLNode oldRoot){
        AVLNode newRoot = oldRoot.left;
        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;
        oldRoot.height = 1 + max(height(oldRoot.left), height(oldRoot.right));
        newRoot.height = 1 + max(height(newRoot.left), height(newRoot.right));
        return newRoot;

    }
}
