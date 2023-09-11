package controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BookDisplayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane display_pane;

    @FXML
    private Label book_name7;

    @FXML
    private ImageView grid_book_view8;

    @FXML
    private Label book_name8;

    @FXML
    private Label book_name5;

    @FXML
    private ImageView grid_book_view7;

    @FXML
    private ImageView grid_book_view6;

    @FXML
    private Label book_name6;

    @FXML
    private Label book_name3;

    @FXML
    private ImageView grid_book_view5;

    @FXML
    private ImageView grid_book_view4;

    @FXML
    private Label book_name4;

    @FXML
    private Label book_name1;

    @FXML
    private ImageView grid_book_view3;

    @FXML
    private ImageView grid_book_view2;

    @FXML
    private Label book_name2;

    @FXML
    private ImageView grid_book_view1;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox4;

    @FXML
    private Label book_surplus8;

    @FXML
    private VBox vbox3;

    @FXML
    private Label book_surplus6;

    @FXML
    private Label book_surplus7;

    @FXML
    private Label book_surplus4;

    @FXML
    private Label book_surplus5;

    @FXML
    private GridPane display_grid;

    @FXML
    private Label book_surplus2;

    @FXML
    private Label book_surplus3;

    @FXML
    private Label book_surplus1;

    @FXML
    private VBox vbox6;

    @FXML
    private VBox vbox5;

    @FXML
    private VBox vbox8;

    @FXML
    private VBox vbox7;

    @FXML
    private final VBox[] vbox = new VBox[8];

    @FXML
    private final ImageView[] grid_book_view = new ImageView[8];

    @FXML
    private final Label[] book_name = new Label[8];

    @FXML
    private final Label[] book_surplus = new Label[8];

    @FXML
    void initialize() {
        assert display_pane != null : "fx:id=\"display_pane\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name7 != null : "fx:id=\"book_name7\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view8 != null : "fx:id=\"grid_book_view8\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name8 != null : "fx:id=\"book_name8\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name5 != null : "fx:id=\"book_name5\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view7 != null : "fx:id=\"grid_book_view7\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view6 != null : "fx:id=\"grid_book_view6\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name6 != null : "fx:id=\"book_name6\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name3 != null : "fx:id=\"book_name3\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view5 != null : "fx:id=\"grid_book_view5\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view4 != null : "fx:id=\"grid_book_view4\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name4 != null : "fx:id=\"book_name4\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name1 != null : "fx:id=\"book_name1\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view3 != null : "fx:id=\"grid_book_view3\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view2 != null : "fx:id=\"grid_book_view2\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_name2 != null : "fx:id=\"book_name2\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert grid_book_view1 != null : "fx:id=\"grid_book_view1\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox2 != null : "fx:id=\"vbox2\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox1 != null : "fx:id=\"vbox1\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox4 != null : "fx:id=\"vbox4\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus8 != null : "fx:id=\"book_surplus8\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox3 != null : "fx:id=\"vbox3\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus6 != null : "fx:id=\"book_surplus6\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus7 != null : "fx:id=\"book_surplus7\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus4 != null : "fx:id=\"book_surplus4\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus5 != null : "fx:id=\"book_surplus5\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert display_grid != null : "fx:id=\"display_grid\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus2 != null : "fx:id=\"book_surplus2\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus3 != null : "fx:id=\"book_surplus3\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert book_surplus1 != null : "fx:id=\"book_surplus1\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox6 != null : "fx:id=\"vbox6\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox5 != null : "fx:id=\"vbox5\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox8 != null : "fx:id=\"vbox8\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";
        assert vbox7 != null : "fx:id=\"vbox7\" was not injected: check your FXML file 'bookDisplayBox.fxml'.";


        vbox[0] = vbox1;
        vbox[1] = vbox2;
        vbox[2] = vbox3;
        vbox[3] = vbox4;
        vbox[4] = vbox5;
        vbox[5] = vbox6;
        vbox[6] = vbox7;
        vbox[7] = vbox8;

        grid_book_view[0] = grid_book_view1;
        grid_book_view[1] = grid_book_view2;
        grid_book_view[2] = grid_book_view3;
        grid_book_view[3] = grid_book_view4;
        grid_book_view[4] = grid_book_view5;
        grid_book_view[5] = grid_book_view6;
        grid_book_view[6] = grid_book_view7;
        grid_book_view[7] = grid_book_view8;

        book_name[0] = book_name1;
        book_name[1] = book_name2;
        book_name[2] = book_name3;
        book_name[3] = book_name4;
        book_name[4] = book_name5;
        book_name[5] = book_name6;
        book_name[6] = book_name7;
        book_name[7] = book_name8;

        book_surplus[0] = book_surplus1;
        book_surplus[1] = book_surplus2;
        book_surplus[2] = book_surplus3;
        book_surplus[3] = book_surplus4;
        book_surplus[4] = book_surplus5;
        book_surplus[5] = book_surplus6;
        book_surplus[6] = book_surplus7;
        book_surplus[7] = book_surplus8;


        // 初始化时监听窗口大小变化
        Stage stage = (Stage) display_pane.getScene().getWindow();

        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            double scaleX = newValue.doubleValue() / 625.0; // 625.0是初始宽度

            // 调整容器的宽度
            for (VBox box : vbox) {
                box.setPrefWidth(200.0 * scaleX);
            }

            // 调整图片的宽度
            for (ImageView imageView : grid_book_view) {
                imageView.setFitWidth(99.0 * scaleX);
            }

            // 调整字体大小
            double fontSize = 25.0 * scaleX; // 25.0是初始字体大小
            for (Label label : book_name) {
                label.setFont(new Font(fontSize));
            }
            for (Label label : book_surplus) {
                label.setFont(new Font(fontSize));
            }
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            double scaleY = newValue.doubleValue() / 333.0; // 333.0是初始高度

            // 调整容器的高度
            for (VBox box : vbox) {
                box.setPrefHeight(100.0 * scaleY);
            }

            // 调整图片的高度
            for (ImageView imageView : grid_book_view) {
                imageView.setFitHeight(115.0 * scaleY);
            }
        });

        ControllerManager.controllers.put("bookDisplayController",this );
    }
}
