package controller;

import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.MenuModel;
import model.WorldModel;
import view.MenuView;
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
    private Scene scene;

    public WorldController(Stage stage, Scene scene,
                           WorldModel model, WorldView view) {

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
                System.out.println(event.getText());

                if(event.getCode() == KeyCode.ESCAPE)
                    handleEscEvent();

                if(event.getCode() == KeyCode.A)
                    model.getPlayer1().walk(-32);
                if(event.getCode() == KeyCode.D)
                    model.getPlayer1().walk(32);

                view.updateView();
            }
        });
    }

    private void handleEscEvent() {
        MenuModel mModel = new MenuModel();
        MenuView mView = new MenuView(mModel);
        scene = new Scene(mView);
        stage.setScene(scene);
        MenuController menuController = new MenuController(stage, scene, mModel, mView);
        mView.addEventHandlers(menuController);
    }
}
