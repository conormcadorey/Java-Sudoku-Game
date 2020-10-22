package sudoku.problemdomain;

import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //store coordinate objects in a hashmap
    //using cooridnates as keys to keep track of different UI elements
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() !=o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    //hashcode = unique identifier generated from data it is given
    @Override
    public int hashCode() {
        return Objects.hash(x, y); //create the unique identifier
    }
}
