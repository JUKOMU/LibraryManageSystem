package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWidget.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
