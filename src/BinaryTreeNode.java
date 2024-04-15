
public class BinaryTreeNode<T> {

    private BinaryTreeNode<T> parent, left, right;
    private T element;
    private boolean isBlack;

    public BinaryTreeNode(T elem){

        parent = left = right = null;
        element = elem;
        isBlack = true;

    }

    public void setLeft(BinaryTreeNode<T> node){

        left = node;

    }

    public void setRight(BinaryTreeNode<T> node){

        right = node;
    }

    public BinaryTreeNode<T> getLeft(){
        return left;
    }

    public BinaryTreeNode<T> getRight(){
        return right;
    }

    public BinaryTreeNode<T> getParent(){
        return parent;
    }

    public T getElement(){
        return element;
    }

    public int numChildren(){
        //implement
        return 0;
    }

    public boolean isBlack(){
        return isBlack;
    }

    public boolean isLeaf(){
        //implement
        return false;
    }
}
