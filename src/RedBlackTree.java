import java.awt.*;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;
/**
 * Implementation of a Red-Black Tree data structure.
 */

public class RedBlackTree {


   private Node root, NIL;
   private int length;

    /**
     * Constructor to initialize an empty Red-Black Tree.
     */

   public RedBlackTree() {
       length = 0;
       NIL = new Node();
       root = NIL;
   }
   /**
     * Get the root node of the Red-Black Tree.
     * 
     * @return The root node of the tree.
     */
    public Node getRoot() {
        return root;
    }

   /**
     * Get the size of the Red-Black Tree.
     * 
     * @return The number of nodes in the tree.
     */
   public int getSize() {
       return length;
   }
   

   /**
     * Get the depth of a subtree starting from the given node.
     * 
     * @param currentNode The current node being visited.
     * @param level       The level of the current node in the tree.
     * @return The depth of the subtree.
     */
   private int getDepth(Node currentNode, int level) {
       if (currentNode == null) return level;
       return Math.max(getDepth(currentNode.left, level + 1),
               getDepth(currentNode.right, level + 1));
   }

   /**
     * Get the depth of the Red-Black Tree.
     * 
     * @return The depth of the tree.
     */
   private int getDepth() {
       return getDepth(root, 0);
   }

   /**
     * Perform a left rotation on the given node.
     * 
     * @param currentNode The node on which the left rotation is performed.
     */
   private void leftRotate(Node currentNode) {
       Node y = currentNode.right;
       currentNode.right = y.left;

       if (y.left != NIL)
           y.left.parent = currentNode;
       y.parent = currentNode.parent;

       if (currentNode.parent == NIL) root = y;
       else if (currentNode.parent.left == currentNode) currentNode.parent.left = y;
       else currentNode.parent.right = y;

       y.left = currentNode;
       currentNode.parent = y;
   }

   /**
     * Perform a right rotation on the given node.
     * 
     * @param currentNode The node on which the right rotation is performed.
     */

   private void rightRotate(Node currentNode) {
       Node y = currentNode.left;
       currentNode.left = y.right;

       if (y.right != NIL)
           y.right.parent = currentNode;


       y.parent = currentNode.parent;


       if (currentNode.parent == NIL) root = y;
       else if (currentNode.parent.left == currentNode) currentNode.parent.left = y;
       else currentNode.parent.right = y;


       y.right = currentNode;
       currentNode.parent = y;
   }

   /**
     * Replace one node with another in the tree.
     * 
     * @param node        The node to be replaced.
     * @param replacement The node that will replace the original node.
     */
   private void replaceNode(Node node, Node replacement) {
       if (node.parent == NIL) root = replacement;
       else if (node == node.parent.left) node.parent.left = replacement;
       else node.parent.right = replacement;
       replacement.parent = node.parent;
   }

   /**
     * Fix any violations of the Red-Black Tree properties after insertion.
     * 
     * @param newNode The node that was inserted and may have caused violations.
     */
   private void insertFixer(Node newNode) {
       while (newNode.parent.colorIsRed) { 
           if (newNode.parent == newNode.parent.parent.left) {
               var y = newNode.parent.parent.right;
               if (y.colorIsRed) {
                   newNode.parent.colorIsRed = false;
                   y.colorIsRed = false;
                   newNode.parent.parent.colorIsRed = true;
                   newNode = newNode.parent.parent;
               } else {
                   if (newNode == newNode.parent.right) {
                       newNode = newNode.parent;
                       leftRotate(newNode);
                   }
                   newNode.parent.colorIsRed = false;
                   newNode.parent.parent.colorIsRed = true;
                   rightRotate(newNode.parent.parent);
               }
           } else {
               var y = newNode.parent.parent.left;
               if (y.colorIsRed) {
                   newNode.parent.colorIsRed = false;
                   y.colorIsRed = false;
                   newNode.parent.parent.colorIsRed = true;
                   newNode = newNode.parent.parent;
               } else {
                   if (newNode == newNode.parent.left) {
                       newNode = newNode.parent;
                       rightRotate(newNode);
                   }
                   newNode.parent.colorIsRed = false;
                   newNode.parent.parent.colorIsRed = true;
                   leftRotate(newNode.parent.parent);
               }
           }
       }
       root.colorIsRed = false;
   }

   /**
     * Fix any violations of the Red-Black Tree properties after deletion.
     * 
     * @param currentNode The node where violations may have occurred.
     */
   private void deleteFixer(Node currentNode) {
       Node y;
       while (currentNode != root && !currentNode.colorIsRed) {
           if (currentNode == currentNode.parent.left) {
               y = currentNode.parent.right;
               if (y.colorIsRed) {
                   y.colorIsRed = false;
                   currentNode.parent.colorIsRed = true;
                   leftRotate(currentNode.parent);
                   y = currentNode.parent.right;
               }
               if (!y.left.colorIsRed && !y.right.colorIsRed) {
                   y.colorIsRed = true;
                   currentNode = currentNode.parent;
               } else {
                   if (!y.right.colorIsRed) {
                       y.left.colorIsRed = false;
                       y.colorIsRed = true;
                       rightRotate(y);
                       y = currentNode.parent.right;
                   }
                   y.colorIsRed = currentNode.parent.colorIsRed;
                   currentNode.parent.colorIsRed = false;
                   y.right.colorIsRed = false;
                   leftRotate(currentNode.parent);
                   currentNode = root;
               }
           } else {
               y = currentNode.parent.left;
               if (y.colorIsRed) {
                   y.colorIsRed = false;
                   currentNode.parent.colorIsRed = true;
                   rightRotate(currentNode.parent);
                   y = currentNode.parent.left;
               }
               if (!y.right.colorIsRed && !y.left.colorIsRed) {
                   y.colorIsRed = true;
                   currentNode = currentNode.parent;
               } else {
                   if (!y.left.colorIsRed) {
                       y.right.colorIsRed = false;
                       y.colorIsRed = true;
                       leftRotate(y);
                       y = currentNode.parent.left;
                   }
                   y.colorIsRed = currentNode.parent.colorIsRed;
                   currentNode.parent.colorIsRed = false;
                   y.left.colorIsRed = false;
                   rightRotate(currentNode.parent);
                   currentNode = root;
               }
           }
       }
       currentNode.colorIsRed = false;
   }

   /**
     * Find the minimum node in the subtree rooted at the given node.
     * 
     * @param currentNode The root of the subtree.
     * @return The minimum node in the subtree.
     */
   private Node getMinimumNode(Node currentNode) {
       while (currentNode.left != NIL) {
           currentNode = currentNode.left;
       }
       return currentNode;
   }

   /**
     * Insert a value into the Red-Black Tree.
     * 
     * @param value The value to be inserted.
     */
   public void insert(int value) {
       if (root == NIL) {
           root = new Node(NIL);
           root.value = value;
           root.colorIsRed = false;
       } else {
           Node newNode = new Node(NIL);
           newNode.value = value;
           Node currentNode = root;
           Node currentParent = null;
           while (currentNode != NIL) {
               currentParent = currentNode;
               if (value < currentNode.value) currentNode = currentNode.left;
               else currentNode = currentNode.right;
           }
           newNode.parent = currentParent;
           if (value < currentParent.value) currentParent.left = newNode;
           else currentParent.right = newNode;


           insertFixer(newNode);
       }
       length++;
   }

    /**
     * Search for a node with the given value in the Red-Black Tree.
     * 
     * @param value The value to search for.
     * @return The node containing the value if found, otherwise null.
     */
   public Node Search(int value) {
       Node currentNode = root;
       while (currentNode != NIL && currentNode.value != value) {
           if (value < currentNode.value) currentNode = currentNode.left;
           else currentNode = currentNode.right;
       }
       return currentNode;
   }

   

    /**
     * Remove a node from the Red-Black Tree.
     * 
     * @param z The node to be removed.
     */
   public void remove(Node z) {
       if (z == NIL) 
       return; //changed null to NIL so that the deleteButton method can identify
       Node x, y = z;
       boolean originalColor = z.colorIsRed;
       if (z.left == NIL) {
           x = z.right;
           replaceNode(z, x);
       } else if (z.right == NIL) {
           x = z.left;
           replaceNode(z, x);
       } else {
           y = getMinimumNode(z.right);
           originalColor = y.colorIsRed;
           x = y.right;
           if (y.parent == z) {
               x.parent = y;
           } else {
               replaceNode(y, x);
               y.right = z.right;
               y.right.parent = y;
           }
           replaceNode(z, y);
           y.left = z.left;
           y.left.parent = y;
           y.colorIsRed = z.colorIsRed;
       }
       if (!originalColor) deleteFixer(x);
       length--;
   }

/**
     * Recursively trace the Red-Black Tree and draw it using a graphics group.
     * 
     * @param x             The x-coordinate of the current node.
     * @param y             The y-coordinate of the current node.
     * @param currentNode   The current node being processed.
     * @param graphicsGroup The graphics group used for drawing.
     * @param n             Number of nodes.
     * @param level         The level of the current node in the tree.
     * @param nodeDiameter  The diameter of each node.
     */
   private void traceTreeDrawer(int x, int y, Node currentNode, GraphicsGroup graphicsGroup, int n, int level, int nodeDiameter) {
       if (currentNode == NIL) {
           return;
       }

       GraphicsGroup nodeGroup = new GraphicsGroup();
  
       Ellipse nodeCircle = new Ellipse(x - nodeDiameter / 2, y - nodeDiameter / 2, nodeDiameter, nodeDiameter);  
       nodeCircle.setFilled(true);
       nodeCircle.setFillColor((currentNode.colorIsRed) ? Color.RED : Color.BLACK);
       nodeGroup.add(nodeCircle);
  
       int horizontalGap = (400 / (n * nodeDiameter)) * Math.max(3, level);
       int childY = y + 70;
       int leftChildX = x - horizontalGap - 30;
       int rightChildX = x + horizontalGap + 30;
       if (currentNode.left != NIL) {
           Line leftLine = new Line(x - nodeDiameter / 2, y, leftChildX, childY - nodeDiameter / 2);
           nodeGroup.add(leftLine);
       }
       if (currentNode.right != NIL) {
           Line rightLine = new Line(x + nodeDiameter / 2, y, rightChildX, childY - nodeDiameter / 2);
           nodeGroup.add(rightLine);
       }
  
       GraphicsText nodeValue = new GraphicsText(Integer.toString(currentNode.value));
       nodeValue.setPosition(nodeCircle.getX()+nodeDiameter/2 - nodeValue.getWidth()/2, nodeCircle.getY()+nodeDiameter/2);
       nodeValue.setFillColor(Color.white);
       nodeGroup.add(nodeValue);
       graphicsGroup.add(nodeGroup);
  
       traceTreeDrawer(leftChildX, childY, currentNode.left, graphicsGroup, 2 * n, level - 1, nodeDiameter);
       traceTreeDrawer(rightChildX, childY, currentNode.right, graphicsGroup, 2 * n, level - 1, nodeDiameter);
   }
  
    /**
         * Draw the Red-Black Tree using the specified graphics group and node diameter.
         * 
         * @param graphicsGroup The graphics group used for drawing.
         * @param nodeDiameter  The diameter of each node.
         */
   public void draw(GraphicsGroup graphicsGroup, int nodeDiameter) {
       int width = getDepth();
       this.traceTreeDrawer(1000 / 2, 200, root, graphicsGroup, 1, width * 2, nodeDiameter);
   }


/**
 * Perform a preorder traversal of the Red-Black Tree and return the traversal result as a string.
 *
 * @return The preorder traversal result.
 */
public String preorderTraversal() {
    StringBuilder result = new StringBuilder();
    preorderTraversal(root, result);
    return result.toString();
}

/**
 * Perform a preorder traversal of the Red-Black Tree starting from the given node and append the traversal result to the StringBuilder.
 *
 * @param currentNode The current node being visited.
 * @param result      The StringBuilder to append the traversal result.
 */
private void preorderTraversal(Node currentNode, StringBuilder result) {
    if (currentNode == NIL) {
        return;
    }
    result.append(currentNode.value).append(" ");
    preorderTraversal(currentNode.left, result);
    preorderTraversal(currentNode.right, result);
}



/**
 * Perform an in-order traversal of the Red-Black Tree and return the traversal result as a string.
 *
 * @return The in-order traversal result.
 */
public String inorderTraversal() {
    StringBuilder result = new StringBuilder();
    inorderTraversal(root, result);
    return result.toString();
}

/**
 * Perform an in-order traversal of the Red-Black Tree starting from the given node and append the traversal result to the StringBuilder.
 *
 * @param currentNode The current node being visited.
 * @param result      The StringBuilder to append the traversal result.
 */
private void inorderTraversal(Node currentNode, StringBuilder result) {
    if (currentNode == NIL) {
        return;
    }
    inorderTraversal(currentNode.left, result);
    result.append(currentNode.value).append(" ");
    inorderTraversal(currentNode.right, result);
}
/**
 * Perform a post-order traversal of the Red-Black Tree and return the traversal result as a string.
 *
 * @return The post-order traversal result.
 */
public String postorderTraversal() {
    StringBuilder result = new StringBuilder();
    postorderTraversal(root, result);
    return result.toString();
}

/**
 * Perform a post-order traversal of the Red-Black Tree starting from the given node and append the traversal result to the StringBuilder.
 *
 * @param currentNode The current node being visited.
 * @param result      The StringBuilder to append the traversal result.
 */
private void postorderTraversal(Node currentNode, StringBuilder result) {
    if (currentNode == NIL) {
        return;
    }

    postorderTraversal(currentNode.left, result);
    postorderTraversal(currentNode.right, result);
    result.append(currentNode.value).append(" ");
}


}
  


