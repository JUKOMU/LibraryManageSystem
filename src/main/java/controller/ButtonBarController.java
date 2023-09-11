package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ButtonBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button borrow_renewal;

    @FXML
    private Button my_space;

    @FXML
    private Pane search_pane;

    @FXML
    private Button book_search;

    @FXML
    private Button return_book;

    @FXML
    void onBookSearch(ActionEvent event) {
        book_search.setStyle("-fx-background-color:#ffa500;-fx-background-radius:30px");
        borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");

    }

    @FXML
    void onBorrowRenewal(ActionEvent event) {
        book_search.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        borrow_renewal.setStyle("-fx-background-color:#ffa500;-fx-background-radius:30px;");
        return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");

    }

    @FXML
    void onReturnBook(ActionEvent event) {
        book_search.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        return_book.setStyle("-fx-background-color:#ffa500;-fx-background-radius:30px;");
        my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");

    }

    @FXML
    void onMySpace(ActionEvent event) {
        book_search.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:30px");
        my_space.setStyle("-fx-background-color:#ffa500;-fx-background-radius:30px;");

    }

    @FXML
    void initialize() {
        assert borrow_renewal != null : "fx:id=\"borrow_renewal\" was not injected: check your FXML file 'buttonBar.fxml'.";
        assert my_space != null : "fx:id=\"my_space\" was not injected: check your FXML file 'buttonBar.fxml'.";
        assert search_pane != null : "fx:id=\"search_pane\" was not injected: check your FXML file 'buttonBar.fxml'.";
        assert book_search != null : "fx:id=\"book_search\" was not injected: check your FXML file 'buttonBar.fxml'.";
        assert return_book != null : "fx:id=\"return_book\" was not injected: check your FXML file 'buttonBar.fxml'.";


        ControllerManager.controllers.put("buttonBarController",this );
    }
}
