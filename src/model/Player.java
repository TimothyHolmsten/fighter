package model;

/**
 * Created by timothy on 2016-10-11.
 */
public class Player {
    public static final double BILLION = 1_000_000_000.0;
    private double x, y;
    private double dx, dy;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void walk(double dx) {
        this.dx = dx;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDy() {
        return dy;
    }

    public double getDx() {
        return dx;
    }

    public void move(long elapsedTimeNs) {
        x += dx * elapsedTimeNs / BILLION;

        if(dx != 0)
            if(dx < 0)
                dx -= dx/10;
            else if(dx > 0)
                dx -= dx/10;
    }
}
