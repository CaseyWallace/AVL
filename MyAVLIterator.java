import java.util.Iterator;
import java.util.ArrayList;

public class MyAVLIterator<T extends Comparable <T>> implements Iterator<T>{

  Iterator<T> it;
  ArrayList<T> list;

  public MyAVLIterator(MyAVL mAVL){
    list = mAVL.toArrayList();
    it = list.iterator();
  }

  @Override
  public boolean hasNext() {
    return it.hasNext();
  }

  @Override
  public T next() {
    return it.next();
  }
  
}
