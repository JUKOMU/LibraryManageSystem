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

        ControllerManager.controllers.put("mySpaceController",this);
    }
}
