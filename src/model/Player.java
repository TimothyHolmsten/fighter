package model;

import java.lang.Math;

/**
 * Created by timothy on 2016-10-11.
 */
public class Player {
    private double x, y;
    private double dx, dy;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void walk(double dx) {
        this.dx = dx;
    }

    public void jump(double dy) {
        this.dy = -dy;
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

    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void move(long elapsedTimeNs) {
        x += dx * elapsedTimeNs / 1_000_000_000.0;
    }

    public void gravity(long elapsedTimeNs) {
        y += 10 * elapsedTimeNs / 1_000_000_000.0;
    }

    public void constrain(double boxX, double boxY, double width, double height) {
        if (x > boxX - width)
            x = boxX - width;
        else if (x < 0)
            x = 0;

        if (y > boxY - height * 2)
            y = boxY - height * 2;
    }
}
