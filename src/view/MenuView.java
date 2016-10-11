package view;

import controller.MenuController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.MenuModel;

/**
 * Created by timothy on 2016-10-11.
 */
public class MenuView extends GridPane {
    private final MenuModel model;

    private Button pvpButton, pvAiButton, exitButton;

    public MenuView(MenuModel model) {
        this.model = model;
        initView();
    }

    private void initView() {
        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.CENTER);

        pvpButton = new Button("Player vs Player");
        pvAiButton = new Button("Player vs AI");
        exitButton = new Button("Exit");

        this.add(pvpButton, 0, 0);
        this.add(pvAiButton, 0, 1);
        this.add(exitButton, 0, 2);
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
