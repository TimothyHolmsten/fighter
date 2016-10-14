package model;

import java.lang.Math;

/**
 * Created by timothy on 2016-10-11.
 */
public class Player {
    private double x, y;
    private double width, height;
    private double dx, dy;
    private long lastJump = 0;
    private long lastAttack = 0;
    private int score = 0;

    public Player(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void walk(double dx) {
        this.dx = dx;
    }

    public void jump(double dy) {
        if (System.currentTimeMillis() - lastJump > 200) {
            this.dy = -dy * y / 500;
            lastJump = System.currentTimeMillis();
        }

    }

    public void attack(double attackPower) {
        if (System.currentTimeMillis() - lastAttack > 500) {
            dy = attackPower;
            lastAttack = System.currentTimeMillis();
        }
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
        y += dy * elapsedTimeNs / 1_000_000_000.0;
    }

    public void gravity(long elapsedTimeNs) {
        dy += 900 * elapsedTimeNs / 1_000_000_000.0;
    }

    public void constrain(double boxX, double boxY) {
        if (x > boxX - width)
            x = boxX - width;
        else if (x < 0)
            x = 0;

        if (y > boxY - height * 2) {
            dy = 0;
            y = boxY - height * 2;
        }
    }

    public boolean onTopOfPlayer(Player otherPlayer) {
        if (x > otherPlayer.getX() - width &&
                x < otherPlayer.getX() + width &&
                Math.abs(y - otherPlayer.getY()) > height / 2 &&
                y < otherPlayer.getY())
            return true;
        return false;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
