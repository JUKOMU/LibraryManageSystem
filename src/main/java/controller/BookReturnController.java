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
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class BookReturnController {

    @FXML
    private ResourceBundle resources;

    public void setCapturing(boolean capturing) {
        this.capturing = capturing;
    }

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

    private FrameGrabber grabber;
    private Java2DFrameConverter converter;

    private boolean capturing = false;

    @FXML
    void initialize() {
        assert camera_return != null : "fx:id=\"camera_return\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert book_view != null : "fx:id=\"book_view\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert book_author != null : "fx:id=\"book_author\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert book_name != null : "fx:id=\"book_name\" was not injected: check your FXML file 'bookReturn.fxml'.";
        assert tip_return != null : "fx:id=\"tip_return\" was not injected: check your FXML file 'bookReturn.fxml'.";

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
                            Platform.runLater(() -> camera_return.setImage(image));
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
        ControllerManager.controllers.put("bookReturnController",this);
    }
}
