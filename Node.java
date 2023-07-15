import java.util.Objects;

public class Node {
    private Coordinate coordinate;

    public Node() {

    }

    public Node(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return ("coordinate=" + coordinate);
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        /*
         * Check if o is an instance of Complex or not
         * "null instanceof [type]" also returns false
         */
        if (!(o instanceof Node)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Node node = (Node) o;

        return (getCoordinate().equals(node.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordinate());
    }

    // #region
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    // #endregion

}