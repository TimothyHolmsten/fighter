package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.MenuModel;
import model.Player;
import model.WorldModel;
import view.MenuView;
import view.WorldView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import model.Player;
import java.lang.Math;

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

                switch (event.getCode()) {
                    case ESCAPE:
                        handleEscEvent();
                        break;
                    case A:
                        model.getPlayer1().walk(-100);
                        break;
                    case D:
                        model.getPlayer1().walk(100);
                        break;
                    case W:
                        model.getPlayer1().jump(1000);
                        break;

                    case LEFT:
                        model.getPlayer2().walk(-100);
                        break;
                    case RIGHT:
                        model.getPlayer2().walk(100);
                        break;

                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                Player p1 = model.getPlayer1();
                Player p2 = model.getPlayer2();
                switch (e.getCode()) {
                case A:
                    if (p1.getDx() < 0)
                        p1.walk(0);
                    break;
                case D:
                    if (p1.getDx() > 0)
                        p1.walk(0);
                    break;
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
            Player p1 = model.getPlayer1();
            Player p2 = model.getPlayer2();

            for(Player p: model.getPlayers()) {
                p.move(now-previous);
                p.gravity(now-previous);
                p.constrain(stage.getWidth(), stage.getHeight(), 32, 32);
            }

            if (Math.abs(p1.getX() - p2.getX()) < 16
                && Math.abs(p1.getY() - p2.getY()) < 32) {
                double p1Speed = p1.getSpeed();
                double p2Speed = p2.getSpeed();
                if (p1Speed > 10 || p2Speed > 10) {
                    if (p1Speed > p2Speed) {
                        System.out.println("Player 1 wins!");
                    } else if (p2Speed > p1Speed) {
                        System.out.println("Player 2 wins!");
                    }
                }
            }

            view.updateView();

            previous = now;
        }

    }
}
