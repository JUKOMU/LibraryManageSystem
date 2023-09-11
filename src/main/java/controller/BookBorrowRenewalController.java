package controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;


public class BookBorrowRenewalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView book_view;

    @FXML
    private Label book_author;

    @FXML
    private Label tip;

    @FXML
    private Label book_name;

    @FXML
    private ImageView camera;

    private FrameGrabber grabber;
    private Java2DFrameConverter converter;

    private boolean capturing = true;

    @FXML
    void initialize() {
        assert book_view != null : "fx:id=\"book_view\" was not injected: check your FXML file 'bookBorrowRenewal.fxml'.";
        assert book_author != null : "fx:id=\"book_author\" was not injected: check your FXML file 'bookBorrowRenewal.fxml'.";
        assert tip != null : "fx:id=\"tip\" was not injected: check your FXML file 'bookBorrowRenewal.fxml'.";
        assert book_name != null : "fx:id=\"book_name\" was not injected: check your FXML file 'bookBorrowRenewal.fxml'.";
        assert camera != null : "fx:id=\"camera\" was not injected: check your FXML file 'bookBorrowRenewal.fxml'.";


        try {

            grabber = new OpenCVFrameGrabber(0); // 0 表示默认摄像头
            grabber.start();

            converter = new Java2DFrameConverter();

            capturing = true;

            Thread captureThread = new Thread(() -> {
                try {
                    while (capturing) {
                        Frame frame = grabber.grab();
                        if (frame != null) {
                            BufferedImage bufferedImage = converter.convert(frame);
                            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                            Platform.runLater(() -> camera.setImage(image));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            captureThread.setDaemon(true);
            captureThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
