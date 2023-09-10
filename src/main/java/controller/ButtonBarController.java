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

        // 初始化时监听窗口大小变化
        search_pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue.doubleValue() / 153.0; // 153.0是初始宽度

            // 调整按钮的宽度
            book_search.setPrefWidth(192.0 * scale);
            borrow_renewal.setPrefWidth(192.0 * scale);
            return_book.setPrefWidth(192.0 * scale);
            my_space.setPrefWidth(192.0 * scale);

            // 调整字体大小
            double fontSize = 25.0 * scale; // 25.0是初始字体大小
            book_search.setFont(new Font(fontSize));
            borrow_renewal.setFont(new Font(fontSize));
            return_book.setFont(new Font(fontSize));
            my_space.setFont(new Font(fontSize));
        });

        search_pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue.doubleValue() / 387.5; // 387.5是初始高度

            // 调整按钮的高度
            book_search.setPrefHeight(62.5 * scale);
            borrow_renewal.setPrefHeight(62.5 * scale);
            return_book.setPrefHeight(62.5 * scale);
            my_space.setPrefHeight(62.5 * scale);
        });
        ControllerManager.controllers.put("buttonBarController",this );
    }
}
