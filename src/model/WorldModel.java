package model;

import java.util.ArrayList;

/**
 * This class represents the model of a game world. The {@link #play}
 * method should be called every frame.
 */
public class WorldModel {
    /** The time left before the game ends. */
    public int timeLeft;

    private Player player1;
    private Player player2;
    private long endTime;
    private State state = State.RUNNING;

    /**
     * Constructs a new world model containing the two specified
     * players.
     * @param player1 the first player
     * @param player2 the second player
     */
    public WorldModel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        endTime = System.currentTimeMillis() + 60 * 1000;
        timeLeft = (int) (endTime - System.currentTimeMillis());
    }

    /**
     * Returns the first player.
     * @return the first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Returns the second player.
     * @return the second player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Returns a list of all players.
     * @return a list of all players
     */
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        return players;
    }

    /**
     * Returns the state of the game.
     * @return the state of the game
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the game.
     * @param state the new state of the game
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Updates the game. This method should be called every frame.
     * @param time the elapsed time since the last time this method
     * was called
     * @param boxX the width of the game world
     * @param boxY the height of the game world
     */
    public void play(long time, double boxX, double boxY) {
        if (getState() == State.RUNNING) {
            timeLeft = (int) (endTime - System.currentTimeMillis());

            for (Player p : getPlayers()) {
                p.move(time);
                p.gravity(time);
                p.constrain(boxX, boxY);
                if (p instanceof AI)
                    ((AI) p).think();
            }

            if (Math.abs(player1.getX() - player2.getX()) < 16
                    && Math.abs(player1.getY() - player2.getY()) < 32) {
                double p1Speed = player1.getSpeed();
                double p2Speed = player2.getSpeed();
                if (p1Speed > 10 || p2Speed > 10) {
                    if (player1.onTopOfPlayer(player2) && player1.getDy() > player2.getDy()) {
                        player1.addScore(1);
                        player1.jump(1000);
                        player2.attack(1000);
                    } else if (player2.onTopOfPlayer(player1) && player2.getDy() > player1.getDy()) {
                        player2.addScore(1);
                        player2.jump(1000);
                        player1.attack(1000);
                    }
                }
            }
        } else {
            endTime = System.currentTimeMillis() + timeLeft;
        }
    }
}
