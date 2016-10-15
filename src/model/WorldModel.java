package model;

import java.util.ArrayList;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldModel {
    public int timeLeft;
    private Player player1;
    private Player player2;
    private long endTime;

    public WorldModel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        endTime = System.currentTimeMillis() + 60 * 1000;
        timeLeft = (int) (endTime - System.currentTimeMillis());
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        return players;
    }

    public void play(long time, double boxX, double boxY) {

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
    }
}
