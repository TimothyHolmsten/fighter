package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;

public class FighterMenuBar extends MenuBar {
    public FighterMenuBar() {
        Menu gameMenu = new Menu("Game");

        MenuItem MIPvP = new MenuItem("Start Player vs Player");
        MenuItem MIPvAI = new MenuItem("Start Player vs AI");
        MenuItem MIPause = new MenuItem("Pause");
        MenuItem MIHighscore = new MenuItem("Show highscore");
        MenuItem MIExit = new MenuItem("Exit");

        gameMenu.getItems().addAll(MIPvP, MIPvAI, MIPause, MIHighscore, MIExit);

        Menu helpMenu = new Menu("Help");
        MenuItem MIHow = new MenuItem("How to play");
        helpMenu.getItems().addAll(MIHow);

        getMenus().addAll(gameMenu, helpMenu);
    }
}
