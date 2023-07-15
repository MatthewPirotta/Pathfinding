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
        Node node = new Node();
        String textColour, backgroundColour;
        String resetTextColour = "\u001B[47m";
        String resestColour = "\u001B[0m";

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                coord.set(x, y);
                if (grid.getAllNodes().containsKey(coord)) {
                    node = grid.getAllNodes().get(coord);

                    textColour = selectTextColour(grid, node, startNode, targetNode);
                    backgroundColour = selectBackgroundColour(grid, coord);

                    System.out.print(textColour + backgroundColour + coord + resestColour); // white
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
    }

    private String selectTextColour(Grid grid, Node node, Node startNode, Node targetNode) {
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

    private String selectBackgroundColour(Grid grid, Coordinate coordinate) {
        if (!grid.getAllNodes().containsKey(coordinate)) {
            return "\u001B[45m"; // purple
        } else if (grid.getNodeWeight().get(coordinate) == 5) { //road
            return "\u001B[47m"; // Cyan
        } else {
            return "\u001B[40m"; // black
        }

    }

}
