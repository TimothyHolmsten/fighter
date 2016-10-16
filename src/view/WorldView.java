package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;
import model.WorldModel;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldView extends BorderPane {
    private final WorldModel model;
    private Stage stage;
    private AnchorPane anchorPane;
    private ImageView player1;
    private ImageView player2;
    private int p1Score = -1;
    private Label p1ScoreLabel;
    private int p2Score = -1;
    private Label p2ScoreLabel;
    private Label timeLabel;

    public WorldView(Stage stage, WorldModel model) {
        this.model = model;
        this.stage = stage;

        initView();
    }

    private void initView() {
        anchorPane = new AnchorPane();
        setCenter(anchorPane);

        timeLabel = new Label();
        anchorPane.setTopAnchor(timeLabel, 20.0);
        anchorPane.setLeftAnchor(timeLabel, 0.0);
        timeLabel.setAlignment(Pos.CENTER);
        timeLabel.setMinWidth(stage.getWidth());
        anchorPane.getChildren().add(timeLabel);

        p1ScoreLabel = new Label("Player 1: ");
        anchorPane.setLeftAnchor(p1ScoreLabel, 20.0);
        anchorPane.setTopAnchor(p1ScoreLabel, 20.0);
        anchorPane.getChildren().add(p1ScoreLabel);

        p2ScoreLabel = new Label("Player 2: ");
        anchorPane.setRightAnchor(p2ScoreLabel, 20.0);
        anchorPane.setTopAnchor(p2ScoreLabel, 20.0);
        anchorPane.getChildren().add(p2ScoreLabel);

        FighterMenuBar fmb = new FighterMenuBar(stage, true, model);
        setTop(fmb);

        player1 = new ImageView();
        Image p1img = new Image("file:images/player1.png", 32, 32, true, false);
        player1.setImage(p1img);
        player1.setX(model.getPlayer1().getX());
        player1.setY(model.getPlayer1().getY());

        player2 = new ImageView();
        Image p2img = new Image("file:images/player2.png", 32, 32, true, false);
        player2.setImage(p2img);
        player2.setX(model.getPlayer2().getX());
        player2.setY(model.getPlayer2().getY());

        getChildren().addAll(player1, player2);
    }

    public void updateView() {
        Player p1 = model.getPlayer1();
        Player p2 = model.getPlayer2();

        timeLabel.textProperty().setValue(Integer.toString(model.getTimeLeft() / 1000));

        player1.setX(p1.getX());
        player1.setY(p1.getY());

        player2.setX(p2.getX());
        player2.setY(p2.getY());

        if (p1.getScore() != p1Score) {
            p1ScoreLabel.textProperty().setValue("Player 1: " + p1.getScore());
            p1Score = p1.getScore();
        }
        if (p2.getScore() != p2Score) {
            p2ScoreLabel.textProperty().setValue("Player 2: " + p2.getScore());
            p2Score = p2.getScore();
        }
    }
}
