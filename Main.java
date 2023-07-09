public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.initiliseGrid();

        grid.breathFirstSearch(grid.getAllNodes(), new Node(new Coordinate(5, 5)), new Node(new Coordinate(9, 0)));
    }

}
