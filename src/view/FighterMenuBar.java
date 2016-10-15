package view;

import controller.WorldController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import model.AI;
import model.Player;
import model.WorldModel;
import model.HighScoreList;
import model.HighScoreEntry;

public class FighterMenuBar extends MenuBar {
    public FighterMenuBar(Stage stage, boolean showPause) {

        Menu gameMenu = new Menu("Game");

        MenuItem MIPvP = new MenuItem("Start Player vs Player");
        MIPvP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                WorldModel wModel = new WorldModel(new Player(0, 50, 32, 32), new Player(50, 50, 32, 32));
                WorldView wView = new WorldView(stage, wModel);
                Scene scene = new Scene(wView);
                stage.setScene(scene);
                new WorldController(stage, scene, wModel, wView);
            }
        });
        MenuItem MIPvAI = new MenuItem("Start Player vs AI");
        MIPvAI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player realPlayer = new Player(0, 50, 32, 32);

                WorldModel wModel = new WorldModel(realPlayer, new AI(50, 50, 32, 32, realPlayer));
                WorldView wView = new WorldView(stage, wModel);
                Scene scene = new Scene(wView);
                stage.setScene(scene);
                new WorldController(stage, scene, wModel, wView);
            }
        });
        MenuItem MIPause = new MenuItem("Pause");
        MenuItem MIHighscore = new MenuItem("Show highscore");
        MIHighscore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<HighScoreEntry> list
                    = HighScoreList.getInstance().getList();
                Stage stage = new Stage();
                stage.setTitle("Highscore");
                GridPane root = new GridPane();
                root.setVgap(10);
                root.setHgap(10);
                root.setAlignment(Pos.CENTER);
                int row = 0;
                HighScoreList.getInstance().sort();
                for (HighScoreEntry entry : list) {
                    Label name = new Label(entry.name);
                    Label score = new Label(Integer.toString(entry.score));
                    root.add(name, 0, row);
                    root.add(score, 1, row);
                    row++;
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
        MenuItem MIExit = new MenuItem("Exit");
        MIExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.close();
            }
        });

        if (showPause)
            gameMenu.getItems().addAll(MIPvP, MIPvAI, MIPause, MIHighscore, MIExit);
        else
            gameMenu.getItems().addAll(MIPvP, MIPvAI, MIHighscore, MIExit);

        Menu helpMenu = new Menu("Help");
        MenuItem MIHow = new MenuItem("How to play");
        MIHow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                stage.setTitle("How to play");
                BorderPane root = new BorderPane();
                String htpText = "How to play:\n\n"
                        + "Player 1 controls: W A S D\n" +
                        "Player 2 controls: Arrow keys\n\n" +
                        "The purpose of the game is to jump onto the head of the opponent.\n" +
                        "The players can jump and attack where attack makes the player dive faster.\n\n\n" +
                        "Press ESC to go back to the menu.";
                Label text = new Label(htpText);
                text.setPadding(new Insets(10));
                root.setCenter(text);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
        helpMenu.getItems().addAll(MIHow);

        getMenus().addAll(gameMenu, helpMenu);
    }
}
