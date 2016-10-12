package controller;

import javafx.animation.AnimationTimer;
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
        UpdateTimer updateTimer = new UpdateTimer();
        updateTimer.start();
    }

    private void addEventHandlers() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getText());

                if(event.getCode() == KeyCode.ESCAPE)
                    handleEscEvent();

                if (event.getCode() == KeyCode.A) {
                    model.getPlayer1().walk(-100);
                } else if (event.getCode() == KeyCode.D) {
                    model.getPlayer1().walk(100);
                } else if (event.getCode() == KeyCode.LEFT) {
                    model.getPlayer2().walk(-100);
                } else if (event.getCode() == KeyCode.RIGHT) {
                    model.getPlayer2().walk(100);
                }
            }
        });
    }

    private void handleEscEvent() {
        MenuModel mModel = new MenuModel();
        MenuView mView = new MenuView(stage, mModel);
        scene = new Scene(mView);
        stage.setScene(scene);
        MenuController menuController = new MenuController(stage, scene, mModel, mView);
        mView.addEventHandlers(menuController);
    }

    private class UpdateTimer extends AnimationTimer {
        private long previous = 0;

        @Override
        public void handle(long now) {


            model.getPlayer1().move(now - previous);
            model.getPlayer2().move(now - previous);
            view.updateView();

            previous = now;
        }

    }
}
