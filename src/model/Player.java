package model;

/**
 * <code>Player</code> is a player in the game.
 */
public class Player {
    private double x, y;
    private double width, height;
    private double dx, dy;
    private long lastJump = 0;
    private long lastAttack = 0;
    private int score = 0;

    /**
     * Creates a new player with specified position and size.
     * @param x x position of the player
     * @param y y position of the player
     * @param width the width of the player
     * @param height the height of the player
     */
    public Player(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the player's walking speed.
     * @param speed the speed that the player will walk at
     */
    public void walk(double speed) {
        this.dx = speed;
    }

    /**
     * Makes the player jump at the specified speed.
     * @param speed the upward speed that the player will jump at
     */
    public void jump(double speed) {
        if (System.currentTimeMillis() - lastJump > 200) {
            this.dy = -speed * y / 500;
            lastJump = System.currentTimeMillis();
        }

    }

    /**
     * Makes the player dive down with the specified attack power. No
     * more than two attacks per second can be performed.
     * @param attackPower the dive speed
     */
    public void attack(double attackPower) {
        if (System.currentTimeMillis() - lastAttack > 500) {
            dy = attackPower;
            lastAttack = System.currentTimeMillis();
        }
    }

    /**
     * Returns the x position of the player.
     * @return the x position of the player
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y position of the player.
     * @return the y position of the player
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the y speed of the player.
     * @return the y speed of the player
     */
    public double getDy() {
        return dy;
    }

    /**
     * Returns the x speed of the player.
     * @return the x speed of the player
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the total speed of the player.
     * @return the total speed of the player
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Moves the player based on its velocity.
     * @param elapsedTimeNs the time in nanoseconds since last time
     * this method was called
     */
    public void move(long elapsedTimeNs) {
        x += dx * elapsedTimeNs / 1_000_000_000.0;
        y += dy * elapsedTimeNs / 1_000_000_000.0;
    }

    /**
     * Increase the downward speed of the player.
     * @param elapsedTimeNs the time in nanoseconds since last time
     * this method was called
     */
    public void gravity(long elapsedTimeNs) {
        dy += 900 * elapsedTimeNs / 1_000_000_000.0;
    }

    /**
     * Makes sure the player is inside a box with the specified width
     * and height.
     * @param boxX the width of the box
     * @param boxY the height of the box
     */
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

    /**
     * Checks whether the player is on top of the other player.
     * @param otherPlayer the other player
     * @return true if the player is on top of the other player,
     * otherwise false
     */
    public boolean onTopOfPlayer(Player otherPlayer) {
        if (x > otherPlayer.getX() - width &&
                x < otherPlayer.getX() + width &&
                Math.abs(y - otherPlayer.getY()) > height / 2 &&
                y < otherPlayer.getY())
            return true;
        return false;
    }

    /**
     * Returns the width of the player.
     * @return the width of the player
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the player.
     * @return the height of the player
     */
    public double getHeight() {
        return height;
    }

    /**
     * Increases the player's score.
     * @param score the score to add
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Returns the player's score.
     * @return the player's score
     */
    public int getScore() {
        return score;
    }
}
