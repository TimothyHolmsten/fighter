package view;

import controller.MenuController;
import controller.WorldController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.WorldModel;

/**
 * Created by timothy on 2016-10-11.
 */
public class WorldView extends GridPane {
    private final WorldModel model;

    private Button test;

    public WorldView(WorldModel model) {
        this.model = model;
        initView();
    }

    private void initView() {
        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.CENTER);

        test = new Button("TEST");

        this.add(test, 0, 0);
    }

    public void addEventHandlers(WorldController controller) {

        test.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("HEJ TEST");
            }
        });
    }
}
