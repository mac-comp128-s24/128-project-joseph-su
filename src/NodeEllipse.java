import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

public class NodeEllipse extends Ellipse {

    Node node;
    private static final double RADIUS = 25.0; 

    public NodeEllipse(Node node, int numElements){
        super(0,0, RADIUS * 2, RADIUS * 2);
        this.node = node;
    }

    public void setPosition(){
        //sets the position based on its level and whether it is the left child or right child
        // if left child -x, if right child +x
        // if more than one, every two elements become a level
        // each level, there are n ^ 2 children where n is the order (2)
        

    }

    public double getRadius(){
        return RADIUS;
    }


    
}
