import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends GraphicsGroup {

    private static int CANVAS_WIDTH = 800;
    private static int CANVAS_HEIGHT = 600;
    private TextField inputNumber;
    static RedBlackTree tree;
    private Button insertButton;
    private Button deleteButton;
    private CanvasWindow canvas;
    private List<GraphicsText> textElements;
    private List<NodeEllipse> ellipseElements;
    private List<Line> connectionLines;
    private Node node;
    private NodeEllipse nodeEllipse;

    public MainUI() {

        canvas = new CanvasWindow("Red Black Tree", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.draw();

        inputNumber = new TextField();
        inputNumber.setPosition(CANVAS_WIDTH * 0.015, CANVAS_HEIGHT * 0.02);
        canvas.add(inputNumber);

        insertButton = new Button("Insert");
        insertButton.setPosition(CANVAS_WIDTH * 0.1375, CANVAS_HEIGHT * 0.02);
        canvas.add(insertButton);

        deleteButton = new Button("Delete");
        deleteButton.setPosition(CANVAS_WIDTH * 0.225, CANVAS_HEIGHT * 0.02);
        canvas.add(deleteButton);

        textElements = new ArrayList<>();
        ellipseElements = new ArrayList<>();
        connectionLines = new ArrayList<>();

        insertButton.onClick(() -> {
            try {
     
                int valueToInsert = Integer.parseInt(inputNumber.getText());
                createEllipseWithValue(valueToInsert);
                    // if(ellipseElements.isEmpty()){
                    //     tree = new RedBlackTree(valueToInsert);
                    // } else{
                    //     tree.insert(valueToInsert);
                    //     tree.inorder(tree.getRoot());
                    // }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        });

        deleteButton.onClick(() -> {
            try {
                int valueToDelete = Integer.parseInt(inputNumber.getText());
                removeEllipseWithValue(valueToDelete);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (IllegalArgumentException e) {
                System.out.println("Integer not found");
            }
        });
    }

    private void createEllipseWithValue(int value) {
        GraphicsText displayText = new GraphicsText(Integer.toString(value));
        displayText.setFontSize(24);
        displayText.setFillColor(Color.WHITE);
        displayText.setCenter((CANVAS_WIDTH*0.5)+size()*60, 150 + size() * 75);

        node = new Node(value);
        nodeEllipse = new NodeEllipse(node, ellipseElements.size());
        nodeEllipse.setFillColor(Color.RED);
        nodeEllipse.setStrokeColor(Color.BLACK);
        nodeEllipse.setCenter((CANVAS_WIDTH*0.5)+size()*60, 150 + size() * 75);

        canvas.add(nodeEllipse);
        canvas.add(displayText);
        textElements.add(displayText);
        ellipseElements.add(nodeEllipse);

        connectElements();
        canvas.draw();
    }

    private void removeEllipseWithValue(int valueToDelete) {
        boolean found = false;
        for (int i = 0; i < size(); i++) {
            GraphicsText text = textElements.get(i);
            Ellipse ellipse = ellipseElements.get(i);
            if (text.getText().equals(Integer.toString(valueToDelete))) {
                remove(text);
                remove(ellipse);
                textElements.remove(i);
                ellipseElements.remove(i);
                found = true;
                connectElements();
                canvas.draw();
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Integer not found");
        }
    }

    private void connectElements() {
        if (size() < 2) {
            return;
        }

        clearConnectionLines();

        for (int i = 0; i < size() - 1; i++) {
            NodeEllipse startEllipse = ellipseElements.get(i);
            NodeEllipse endEllipse = ellipseElements.get(i + 1);

            double startX = startEllipse.getCenter().getX(); 
            double startY = startEllipse.getCenter().getY() + (startEllipse.getHeight() / 2);
            Point startPoint = new Point(startX, startY);
        

            double endX = endEllipse.getCenter().getX(); 
            double endY = endEllipse.getCenter().getY() - (endEllipse.getHeight() / 2);      
            Point endPoint = new Point(endX, endY);

            Line connectionLine = new Line(startPoint, endPoint);
            connectionLine.setStrokeColor(Color.BLACK);
            connectionLine.setStrokeWidth(2);
            connectionLines.add(connectionLine);
            canvas.add(connectionLine);
        }
    }

    private void clearConnectionLines() {
        for (Line line : connectionLines) {
            canvas.remove(line);
        }
        connectionLines.clear();
    }

    private int size() {
        return ellipseElements.size();
    }

    public static void main(String[] args) {

        new MainUI();



        // RedBlackTree tree1 = new RedBlackTree(10);
        // tree1.insert(15);
        // tree1.insert(5);
        // tree1.insert(20);
        // tree1.remove(15);
        // tree1.inorder(tree1.getRoot());
//     }

// }
    }


}
