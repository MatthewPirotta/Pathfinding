public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.initiliseGrid();
        grid.displayGrid();

        grid.breathFirstSearch(grid.getAllNodes(), new Node(new Coordinate(2, 2)), new Node(new Coordinate(5, 5)));
        
        //System.out.println(grid.findNeighbours(grid.getAllNodes(), new Node(new Coordinate(2, 2))));
    }

}
