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

        ControllerManager.controllers.put("searchBarController",this );
    }
}
