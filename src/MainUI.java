import java.awt.Color;
import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

/**
 * MainUI class represents the user interface for visualizing operations on a Red-Black Tree.
 */
public class MainUI {
   private final RedBlackTree tree;
   private static int CANVAS_WIDTH = 1000;
   private static int CANVAS_HEIGHT = 800;
   private static int NODE_DIAMETER = 50;


   private GraphicsGroup TreeLayer;
   private TextField inputNumber;
   private Button insertButton, deleteButton, clearAllButton, preorderButton, inorderButton, postOrderButton;
   private CanvasWindow canvas, canvasPrint;

    /**
     * Constructs a new MainUI object and initializes the user interface.
     */
    public MainUI() {
        tree = new RedBlackTree();
        initialize();
    }
    /**
     * Initializes the user interface components.
     */
    private void initialize() {
        canvas = new CanvasWindow("Red-Black Tree Visualizer", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.draw();

        Rectangle container = new Rectangle(10,25, CANVAS_WIDTH - 20, CANVAS_HEIGHT * 0.05);
        container.setFillColor(Color.LIGHT_GRAY);
        canvas.add(container);

        Rectangle container2 = new Rectangle(10,740, CANVAS_WIDTH - 20, CANVAS_HEIGHT * 0.05);
        container2.setFillColor(Color.LIGHT_GRAY);
        canvas.add(container2);

        TreeLayer = new GraphicsGroup();
        canvas.add(TreeLayer);

        inputNumber = new TextField();
        inputNumber.setPosition(CANVAS_WIDTH * 0.3, CANVAS_HEIGHT * 0.04);
        canvas.add(inputNumber);

        insertButton = new Button("Insert");
        insertButton.setPosition(inputNumber.getPosition().getX() + inputNumber.getWidth() + 20, CANVAS_HEIGHT * 0.039);
        canvas.add(insertButton);

        deleteButton = new Button("Delete");
        deleteButton.setPosition(insertButton.getPosition().getX() + insertButton.getWidth() + 20, CANVAS_HEIGHT * 0.039);
        canvas.add(deleteButton);

        clearAllButton = new Button("Clear");
        clearAllButton.setPosition(deleteButton.getPosition().getX() + deleteButton.getWidth() + 20, CANVAS_HEIGHT * 0.039);
        canvas.add(clearAllButton);

        preorderButton = new Button("Pre-order");
        preorderButton.setPosition(CANVAS_WIDTH * 0.35, CANVAS_HEIGHT * 0.93);
        canvas.add(preorderButton);

        inorderButton = new Button("In-order");
        inorderButton.setPosition(preorderButton.getPosition().getX() + preorderButton.getWidth() + 20, CANVAS_HEIGHT * 0.93);
        canvas.add(inorderButton);

        postOrderButton = new Button("Post-order");
        postOrderButton.setPosition(inorderButton.getPosition().getX() + inorderButton.getWidth() + 20, CANVAS_HEIGHT * 0.93);
        canvas.add(postOrderButton);

        preorderButton.onClick(() -> {
            canvasPrint = new CanvasWindow("Pre-Order", 600, 50);
            String preorderResult = tree.preorderTraversal();
            GraphicsText preorderText = new GraphicsText("Pre-order: " + preorderResult);
            preorderText.setPosition(10, 25); 
            canvasPrint.add(preorderText);
        });

        inorderButton.onClick(() -> {
            canvasPrint = new CanvasWindow("In-Order", 600, 50);
            String inorderResult = tree.inorderTraversal();
            GraphicsText inorderText = new GraphicsText("In-order: " + inorderResult);
            inorderText.setPosition(10, 25);
            canvasPrint.add(inorderText);
        });
        
        postOrderButton.onClick(() -> {
            canvasPrint = new CanvasWindow("Post-Order", 600, 50);
            String postorderResult = tree.postorderTraversal();
            GraphicsText postorderText = new GraphicsText("Post-order: " + postorderResult);
            postorderText.setPosition(10, 25);
            canvasPrint.add(postorderText);
        });
        
        insertButton.onClick(() -> {
            try {
                canvas.remove(TreeLayer);
                TreeLayer = new GraphicsGroup();
                canvas.add(TreeLayer);
                int valueToInsert = Integer.parseInt(inputNumber.getText());
                tree.insert(valueToInsert);
                tree.draw(TreeLayer, NODE_DIAMETER);
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        });

        deleteButton.onClick(() -> {
            try {
                canvas.remove(TreeLayer);
                TreeLayer = new GraphicsGroup();
                canvas.add(TreeLayer);
                int valueToDelete = Integer.parseInt(inputNumber.getText());

                if(tree.Search((valueToDelete))!= null){
                    tree.remove(tree.Search((valueToDelete)));}
                else {
                    System.out.println("Invalid input");
                }

                tree.draw(TreeLayer, NODE_DIAMETER);
            
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        });

        clearAllButton.onClick(() -> {
            try {
                while (tree.getSize() != 0) {
                    tree.remove(tree.getRoot());
                }
                canvas.remove(TreeLayer);
                TreeLayer = new GraphicsGroup();
                canvas.add(TreeLayer);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        });  
    }
}
