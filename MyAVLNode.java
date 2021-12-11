public class MyAVLNode<T> {

    public T item;
    public MyAVLNode rNode;
    public MyAVLNode lNode;
    public int height = 0;
    public int balance = 0;

    public MyAVLNode (T value){
        item = value;
        lNode = rNode = null;
    }

    public int getLHeight(){
        return lNode == null ? -1 : lNode.height;
    }

    public int getRHeight(){
        return rNode == null ? -1 : rNode.height;
    }
}
