import java.util.Collection;

public interface ICollection<T> extends Iterable<T> {
    boolean add(T toAdd);
    boolean addAll(Collection<T> values);
    void clear();
    boolean contains(T item);
    boolean isEmpty();
    void updateAll(T oldValue, T newValue);
    boolean remove(T item);
    int size();
}