package controller;

import javafx.stage.Stage;
import model.WorldModel;
import view.WorldView;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldController {
    private final WorldModel model;
    private final WorldView view;
    private final Stage stage;

    public WorldController(Stage stage, WorldModel model, WorldView view) {
        //stage.setTitle("Menu");

        this.model = model;
        this.view = view;
        this.stage = stage;

    }
}
