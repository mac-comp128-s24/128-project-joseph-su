import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;


public class MainUI {
   private final RedBlackTree tree;
   private static int CANVAS_WIDTH = 1000;
   private static int CANVAS_HEIGHT = 800;
   private static int NODE_DIAMETER = 50;


   private GraphicsGroup TreeLayer;
   private TextField inputNumber;
   private Button insertButton;
   private Button deleteButton;
   private Button clearAllButton;
   private CanvasWindow canvas;


   public MainUI() {
       tree = new RedBlackTree();
       initialize();
   }


   //THE INITIALIZER IS THE TRIGGER FOR THE MAIN METHOD ---SEE MAIN.JAVA--- THEREFORE YOU GOTTA NEED IT
   private void initialize() {




       canvas = new CanvasWindow("Red-Black Tree Visualizer", CANVAS_WIDTH, CANVAS_HEIGHT);
       canvas.draw();

       Rectangle container = new Rectangle(10,25, CANVAS_WIDTH - 20, CANVAS_HEIGHT * 0.05);
       container.setFillColor(Color.LIGHT_GRAY);
       canvas.add(container);

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


    insertButton.onClick(() -> {
           try {
               canvas.remove(TreeLayer);
               TreeLayer = new GraphicsGroup();
               canvas.add(TreeLayer);
               int valueToInsert = Integer.parseInt(inputNumber.getText());
               tree.insert(valueToInsert);
               tree.drawTree(TreeLayer, NODE_DIAMETER);
              
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

            tree.drawTree(TreeLayer, NODE_DIAMETER);
           
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
