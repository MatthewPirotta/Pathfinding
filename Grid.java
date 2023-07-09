import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    private HashMap<Coordinate, Node> allNodes = new HashMap<>();
    private HashMap<Node, Node> cameFrom = new HashMap<>();
    private ArrayList<Node> path = new ArrayList<>();

    private GridManager gridManager = GridManager.getInstance();

    public void setupSearch(){
        gridManager.initiliseGrid(this);
        gridManager.breathFirstSearch(this, new Node(new Coordinate(5, 5)), new Node(new Coordinate(9, 0)));
    }

    // #region Getters & Setters

    public HashMap<Coordinate, Node> getAllNodes() {
        return allNodes;
    }

    public HashMap<Node, Node> getCameFrom() {
        return cameFrom;
    }
    
    public ArrayList<Node> getPath() {
        return path;
    }

    public void setPath(ArrayList<Node> path) {
        this.path = path;
    }

    // #endregion
}
