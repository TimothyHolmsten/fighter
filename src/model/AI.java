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
        boolean attacked = false;

        if(getY() <= enemyPlayer.getY() && Math.abs(diffX) < 5) {
            attack(500);
            attacked = true;
        }
        else if (getY() <= enemyPlayer.getY())
            walk(diffX);
        else if(Math.abs(diffX) > 10)
            walk( diffX - (2000/diffX) );

        if(Math.abs(diffX) < enemyPlayer.getWidth() && !attacked)
            jump(300);

    }
}
