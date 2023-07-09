import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    HashMap<Coordinate, Node> allNodes = new HashMap<Coordinate, Node>();

    public void initiliseGrid() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coord = new Coordinate(x, y);
                allNodes.put(coord, new Node(coord));
            }
        }
    }

    public void displayGrid(HashMap<Coordinate, Node> allNodes, HashMap<Node, Node> cameFrom, ArrayList<Node> path,
            Node startNode,
            Node targetNode) {

        Coordinate coord = new Coordinate();
        Node node = new Node();

        String colour;

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                coord.set(x, y);
                if (allNodes.containsKey(coord)) {
                    colour = selectColour(cameFrom, path, allNodes.get(coord), startNode, targetNode);
                    System.out.print(colour + coord + "\u001B[37m");
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
    }

    private String selectColour(HashMap<Node, Node> cameFrom, ArrayList<Node> path, Node node, Node startNode,
            Node targetNode) {
        if (node.equals(startNode)) {
            return "\u001B[32m"; // green
        } else if (node.equals(targetNode)) {
            return "\u001B[31m"; // red
        } else if (path.contains(node)) {
            return "\u001B[34m"; // blue
        } else if (cameFrom.containsValue(node)) {
            return "\u001B[30m"; // blue
        } else
            return "\u001B[37m"; // white
    }

    private ArrayList<Node> findNeighbours(HashMap<Coordinate, Node> allNodes, Node searchNode,
            HashMap<Node, Node> cameFrom) {
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
            if (allNodes.containsKey(neighbourCoordinate)) {
                foundNeighbour = allNodes.get(neighbourCoordinate);
                if (cameFrom.containsKey(foundNeighbour)) {
                    continue;
                }

                foundNeighbours.add(foundNeighbour);
                cameFrom.put(foundNeighbour, searchNode);
            }
        }
        return foundNeighbours;
    }

    public void breathFirstSearch(HashMap<Coordinate, Node> allNodes, Node startNode, Node targetNode) {
        Queue<Node> searchQueue = new LinkedList<Node>();
        searchQueue.add(startNode);

        HashMap<Node, Node> cameFrom = new HashMap<>();
        cameFrom.put(startNode, null);

        Node searchNode;

        while (!searchQueue.isEmpty()) {

            searchNode = searchQueue.remove();

            if (searchNode.equals(targetNode))
                break;

            searchQueue.addAll(findNeighbours(allNodes, searchNode, cameFrom));
        }

        ArrayList<Node> path = generatePath(targetNode, startNode, cameFrom);
        displayGrid(allNodes, cameFrom, path, startNode, targetNode);
    }

    private ArrayList<Node> generatePath(Node targetNode, Node startNode, HashMap<Node, Node> cameFrom) {
        Node currentNode = targetNode;
        ArrayList<Node> path = new ArrayList<>();

        while (!currentNode.equals(startNode)) {
            path.add(currentNode);
            currentNode = cameFrom.get(currentNode);
        }

        path.add(startNode);
        Collections.reverse(path);

        return path;
    }

    // #region
    public HashMap<Coordinate, Node> getAllNodes() {
        return allNodes;
    }

    // #endregion
}
