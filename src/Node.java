
/**
 * Node class represents a node in a binary tree.
 * Each node contains a value, color information for Red-Black Tree, and references to its parent, left child, and right child.
 */
public class Node {

   public int value;
   public boolean colorIsRed;
   public Node parent, left, right;

    /**
     * Constructs a new node with default values.
     * The color of the node is set to black, and parent, left, and right references are set to null.
     */
    public Node() {
        colorIsRed = false;
        parent = left = right = null;
    }
    /**
     * Constructs a new node with the given sentinel node as references.
     * The color of the node is set to red, and parent, left, and right references are set to the sentinel node.
     * 
     * @param NIL The sentinel node to be used as references.
     */
    public Node(Node NIL) {
        colorIsRed = true;
        parent = left = right = NIL;
    }
}
