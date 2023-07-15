import java.util.ArrayList;
import java.util.Collections;
import java.util.AbstractMap;

public class PathFinding {

    GridManager gridManager = GridManager.getInstance();

    private void addNeighboursToFrontier(Grid grid, Node searchNode, Node endNode) {
        Coordinate searchCoordinate = searchNode.getCoordinate();
        Node foundNeighbour;

        ArrayList<Coordinate> directions = new ArrayList<Coordinate>() {
            {
                add(new Coordinate(searchCoordinate.getX(), searchCoordinate.getY()-1)); //N
                add(new Coordinate(searchCoordinate.getX()+1, searchCoordinate.getY())); //E
                add(new Coordinate(searchCoordinate.getX(), searchCoordinate.getY()+1)); //S
                add(new Coordinate(searchCoordinate.getX()-1, searchCoordinate.getY())); //W
            }
        };

        int newCost;
        int priority;
        for (Coordinate neighbourCoordinate : directions) {
            if (grid.getAllNodes().containsKey(neighbourCoordinate)) {
                foundNeighbour = grid.getAllNodes().get(neighbourCoordinate);
                if (grid.getCameFrom().containsKey(foundNeighbour)) {
                    continue;
                }

                newCost = grid.getCostSoFar().get(searchNode.getCoordinate())
                        + grid.getNodeWeight().get(neighbourCoordinate);
                if (!grid.getCostSoFar().containsKey(neighbourCoordinate)
                        || newCost < grid.getCostSoFar().get(neighbourCoordinate)) {

                    priority = newCost + heuristic(endNode.getCoordinate(), neighbourCoordinate);
                    grid.getCostSoFar().put(neighbourCoordinate, priority);
                    grid.getSearchQueue().add(new AbstractMap.SimpleEntry<>(foundNeighbour, newCost));
                    grid.getCameFrom().put(foundNeighbour, searchNode);
                }

            }
        }
    }

    public void breathFirstSearch(Grid grid, Node startNode, Node targetNode) {
        grid.getSearchQueue().add(new AbstractMap.SimpleEntry<>(startNode, 0));

        grid.getCameFrom().put(startNode, null);
        grid.getCostSoFar().put(startNode.getCoordinate(), 0);

        Node searchNode;

        while (!grid.getSearchQueue().isEmpty()) {

            searchNode = grid.getSearchQueue().remove().getKey();

            if (searchNode.equals(targetNode))
                break;

            addNeighboursToFrontier(grid, searchNode, targetNode);
        }

        grid.setPath(generatePath(grid, targetNode, startNode));
        gridManager.displayGrid(grid, startNode, targetNode);
    }

    private ArrayList<Node> generatePath(Grid grid, Node endNode, Node startNode) {
        Node currentNode = endNode;

        while (!currentNode.equals(startNode)) {
            grid.getPath().add(currentNode);
            currentNode = grid.getCameFrom().get(currentNode);
        }

        grid.getPath().add(startNode);
        Collections.reverse(grid.getPath());

        return grid.getPath();
    }

    int heuristic(Coordinate a, Coordinate b) {
        // Manhattan distance on a square grid
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}