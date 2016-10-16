package controller;

import file.File;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;
import view.MenuView;
import view.WorldView;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldController {
    private final WorldModel model;
    private final WorldView view;
    private final Stage stage;
    private Scene scene;
    private final UpdateTimer updateTimer;

    public WorldController(Stage stage, Scene scene,
                           WorldModel model, WorldView view) {

        this.model = model;
        this.view = view;
        this.stage = stage;
        this.scene = scene;

        addEventHandlers();
        updateTimer = new UpdateTimer();
        updateTimer.start();
    }

    private void addEventHandlers() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case ESCAPE:
                        updateTimer.stop();
                        handleEscEvent();
                        break;
                }
                if (model.getState() == State.RUNNING) {
                    if (!(model.getPlayer1() instanceof AI))
                        switch (event.getCode()) {
                            case A:
                                model.getPlayer1().walk(-100);
                                break;
                            case D:
                                model.getPlayer1().walk(100);
                                break;
                            case W:
                                model.getPlayer1().jump(300);
                                break;
                            case S:
                                model.getPlayer1().attack(500);
                                break;
                        }
                    if (!(model.getPlayer2() instanceof AI))
                        switch (event.getCode()) {
                            case LEFT:
                                model.getPlayer2().walk(-100);
                                break;
                            case RIGHT:
                                model.getPlayer2().walk(100);
                                break;
                            case UP:
                                model.getPlayer2().jump(300);
                                break;
                            case DOWN:
                                model.getPlayer2().attack(500);

                        }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                Player p1 = model.getPlayer1();
                Player p2 = model.getPlayer2();
                if (!(p1 instanceof AI))
                    switch (e.getCode()) {
                        case A:
                            if (p1.getDx() < 0)
                                p1.walk(0);
                            break;
                        case D:
                            if (p1.getDx() > 0)
                                p1.walk(0);
                            break;
                    }
                if (!(p2 instanceof AI))
                    switch (e.getCode()) {
                        case LEFT:
                            if (p2.getDx() < 0)
                                p2.walk(0);
                            break;
                        case RIGHT:
                            if (p2.getDx() > 0)
                                p2.walk(0);
                            break;

                    }
            }
        });
    }

    private void handleEscEvent() {
        updateTimer.stop();

        MenuModel mModel = new MenuModel();
        MenuView mView = new MenuView(stage, mModel);
        scene = new Scene(mView);
        stage.setScene(scene);
        MenuController menuController = new MenuController(stage, scene, mModel, mView);
        mView.addEventHandlers(menuController);
    }

    private class UpdateTimer extends AnimationTimer {
        private long previous = 0;
        private Scene myScene = stage.getScene();

        @Override
        public void handle(long now) {

            if (myScene != stage.getScene())
                stop();

            if (model.getTimeLeft() <= 0) {
                stop();
                if (model.getPlayer2() instanceof AI) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            TextInputDialog tid = new TextInputDialog();
                            tid.setContentText("Enter your name:");
                            Optional<String> name = tid.showAndWait();
                            if (name.isPresent()) {
                                HighScoreList.getInstance().add(name.get(), model.getPlayer1().getScore());
                                try {
                                    File.writeObject("highscorelist",
                                            HighScoreList.getInstance());
                                } catch (IOException e) {
                                    Alert a = new Alert(Alert.AlertType.WARNING,
                                            "Could not write to file.",
                                            ButtonType.OK);
                                    a.showAndWait();
                                }
                            }

                            MenuModel mModel = new MenuModel();
                            MenuView mView = new MenuView(stage, mModel);
                            scene = new Scene(mView);
                            stage.setScene(scene);
                            MenuController menuController = new MenuController(stage, scene, mModel, mView);
                            mView.addEventHandlers(menuController);
                        }
                    });
                }
                return;
            }
            model.play(now - previous, stage.getWidth(), stage.getHeight());
            view.updateView();

            previous = now;
        }

    }
}
