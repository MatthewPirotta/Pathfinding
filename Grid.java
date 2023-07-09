import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    HashMap<Coordinate, Node> allNodes = new HashMap<Coordinate, Node>();
    Node startNode;
    Node endNode;

    public void initiliseGrid() {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coord = new Coordinate(x, y);
                allNodes.put(coord, new Node(coord));
            }
        }
    }

    public void displayGrid() {
        /*
         * int prevX =0;
         * for (Node node : allNodes.values()) {
         * 
         * if(prevX != node.getCoordinate().getX()){
         * System.out.println("");
         * prevX = node.getCoordinate().getX();
         * }
         * 
         * System.out.print(node.getCoordinate().getX() + "," +
         * node.getCoordinate().getY());
         * System.out.print("");
         * }
         */

        Coordinate coord = new Coordinate();
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                coord.set(x, y);
                if (allNodes.containsKey(coord)) {
                    System.out.print(coord);
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
    }

    public ArrayList<Node> findNeighbours(HashMap<Coordinate, Node> allNodes, Node searchNode,
            HashMap<Node, Node> cameFrom) {
        Coordinate searchCoordinate = searchNode.getCoordinate();
        ArrayList<Node> foundNeighbours = new ArrayList<>();
        Node foundNeighbour;

        ArrayList<Coordinate> directions = new ArrayList<Coordinate>() {
            {
                add(new Coordinate(searchCoordinate.getX(), searchCoordinate.getY() + 1));
                add(new Coordinate(searchCoordinate.getX() + 1, searchCoordinate.getY()));
                add(new Coordinate(-searchCoordinate.getX(), searchCoordinate.getY() - 1));
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

        System.out.println(generatePath(targetNode, startNode, cameFrom));
    }

    public ArrayList<Node> generatePath(Node targetNode, Node startNode, HashMap<Node, Node> cameFrom){
        Node currentNode = targetNode;
        ArrayList<Node> path = new ArrayList<>();

        while(!currentNode.equals(startNode)){
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
