package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchBarController {

    @FXML private Pane searchPane;
    @FXML private ImageView searchBarImage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search;

    @FXML
    private TextField author;

    @FXML
    private TextField name;

    @FXML
    void onSearchBooks(ActionEvent event) {

    }

    @FXML
    void ff6f0000(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'searchBar.fxml'.";
        assert searchPane != null : "fx:id=\"searchPane\" was not injected: check your FXML file 'searchBar.fxml'.";
        assert author != null : "fx:id=\"author\" was not injected: check your FXML file 'searchBar.fxml'.";
        assert searchBarImage != null : "fx:id=\"searchBarImage\" was not injected: check your FXML file 'searchBar.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'searchBar.fxml'.";


        // 初始化时监听窗口大小变化
        searchPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            // 调整组件的宽度
            double scale = newValue.doubleValue() / 800.0; // 800是初始宽度
            searchBarImage.setFitWidth(700.0 * scale);
            search.setPrefWidth(43.0 * scale);
            name.setPrefWidth(201.0 * scale);
            author.setPrefWidth(201.0 * scale);

            // 调整字体大小
            double fontSize = 14.0 * scale; // 14.0是初始字体大小
            name.setStyle("-fx-font-size: " + fontSize + "px;");
            author.setStyle("-fx-font-size: " + fontSize + "px;");
        });

        searchPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            // 调整组件的高度
            double scale = newValue.doubleValue() / 62.5; // 62.5是初始高度
            searchBarImage.setFitHeight(62.5 * scale);
            search.setPrefHeight(36.0 * scale);
            name.setPrefHeight(36.0 * scale);
            author.setPrefHeight(36.0 * scale);
        });
        ControllerManager.controllers.put("searchBarController",this );
    }
}
