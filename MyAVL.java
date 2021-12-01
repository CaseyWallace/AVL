import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyAVL<T extends Comparable<T>> implements ICollection<T> {

    private int size;
    private MyAVLNode root;

    //COMPLETE
    public MyAVL(){
        root = null;
    }

    //COMPLETE
    @Override
    public Iterator<T> iterator() {
        return new MyAVLIterator(this);
    }

    //COMPLETE
    @Override
    public boolean add(T toAdd) {
        root = addHelper(root, toAdd);
        size++;
        return true;
    }

    private MyAVLNode addHelper(MyAVLNode root, T toAdd) {
        if (root == null){
            root = new MyAVLNode(toAdd);
            return root;
        }
        if (toAdd.compareTo((T)root.item) < 0){
            root.lNode = addHelper(root.lNode, toAdd);
        } else {
            root.rNode = addHelper(root.rNode, toAdd);
        }
        return root;
    }

    //DEPRECIATED
    @Override
    public boolean addAll(Collection<T> values) {
        return false;
    }

    //COMPLETE
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //DEPRECIATED
    @Override
    public void updateAll(T oldValue, T newValue){}

    //COMPLETE
    @Override
    public boolean remove(T item) {
        if (contains(item)){
            MyAVLNode<T> temp = removeHelper(root, item);
            size--;
            root = temp;
            return true;
        } else {
            return false;
        }
    }

    private MyAVLNode removeHelper(MyAVLNode root, T item) {
        if (root == null){
            return null;
        }
        if (item.compareTo((T)root.item) < 0){
            root.lNode = removeHelper(root.lNode, item);
        } else if (item.compareTo((T)root.item) > 0){
            root.rNode = removeHelper(root.rNode, item);
        } else {
            if (root.lNode == null || root.rNode == null) {
                MyAVLNode temp = null;
                temp = root.lNode == null? root.rNode : root.lNode;

                if(temp == null){
                    return null;
                } else {
                    return root.rNode;
                }
            } else {
                MyAVLNode child = childHelp(root);
                root.item = child.item;
                root.rNode = removeHelper(root.rNode, (T)child.item);
                return root;
            }
        }
        return root;
    }

    private MyAVLNode childHelp(MyAVLNode root) {
        if (root == null) {
            return null;
        }
        MyAVLNode temp = root.rNode;
        while (temp != null){
            temp = temp.lNode;
        }
        return temp;
    }

    //COMPLETE
    @Override
    public int size() {
        return size;
    }

    //DEPRECIATED
    @Override
    public void clear() {
        
    }

    //COMPLETE
    @Override
    public boolean contains(T item) {
        MyAVLNode hold = root;
        boolean contains = false;
        contains = containsHelper(item, hold);
        return contains;
    }

    @SuppressWarnings("NullPointerException")
    private boolean containsHelper(T item, MyAVLNode hold){
        if (hold == null){
            return false;
        } else {
            int compare = item.compareTo((T) hold.item);
            if (compare == 0){
                return true;
            } else if (compare > 0) {
                return containsHelper(item, hold.rNode);
            } else{
                return containsHelper(item, hold.lNode);
            }

        }
    }
    public ArrayList toArrayList(){
        ArrayList values = new ArrayList();
        treeTravel(root, values);
        return values;
    }

    private void treeTravel(MyAVLNode root, ArrayList values){
        if (root != null){
            treeTravel(root.lNode, values);
            values.add(root.item);
            treeTravel(root.rNode, values);
        }
    }
}