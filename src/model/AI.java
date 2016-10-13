package model;

/**
 * Created by timothy on 2016-10-11.
 */
public class AI extends Player {
    private Player enemyPlayer;

    public AI(double x, double y, double width, double height, Player enemyPlayer) {
        super(x, y, width, height);
        this.enemyPlayer = enemyPlayer;
    }

    public void think() {
        double diffX = enemyPlayer.getX() - getX();

        if (getY() <= enemyPlayer.getY()) {
            walk(enemyPlayer.getX() - getX());
        }
        else if(Math.abs(diffX) > 10)
            walk( diffX - (1000/diffX) );




    }
}
