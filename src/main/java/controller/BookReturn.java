package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class BookReturn {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView camera_return;

    @FXML
    private ImageView book_view;

    @FXML
    private Label book_author;

    @FXML
    private Label book_name;

    @FXML
    private Label tip_return;

    @FXML
    void initialize() {
        assert camera_return != null : "fx:id=\"camera_return\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert book_view != null : "fx:id=\"book_view\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert book_author != null : "fx:id=\"book_author\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert book_name != null : "fx:id=\"book_name\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert tip_return != null : "fx:id=\"tip_return\" was not injected: check your FXML file 'bookReturn.fxml'.";

    }
}
