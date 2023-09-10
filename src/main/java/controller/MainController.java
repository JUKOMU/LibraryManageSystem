package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
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
    void initialize() {
        // 获取初始大小
        originalWidth = main_pane.getPrefWidth();
        originalHeight = main_pane.getPrefHeight();

        // 监听舞台大小变化

        main_pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue.doubleValue() / originalWidth;
            main_pane.setPrefWidth(newValue.doubleValue());
            resizeChildren(main_pane, scale);
        });

        main_pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue.doubleValue() / originalHeight;
            main_pane.setPrefHeight(newValue.doubleValue());
            resizeChildren(main_pane, scale);
        });

        ControllerManager.controllers.put("mainController",this);
    }
    private void resizeChildren(AnchorPane pane, double scale) {
        for (javafx.scene.Node child : pane.getChildren()) {
            // 根据缩放比例调整子控件的大小
            child.setScaleX(scale);
            child.setScaleY(scale);
        }
    }
}
