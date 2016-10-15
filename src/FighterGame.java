import controller.MenuController;
import file.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.HighScoreList;
import model.MenuModel;
import view.MenuView;

public class FighterGame extends Application {

    public static void main(String[] args) {
        try {
            HighScoreList.setInstance((HighScoreList) File.readObject("highscorelist"));
        } catch (Exception e) {
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MenuModel menuModel = new MenuModel();
        MenuView menuView = new MenuView(primaryStage, menuModel);
        Scene scene = new Scene(menuView);
        MenuController menuController = new MenuController(primaryStage, scene,
                menuModel, menuView);
        menuView.addEventHandlers(menuController);

        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
