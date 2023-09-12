package controller;

import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
    private final boolean[] isON = {false, false, false, false};

    @FXML
    private Button my_space;

    @FXML
    private Pane search_pane;

    @FXML
    private Button book_search;

    @FXML
    private Button return_book;

    @FXML
    void onBookSearch(ActionEvent event) throws IOException {
        if (!isON[0]) {
            for (int i = 0; i < 4;i++) {
                isON[i] = false;
            }
            isON[0] = true;
            book_search.setStyle("-fx-background-color:#ffa500;-fx-background-radius:50px");
            borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookDisplayBox.fxml"));
            Node node = loader.load();

            MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
            HBox mainHbox = mainController.getMain_hbox();
            mainHbox.getChildren().clear();
            mainHbox.getChildren().add(node);
            BookDisplayController bookDisplayController = (BookDisplayController) ControllerManager.controllers.get("bookDisplayController");
            bookDisplayController.showAllBooks();
        }
    }

    void onBookSearch2() throws IOException {
        if (!isON[0]) {
            for (int i = 0; i < 4; i++) {
                isON[i] = false;
            }
            isON[0] = true;
            book_search.setStyle("-fx-background-color:#ffa500;-fx-background-radius:50px");
            borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookDisplayBox.fxml"));
            Node node = loader.load();

            MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
            HBox mainHbox = mainController.getMain_hbox();
            mainHbox.getChildren().clear();
            mainHbox.getChildren().add(node);
            BookDisplayController bookDisplayController = (BookDisplayController) ControllerManager.controllers.get("bookDisplayController");
        }
    }
    void onBookSearch() throws IOException {
        if (!isON[0]) {
            for (int i = 0; i < 4;i++) {
                isON[i] = false;
            }
            isON[0] = true;
            book_search.setStyle("-fx-background-color:#ffa500;-fx-background-radius:50px");
            borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookDisplayBox.fxml"));
            Node node = loader.load();

            MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
            HBox mainHbox = mainController.getMain_hbox();
            mainHbox.getChildren().clear();
            mainHbox.getChildren().add(node);
            BookDisplayController bookDisplayController = (BookDisplayController) ControllerManager.controllers.get("bookDisplayController");
            bookDisplayController.showAllBooks();
        }
    }

    @FXML
    void onBorrowRenewal(ActionEvent event) throws IOException {
        if (!isON[1]) {
            for (int i = 0; i < 4;i++) {
                isON[i] = false;
            }
            isON[1] = true;

            //BookReturnController bookReturnController = (BookReturnController) ControllerManager.controllers.get("bookReturnController");
            //bookReturnController.setCapturing(false);

            book_search.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            borrow_renewal.setStyle("-fx-background-color:#ffa500;-fx-background-radius:50px;");
            return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookBorrowRenewal.fxml"));
            Node node = loader.load();

            MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
            HBox mainHbox = mainController.getMain_hbox();
            mainHbox.getChildren().clear();
            mainHbox.getChildren().add(node);
        }
    }

    @FXML
    void onReturnBook(ActionEvent event) throws IOException {
        if (!isON[2]) {
            for (int i = 0; i < 4;i++) {
                isON[i] = false;
            }
            isON[2] = true;

            //BookBorrowRenewalController bookBorrowRenewalController = (BookBorrowRenewalController) ControllerManager.controllers.get("bookBorrowRenewalController");
            //bookBorrowRenewalController.setCapturing(false);

            book_search.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            return_book.setStyle("-fx-background-color:#ffa500;-fx-background-radius:50px;");
            my_space.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookReturn.fxml"));
            Node node = loader.load();

            MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
            HBox mainHbox = mainController.getMain_hbox();
            mainHbox.getChildren().clear();
            mainHbox.getChildren().add(node);
        }
    }

    @FXML
    void onMySpace(ActionEvent event) throws IOException {
        if (!isON[3]) {
            for (int i = 0; i < 4;i++) {
                isON[i] = false;
            }
            isON[3] = true;
            book_search.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            borrow_renewal.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            return_book.setStyle("-fx-background-color:#fdcd76;-fx-background-radius:50px");
            my_space.setStyle("-fx-background-color:#ffa500;-fx-background-radius:50px;");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Node node = loader.load();

            MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
            HBox mainHbox = mainController.getMain_hbox();
            mainHbox.getChildren().clear();
            mainHbox.getChildren().addAll(node);
        }
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
