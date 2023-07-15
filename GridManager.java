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

    public void displayGrid(Grid grid, Node startNode, Node targetNode) {

        Coordinate coord = new Coordinate();
        String colour;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                coord.set(x, y);
                if (grid.getAllNodes().containsKey(coord)) {
                    colour = selectColour(grid, grid.getAllNodes().get(coord), startNode, targetNode);
                    System.out.print(colour + coord + "\u001B[37m"); //white
                    System.out.print("  ");
                }
                else{
                    colour = "\u001B[45m"; //purple
                    System.out.print(colour + coord+ "\u001B[40m"); //black
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
            return "\u001B[33m"; // yellow
        } else
            return "\u001B[37m"; // white
    }

}
