package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AI;
import model.Player;
import model.WorldModel;
import view.MenuView;
import view.WorldView;

/**
 * Created by timothy on 2016-10-11.
 */
public class MenuController {

    private final MenuView view;
    private final Stage stage;
    private Scene scene;

    public MenuController(Stage stage, Scene scene, MenuView view) {

        this.view = view;
        this.stage = stage;
        this.scene = scene;
    }

    public void handlePvPEvent() {
        WorldModel wModel = new WorldModel(new Player(0, 50, 32, 32), new Player(50, 50, 32, 32));
        WorldView wView = new WorldView(stage, wModel);
        scene = new Scene(wView);
        stage.setScene(scene);
        new WorldController(stage, scene, wModel, wView);
    }

    public void handleExitEvent() {
        stage.close();
    }

    public void handlePvAIEvent() {
        Player realPlayer = new Player(0, 50, 32, 32);

        WorldModel wModel = new WorldModel(realPlayer, new AI(50, 50, 32, 32, realPlayer));
        WorldView wView = new WorldView(stage, wModel);
        scene = new Scene(wView);
        stage.setScene(scene);
        new WorldController(stage, scene, wModel, wView);
    }
}
