import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;
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
   private CanvasWindow canvas;


   public MainUI() {
       tree = new RedBlackTree();
       initialize();
   }


   //THE INITIALIZER IS THE TRIGGER FOR THE MAIN METHOD ---SEE MAIN.JAVA--- THEREFORE YOU GOTTA NEED IT
   private void initialize() {


       canvas = new CanvasWindow("Red-Black Tree Visualizer", CANVAS_WIDTH, CANVAS_HEIGHT);
       canvas.draw();


       TreeLayer = new GraphicsGroup();
       canvas.add(TreeLayer);


       inputNumber = new TextField();
       inputNumber.setPosition(CANVAS_WIDTH * 0.015, CANVAS_HEIGHT * 0.02);
       canvas.add(inputNumber);


       insertButton = new Button("Insert");
       insertButton.setPosition(CANVAS_WIDTH * 0.1375, CANVAS_HEIGHT * 0.02);
       canvas.add(insertButton);


       deleteButton = new Button("Delete");
       deleteButton.setPosition(CANVAS_WIDTH * 0.225, CANVAS_HEIGHT * 0.02);
       canvas.add(deleteButton);


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




   }


}
