import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.AbstractMap;

public class Grid {
    private HashMap<Coordinate, Node> allNodes = new HashMap<>();
    private HashMap<Node, Node> cameFrom = new HashMap<>();
    private HashMap<Coordinate, Integer> costSoFar = new HashMap<>();
    private HashMap<Coordinate, Integer> nodeWeight = new HashMap<>();

    private PriorityQueue<AbstractMap.SimpleEntry<Node, Integer>> searchQueue = new PriorityQueue<>(Comparator.comparing(AbstractMap.SimpleEntry:: getValue));
    private ArrayList<Node> path = new ArrayList<>();

    private GridManager gridManager = GridManager.getInstance();
    private PathFinding pathFinder = new PathFinding(); // TODO SINGLETON? 

    public Grid(){
        initiliseGrid();
    }

    public void setupSearch(){
        pathFinder.breathFirstSearch(this, new Node(new Coordinate(0, 2)), new Node(new Coordinate(9, 2)));
    }


    public void initiliseGrid() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coord = new Coordinate(x, y);
                getAllNodes().put(coord, new Node(coord));
                getNodeWeight().put(coord, 1);
            }
        }
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


    public PriorityQueue<AbstractMap.SimpleEntry<Node, Integer>> getSearchQueue() {
        return searchQueue;
    }


    public HashMap<Coordinate, Integer> getCostSoFar() {
        return costSoFar;
    }


    public HashMap<Coordinate, Integer> getNodeWeight() {
        return nodeWeight;
    }

    

    // #endregion
}
