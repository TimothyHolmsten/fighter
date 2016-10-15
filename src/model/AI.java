package model;

/**
 * AI is a player controlled by the computer. The <code>think</code>
 * method should be called every frame.
 */
public class AI extends Player {
    private Player enemyPlayer;

    /**
     * Creates a new AI with specified position and size.
     * @param x x position of the AI player
     * @param y y position of the AI player
     * @param width the width of the AI player
     * @param height the height of the AI player
     * @param enemyPlayer the enemy player
     */
    public AI(double x, double y, double width, double height, Player enemyPlayer) {
        super(x, y, width, height);
        this.enemyPlayer = enemyPlayer;
    }

    /**
     * Creates a new AI with specified position and size.
     * @param x x position of the AI player
     * @param y y position of the AI player
     * @param width the width of the AI player
     * @param height the height of the AI player
     */
    public AI(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * Makes the AI think. This should be called every frame.
     */
    public void think() {
        if (enemyPlayer != null) {
            double diffX = enemyPlayer.getX() - getX();
            boolean attacked = false;

            if (getY() <= enemyPlayer.getY() && Math.abs(diffX) < 5) {
                attack(500);
                attacked = true;
            } else if (getY() <= enemyPlayer.getY())
                walk(diffX);
            else if (Math.abs(diffX) > 10)
                walk(diffX - (2000 / diffX));

            if (Math.abs(diffX) < enemyPlayer.getWidth() && !attacked)
                jump(300);
        }

    }

    /**
     * Sets the enemy player that is used in the <code>think</code>
     * method.
     * @param enemyPlayer the enemy player
    */
    public void setEnemyPlayer(Player enemyPlayer) {
    this.enemyPlayer = enemyPlayer; } }
