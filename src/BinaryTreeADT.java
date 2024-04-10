import java.util.Iterator;

public interface BinaryTreeADT<T> {
    
    public T getRootElement();

    public boolean isEmpty();

    public int size();

    public boolean contains(T target);

    public T find(T target);

    public Iterator<T> iterator();

    public Iterator<T> iteratorInOrder();

    public Iterator<T> itertorPreOrder();

    public Iterator<T> iteratorPostOrder();

    public Iterator<T> iteratorLevelOrder();

}
