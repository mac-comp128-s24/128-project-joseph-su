import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends GraphicsGroup {
    private TextField inputNumber;
    private Button insertButton;
    private Button deleteButton;
    private CanvasWindow canvas;
    private List<GraphicsText> textElements;
    private List<Ellipse> ellipseElements;
    private List<Line> connectionLines;

    public MainUI(CanvasWindow canvas) {
        this.canvas = canvas;
        inputNumber = new TextField();
        inputNumber.setPosition(12, 12);
        canvas.add(inputNumber);

        insertButton = new Button("Insert");
        insertButton.setPosition(110, 12);
        canvas.add(insertButton);

        deleteButton = new Button("Delete");
        deleteButton.setPosition(180, 12);
        canvas.add(deleteButton);

        textElements = new ArrayList<>();
        ellipseElements = new ArrayList<>();
        connectionLines = new ArrayList<>();

        insertButton.onClick(() -> {
            try {
                int valueToInsert = Integer.parseInt(inputNumber.getText());
                createEllipseWithValue(valueToInsert);
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
        displayText.setCenter(50+size()*10, 150 + size() * 50);

        Ellipse ellipse = new Ellipse(0, 0, 50, 50);
        ellipse.setFillColor(Color.RED);
        ellipse.setStrokeColor(Color.BLACK);
        ellipse.setCenter(50+size()*10, 150 + size() * 50);

        add(ellipse);
        add(displayText);
        textElements.add(displayText);
        ellipseElements.add(ellipse);

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
            Ellipse startEllipse = ellipseElements.get(i);
            Ellipse endEllipse = ellipseElements.get(i + 1);
            Point startPoint = startEllipse.getCenter();
            Point endPoint = endEllipse.getCenter();
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
        CanvasWindow canvas = new CanvasWindow("Red Black Tree", 800, 600);
        MainUI mainUI = new MainUI(canvas);
        canvas.add(mainUI);
        canvas.draw();



//         // RedBlackTree tree1 = new RedBlackTree(10);
//         // tree1.insert(15);
//         // tree1.insert(5);
//         // tree1.insert(20);
//         // tree1.remove(15);
//         // tree1.inorder(tree1.getRoot());
//     }
// }
    }


}
