
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTest {

    @Test
    public void testInsertion() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertEquals("5 10 15 ", tree.inorderTraversal());
    }

    @Test
    public void testRemoval() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.remove(tree.Search(5));
        assertEquals("10 15 ", tree.inorderTraversal());
    }

    @Test
    public void testPreorderTraversal() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertEquals("10 5 15 ", tree.preorderTraversal());
    }

    @Test
    public void testInorderTraversal() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertEquals("5 10 15 ", tree.inorderTraversal());
    }

    @Test
    public void testPostorderTraversal() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertEquals("5 15 10 ", tree.postorderTraversal());
    }
}
