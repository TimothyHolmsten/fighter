package view;

import controller.MenuController;
import controller.WorldController;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.WorldModel;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldView extends BorderPane {
    private final WorldModel model;
    private Stage stage;
    private ImageView player1;
    private ImageView player2;

    public WorldView(Stage stage, WorldModel model) {
        this.model = model;
        this.stage = stage;

        initView();
    }

    private void initView() {
        FighterMenuBar fmb = new FighterMenuBar(stage, true);
        setTop(fmb);

        player1 = new ImageView();
        Image p1img = new Image("file:images/player.png", 32, 32, true, false);
        player1.setImage(p1img);
        player1.setX(model.getPlayer1().getX());
        player1.setY(model.getPlayer1().getY());

        player2 = new ImageView();
        Image p2img = new Image("file:images/player.png", 32, 32, true, false);
        player2.setImage(p2img);
        player2.setX(model.getPlayer2().getX());
        player2.setY(model.getPlayer2().getY());

        getChildren().addAll(player1,player2);
    }

    public void updateView() {
        player1.setX(model.getPlayer1().getX());
        player1.setY(model.getPlayer1().getY());

        player2.setX(model.getPlayer2().getX());
        player2.setY(model.getPlayer2().getY());

    }
}
