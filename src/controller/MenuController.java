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

    public MenuController(Stage stage, MenuModel model, MenuView view) {
        //stage.setTitle("Menu");

        this.model = model;
        this.view = view;
        this.stage = stage;

    }

    public void handlePvPEvent() {
        System.out.println("pvp");
        WorldModel wModel = new WorldModel();
        WorldView wView = new WorldView(wModel);
        WorldController w = new WorldController(stage, wModel, wView);
        wView.addEventHandlers(w);
        stage.setScene(new Scene(wView));
    }

    public void handleExitEvent() {
        stage.close();
    }
}
