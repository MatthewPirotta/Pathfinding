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

    private PriorityQueue<AbstractMap.SimpleEntry<Node, Integer>> searchQueue = new PriorityQueue<>(
            Comparator.comparing(AbstractMap.SimpleEntry::getValue));
    private ArrayList<Node> path = new ArrayList<>();

    private GridManager gridManager = GridManager.getInstance();
    private PathFinding pathFinder = new PathFinding(); // TODO SINGLETON?

    public Grid() {
        initiliseGrid();
    }

    public void setupSearch() {
        pathFinder.breathFirstSearch(this, new Node(new Coordinate(0, 8)), new Node(new Coordinate(8, 1)));
    }

    public void initiliseGrid() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coord = new Coordinate(x, y);
                getAllNodes().put(coord, new Node(coord));
                getNodeWeight().put(coord, 10);
            }
        }
        setupWalls();
        setupRoads();
    }

    private void setupWalls() {
        getAllNodes().remove(new Coordinate(5, 2));
        getAllNodes().remove(new Coordinate(6, 2));
        getAllNodes().remove(new Coordinate(7, 2));
        getAllNodes().remove(new Coordinate(8, 2));
        getAllNodes().remove(new Coordinate(9, 2));
        getAllNodes().remove(new Coordinate(9, 3));
        getAllNodes().remove(new Coordinate(9, 4));
        getAllNodes().remove(new Coordinate(9, 5));
        getAllNodes().remove(new Coordinate(9, 6));
        getAllNodes().remove(new Coordinate(9, 7));
        getAllNodes().remove(new Coordinate(9, 8));
        getAllNodes().remove(new Coordinate(8, 8));
        getAllNodes().remove(new Coordinate(7, 8));
        getAllNodes().remove(new Coordinate(6, 8));
        getAllNodes().remove(new Coordinate(5, 8));
        getAllNodes().remove(new Coordinate(4, 8));
        getAllNodes().remove(new Coordinate(3, 8));
        getAllNodes().remove(new Coordinate(2, 8));
        getAllNodes().remove(new Coordinate(1, 8));
    }

    private void setupRoads(){
        getNodeWeight().put(new Coordinate(0, 7), 5);
        getNodeWeight().put(new Coordinate(1, 7), 5);
        getNodeWeight().put(new Coordinate(2, 7), 5);
        getNodeWeight().put(new Coordinate(3, 7), 5);
        getNodeWeight().put(new Coordinate(4, 7), 5);
        getNodeWeight().put(new Coordinate(4, 6), 5);
        getNodeWeight().put(new Coordinate(4, 5), 5);
        getNodeWeight().put(new Coordinate(4, 4), 5);
        getNodeWeight().put(new Coordinate(4, 3), 5);
        getNodeWeight().put(new Coordinate(4, 2), 5);
        getNodeWeight().put(new Coordinate(4, 1), 5);
        getNodeWeight().put(new Coordinate(4, 0), 5);
        getNodeWeight().put(new Coordinate(5, 0), 5);
        getNodeWeight().put(new Coordinate(6, 0), 5);
        getNodeWeight().put(new Coordinate(7, 0), 5);
        getNodeWeight().put(new Coordinate(8, 0), 5);
        getNodeWeight().put(new Coordinate(9, 0), 5);

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
