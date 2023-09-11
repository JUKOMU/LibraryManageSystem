package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private AnchorPane main_pane;

    private double originalWidth;
    private double originalHeight;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox main_hbox;

    public AnchorPane getMain_pane() {
        return main_pane;
    }

    public double getOriginalWidth() {
        return originalWidth;
    }

    public double getOriginalHeight() {
        return originalHeight;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public URL getLocation() {
        return location;
    }

    public HBox getMain_hbox() {
        return main_hbox;
    }

    @FXML
    void initialize() {
        ControllerManager.controllers.put("mainController",this);
    }

    void addFXMLResource(String FXMLUrl){

        try {
            // 加载其他FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLUrl));
            Node node = loader.load();

            // 将加载的内容添加到HBox中
            main_hbox.getChildren().clear(); // 清除HBox中的内容（可选）
            main_hbox.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
