import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOError;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/GameWindow.fxml"));
        primaryStage.setTitle("Qwirkle");
        primaryStage.setScene(new Scene(root, 760, 760));
        primaryStage.setMaxHeight(760);
        primaryStage.setMinHeight(760);
        primaryStage.setMaxWidth(760);
        primaryStage.setMinWidth(760);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
