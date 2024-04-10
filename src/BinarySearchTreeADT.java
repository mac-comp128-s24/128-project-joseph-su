
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T>{

    public void addElement(T element);

    public T removeElement(T target);

    public void removeAllOccurences(T target);

    public T removeMin();

    public T removeMax();

    public T findMin();

    public T findMax();
}