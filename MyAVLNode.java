public class MyAVLNode<T> {

    public T item;
    public MyAVLNode rNode;
    public MyAVLNode lNode;

    public MyAVLNode (T value){
        item = value;
        lNode = rNode = null;
    }
}
