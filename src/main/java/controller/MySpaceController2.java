package controller;

import java.net.URL;
import java.util.ResourceBundle;

import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MySpaceController2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label borrowedCount;

    @FXML
    private Label title;

    @FXML
    private Label totalCount;

    @FXML
    void initialize() {
        assert borrowedCount != null : "fx:id=\"borrowedCount\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert totalCount != null : "fx:id=\"totalCount\" was not injected: check your FXML file 'mySpace.fxml'.";

        ControllerManager.controllers.put("mySpaceController2",this);
    }

    void showUser(User user) {
        title.setText(user.getUser_name() + ",欢迎使用图书管理系统!");
        borrowedCount.setText(user.getCurrent_borrowed_books() + "");
        totalCount.setText(user.getBorrowed_count() + "");
    }
}
