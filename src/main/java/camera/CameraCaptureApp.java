package camera;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CameraCaptureApp extends Application {

    private FrameGrabber grabber;
    private Java2DFrameConverter converter;
    private ImageView imageView;
    private volatile boolean capturing = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        imageView = new ImageView();
        root.setCenter(imageView);

        Button startButton = new Button("Start Capture");
        startButton.setOnAction(e -> startCapture());
        Button stopButton = new Button("Stop Capture");
        stopButton.setOnAction(e -> stopCapture());
        Button getPhotograph = new Button("GetPhotograph");
        getPhotograph.setOnAction(e -> getPhotograph());
        // 创建一个水平布局来放置按钮
        HBox buttonBox = new HBox(10); // 间距10
        buttonBox.getChildren().addAll(startButton, stopButton, getPhotograph);
        buttonBox.setAlignment(Pos.CENTER);

        root.setBottom(buttonBox);



        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Camera Capture");
        primaryStage.setOnCloseRequest(e -> stopCapture());
        primaryStage.show();
    }

    private void startCapture() {
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
                            Platform.runLater(() -> imageView.setImage(image));
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


    private void stopCapture() {
        if (capturing) {
            try {
                capturing = false;
                grabber.stop();
                grabber.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            imageView.setImage(null);
            //Platform.exit();
        }
    }

    private void getPhotograph() {
        if (capturing) {
            try {
                doExecuteFrame(grabber.grab(),"1.jpg");
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void doExecuteFrame(Frame f, String targetFileName) {
        if (null ==f ||null ==f.image) {
            return;
        }
        Java2DFrameConverter converter =new Java2DFrameConverter();
        targetFileName=targetFileName.replace("mp4", "jpg");
        System.out.println("targetFileName"+targetFileName);
        String imageMat ="jpg";
        String FileName =targetFileName;
        BufferedImage bi =converter.getBufferedImage(f);
        File output =new File(FileName);
        try {
            ImageIO.write(bi,imageMat,output);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
