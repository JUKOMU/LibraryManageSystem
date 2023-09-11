package camera;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        VideoPane摄像头视频面板
         */
        VideoPanel videoPanel = new VideoPanel();
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(videoPanel.getVideoPanel());
        primaryStage.setTitle("JavaFX嵌入通过swing调用的摄像头");
        primaryStage.setScene(new Scene(stackPane, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}