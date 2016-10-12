package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MenuModel;
import model.WorldModel;
import view.MenuView;
import view.WorldView;

/**
 * Created by timothy on 2016-10-11.
 */
public class MenuController {

    private final MenuModel model;
    private final MenuView view;
    private final Stage stage;
    private final Scene scene;

    public MenuController(Stage stage, Scene scene,
                          MenuModel model, MenuView view) {
        //stage.setTitle("Menu");

        this.model = model;
        this.view = view;
        this.stage = stage;
        this.scene = scene;
    }

    public void handlePvPEvent() {
        System.out.println("pvp");
        WorldModel wModel = new WorldModel();
        WorldView wView = new WorldView(wModel);
        WorldController w = new WorldController(stage, scene, wModel, wView);
        stage.setScene(new Scene(wView));
    }

    public void handleExitEvent() {
        stage.close();
    }
}
