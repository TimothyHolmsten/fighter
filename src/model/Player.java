package model;

/**
 * Created by timothy on 2016-10-11.
 */
public class Player {
    private double x, y;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void walk(double x) {
        this.x += x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
