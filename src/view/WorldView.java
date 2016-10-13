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
import javafx.scene.control.Label;
import model.WorldModel;
import model.Player;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldView extends BorderPane {
    private final WorldModel model;
    private Stage stage;
    private ImageView player1;
    private ImageView player2;
    private int p1Score = -1;
    private Label p1ScoreLabel;
    private int p2Score = -1;
    private Label p2ScoreLabel;

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
        Player p1 = model.getPlayer1();
        Player p2 = model.getPlayer2();

        player1.setX(p1.getX());
        player1.setY(p1.getY());

        player2.setX(p2.getX());
        player2.setY(p2.getY());

        if (p1.getScore() != p1Score) {
            p1ScoreLabel = new Label("Player 1: " + p1.getScore());
            p1ScoreLabel.setLayoutX(50);
            p1ScoreLabel.setLayoutY(50);
            getChildren().add(p1ScoreLabel);
            p1Score = p1.getScore();
        }
        if (p2.getScore() != p2Score) {
            p2ScoreLabel = new Label("Player 2: " + p2.getScore());
            p2ScoreLabel.setLayoutX(50);
            p2ScoreLabel.setLayoutY(100);
            getChildren().add(p2ScoreLabel);
            p2Score = p2.getScore();
        }
    }
}
