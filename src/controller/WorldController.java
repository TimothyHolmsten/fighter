package controller;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonType;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import model.AI;
import model.MenuModel;
import model.Player;
import model.WorldModel;
import view.MenuView;
import view.WorldView;
import model.HighScoreList;
import file.File;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldController {
    private final WorldModel model;
    private final WorldView view;
    private final Stage stage;
    private Scene scene;
    private long endTime;
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
            /*Player p1 = model.getPlayer1();
            Player p2 = model.getPlayer2();

            model.timeLeft = (int)(endTime - System.currentTimeMillis());

            for (Player p : model.getPlayers()) {
                p.move(now - previous);
                p.gravity(now - previous);
                p.constrain(stage.getWidth(), stage.getHeight());
                if (p instanceof AI)
                    ((AI) p).think();
            }

            if (Math.abs(p1.getX() - p2.getX()) < 16
                    && Math.abs(p1.getY() - p2.getY()) < 32) {
                double p1Speed = p1.getSpeed();
                double p2Speed = p2.getSpeed();
                if (p1Speed > 10 || p2Speed > 10) {
                    if (p1.onTopOfPlayer(p2) && p1.getDy() > p2.getDy()) {
                        p1.addScore(1);
                        p1.jump(1000);
                    } else if (p2.onTopOfPlayer(p1) && p2.getDy() > p1.getDy()) {
                        p2.addScore(1);
                        p2.jump(1000);
                    }
                }
            }*/

            System.out.println("g");
            model.play(now - previous, stage.getWidth(), stage.getHeight());

            if (model.timeLeft <= 0) {
                stop();
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
                return;
            }

            view.updateView();

            previous = now;
        }

    }
}
