import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GridManager {

    // Singleton
    private static GridManager instance = null;

    // Singleton manager
    public static GridManager getInstance() {
        if (instance == null) {
            instance = new GridManager();
        }
        return instance;
    }

    public void initiliseGrid(Grid grid) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coord = new Coordinate(x, y);
                grid.getAllNodes().put(coord, new Node(coord));
            }
        }
    }

    public void displayGrid(Grid grid, Node startNode, Node targetNode) {

        Coordinate coord = new Coordinate();
        String colour;

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                coord.set(x, y);
                if (grid.getAllNodes().containsKey(coord)) {
                    colour = selectColour(grid, grid.getAllNodes().get(coord), startNode, targetNode);
                    System.out.print(colour + coord + "\u001B[37m");
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
    }

    private String selectColour(Grid grid, Node node, Node startNode, Node targetNode) {
        if (node.equals(startNode)) {
            return "\u001B[32m"; // green
        } else if (node.equals(targetNode)) {
            return "\u001B[31m"; // red
        } else if (grid.getPath().contains(node)) {
            return "\u001B[34m"; // blue
        } else if (grid.getCameFrom().containsValue(node)) {
            return "\u001B[30m"; // blue
        } else
            return "\u001B[37m"; // white
    }

    private ArrayList<Node> getNeighbours(Grid grid, Node searchNode) {
        Coordinate searchCoordinate = searchNode.getCoordinate();
        ArrayList<Node> foundNeighbours = new ArrayList<>();
        Node foundNeighbour;

        ArrayList<Coordinate> directions = new ArrayList<Coordinate>() {
            {
                add(new Coordinate(searchCoordinate.getX(), searchCoordinate.getY() + 1));
                add(new Coordinate(searchCoordinate.getX() + 1, searchCoordinate.getY()));
                add(new Coordinate(searchCoordinate.getX(), searchCoordinate.getY() - 1));
                add(new Coordinate(searchCoordinate.getX() - 1, searchCoordinate.getY()));
            }
        };

        for (Coordinate neighbourCoordinate : directions) {
            if (grid.getAllNodes().containsKey(neighbourCoordinate)) {
                foundNeighbour = grid.getAllNodes().get(neighbourCoordinate);
                if (grid.getCameFrom().containsKey(foundNeighbour)) {
                    continue;
                }

                foundNeighbours.add(foundNeighbour);
                grid.getCameFrom().put(foundNeighbour, searchNode);
            }
        }
        return foundNeighbours;
    }

    public void breathFirstSearch(Grid grid, Node startNode, Node targetNode) {
        Queue<Node> searchQueue = new LinkedList<Node>();
        searchQueue.add(startNode);

        grid.getCameFrom().put(startNode, null);

        Node searchNode;

        while (!searchQueue.isEmpty()) {

            searchNode = searchQueue.remove();

            if (searchNode.equals(targetNode))
                break;

            searchQueue.addAll(getNeighbours(grid, searchNode));
        }

        grid.setPath(generatePath(grid, targetNode, startNode));
        displayGrid(grid, startNode, targetNode);
    }

    private ArrayList<Node> generatePath(Grid grid, Node targetNode, Node startNode){
        Node currentNode = targetNode;

        while (!currentNode.equals(startNode)) {
            grid.getPath().add(currentNode);
            currentNode = grid.getCameFrom().get(currentNode);
        }

        grid.getPath().add(startNode);
        Collections.reverse(grid.getPath());

        return grid.getPath();
    }

    
}
