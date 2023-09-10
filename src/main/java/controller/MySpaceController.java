package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MySpaceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField pwd;

    @FXML
    private Button login_btn;

    @FXML
    private Pane login_pane;

    @FXML
    private Label tip_register1;

    @FXML
    private Label tip_register2;

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert pwd != null : "fx:id=\"pwd\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert login_btn != null : "fx:id=\"login_btn\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert login_pane != null : "fx:id=\"login_pane\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert tip_register1 != null : "fx:id=\"tip_register1\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert tip_register2 != null : "fx:id=\"tip_register2\" was not injected: check your FXML file 'mySpace.fxml'.";

        // 初始化时监听窗口大小变化
        Stage stage = (Stage) login_pane.getScene().getWindow();

        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue.doubleValue() / 625.0; // 625.0是初始宽度
            // 调整组件的宽度
            name.setPrefWidth(265.0 * scale);
            pwd.setPrefWidth(265.0 * scale);
            login_btn.setPrefWidth(150.0 * scale);
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue.doubleValue() / 333.0; // 333.0是初始高度
            // 调整组件的高度
            name.setPrefHeight(39.0 * scale);
            pwd.setPrefHeight(39.0 * scale);
            login_btn.setPrefHeight(34.0 * scale);
            tip_register1.setLayoutY(304.0 * scale);
            tip_register2.setLayoutY(304.0 * scale);
            // 调整字体大小
            double fontSize = 16.0 * scale; // 16.0是初始字体大小
            login_btn.setFont(new Font(fontSize));
            tip_register1.setFont(new Font(fontSize - 5)); // 减少5个单位以保持比例
            tip_register2.setFont(new Font(fontSize - 5)); // 减少5个单位以保持比例
        });
        ControllerManager.controllers.put("mySpaceController",this);
    }
}
