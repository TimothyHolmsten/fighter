package view;

import javafx.stage.Stage;
import controller.MenuController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.MenuModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Created by timothy on 2016-10-11.
 */
public class MenuView extends BorderPane {
    private final MenuModel model;
    private GridPane gridPane;
    private Button pvpButton, pvAiButton, exitButton;
    private Stage stage;

    public MenuView(Stage stage, MenuModel model) {
        this.model = model;
        this.stage = stage;
        initView();
    }

    private void initView() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        setCenter(gridPane);

        setTop(new FighterMenuBar(stage, false));

        pvpButton = new Button("Player vs Player");
        pvAiButton = new Button("Player vs AI");
        exitButton = new Button("Exit");

        gridPane.add(pvpButton, 0, 0);
        gridPane.add(pvAiButton, 0, 1);
        gridPane.add(exitButton, 0, 2);
    }

    public void addEventHandlers(MenuController controller) {
        pvpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handlePvPEvent();
            }
        });

        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleExitEvent();
            }
        });
    }
}
