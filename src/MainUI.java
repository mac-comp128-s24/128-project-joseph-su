import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;
import edu.macalester.graphics.events.*;

public class MainUI {

    private static CanvasWindow canvas;
    private static TextField textField;


    public static void main(String[] args) {
        // RedBlackTree rbTree = new RedBlackTree(10); // Initialize with root value 10
        // rbTree.insert(15);
        // rbTree.insert(5);
        // rbTree.insert(20);
        // rbTree.remove(15);
        // rbTree.inorder(rbTree.getRoot()); // Display the tree nodes in order



        canvas = new CanvasWindow("tree", 600, 800);
        textField = new TextField();
        textField.setText("12");
        textField.setBackground(new Color(12,200,30));
        textField.setPosition(300,400);
        canvas.add(textField);
        canvas.draw();
    }
}
