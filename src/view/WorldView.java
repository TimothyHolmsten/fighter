package view;

import controller.MenuController;
import controller.WorldController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.WorldModel;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldView extends AnchorPane {
    private final WorldModel model;
    private Scene scene;

    private ImageView player1;
    private ImageView player2;

    public WorldView(WorldModel model) {
        this.model = model;
        initView();
    }

    private void initView() {

        player1 = new ImageView();
        Image p1img = new Image("file:images/player.png", 32, 32, true, false);
        player1.setImage(p1img);
        this.setBottomAnchor(player1, 32.0);
        this.setLeftAnchor(player1, 32.0);
        this.getChildren().add(player1);

        player2 = new ImageView();
        Image p2img = new Image("file:images/player.png", 32, 32, true, false);
        player2.setImage(p2img);
        this.setBottomAnchor(player2, 32.0);
        this.setLeftAnchor(player2, 100.0);
        this.getChildren().add(player2);
    }
}
