import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%2d,%2d", x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
        if (!(o instanceof Coordinate)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Coordinate coord = (Coordinate) o;

        return ((getX() == coord.getX()) && (getY() == coord.getY()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

}