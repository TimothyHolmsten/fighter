package controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import model.WorldModel;
import view.WorldView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldController {
    private final WorldModel model;
    private final WorldView view;
    private final Stage stage;
    private final Scene scene;

    public WorldController(Stage stage, Scene scene,
                           WorldModel model, WorldView view) {
        //stage.setTitle("Menu");

        this.model = model;
        this.view = view;
        this.stage = stage;
        this.scene = scene;

        addEventHandlers();
    }

    private void addEventHandlers() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("key");
            }
        });
    }
}
