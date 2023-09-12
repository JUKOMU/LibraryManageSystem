package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.BookDao;
import domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.Result;

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
    void onSearchBooks(ActionEvent event) throws IOException {
        BookService bookService = new BookServiceImpl();
        Book book = new Book();
        String bookName = name.getText().trim();
        String authorName = author.getText().trim();
        System.out.println(bookName);
        Result result = new Result();
        if (bookName != ""){
            if (authorName != "") {
                result = bookService.findBookByNameByAuthor(bookName, authorName);
            }
            result = bookService.findBookByName(bookName);
        } else if (authorName != "") {
            result = bookService.findBookByAuthor(authorName);
        }

        if (result.getCode() == 200) {
            ButtonBarController buttonBarController = (ButtonBarController) ControllerManager.controllers.get("buttonBarController");
            buttonBarController.onBookSearch2();

            BookDisplayController bookDisplayController = (BookDisplayController) ControllerManager.controllers.get("bookDisplayController");

            Label[] book_name = bookDisplayController.getBook_name();
            Label[] book_surplus = bookDisplayController.getBook_surplus();
            ImageView[] grid_book_view = bookDisplayController.getGrid_book_view();

            for (int i = 0; i < 8; i++) {
                book_name[i].setText("");
                book_surplus[i].setText("");
                grid_book_view[i].setImage(null);
            }

            System.out.println("ok");
            List<Book> books = (List<Book>) result.getData();
            for (int i = 0; i < books.size(); i++) {
                book_name[i].setText(books.get(i).getName());
                book_surplus[i].setText("馆藏剩余：" + books.get(i).getAvailable_copies() + "/" + books.get(i).getTotal_library_copies());
                grid_book_view[i].setImage(new Image("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\database_source\\" + books.get(i).getId() + ".jpg"));
            }
        }
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
