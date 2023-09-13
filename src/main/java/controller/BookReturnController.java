package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import domain.Book;
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
import service.BookService;
import service.impl.BookServiceImpl;
import utils.Result;
import zxing.BarCode;

import javax.imageio.ImageIO;

import static camera.CameraCaptureApp.doExecuteFrame;
import static utils.BarCodeUtil.getBarCodePos;

public class BookReturnController {

    private static int[][] num;
    public Date currentTime;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView camera_return;
    @FXML
    private ImageView camera_return2;
    @FXML
    private ImageView book_view;
    @FXML
    private Label book_author;
    @FXML
    private Label book_name;
    @FXML
    private Label tip_return;
    @FXML
    private Label success;
    private FrameGrabber grabber;
    private Java2DFrameConverter converter;
    private boolean capturing = false;
    private boolean isRecognized = false;

    public void setCapturing(boolean capturing) {
        this.capturing = capturing;
    }

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
                while (capturing) {
                    Frame frame;
                    try {
                        frame = grabber.grab();
                    } catch (FrameGrabber.Exception e) {
                        throw new RuntimeException(e);
                    }
                    if (frame != null) {
                        BufferedImage bufferedImage = converter.convert(frame);
                        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                        Platform.runLater(() -> camera_return.setImage(image));
                        // 创建两个Date对象
                        Date date2 = new Date(); // 当前时间
                        // 计算两个时间的间隔（毫秒）
                        long milliseconds = date2.getTime() - currentTime.getTime();
                        // 将毫秒转换为秒
                        long seconds = milliseconds / 1000;
                        if (seconds > 1) {
                            showBarCodePos();
                            recognizeBarCode();
                        }
                    }
                }
            });

            Platform.runLater(() -> {
                captureThread.setDaemon(true);
                currentTime = new Date();
                captureThread.start();
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
        ControllerManager.controllers.put("bookReturnController",this);
    }

    private void showBarCodePos(){
        try {
            doExecuteFrame(grabber.grab(), "src/main/resources/img/1.jpg");
            num = getBarCodePos();
            drawBarCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentTime = new Date();
    }

    private void drawBarCode() {
        try {
            // 读取PNG图片
            BufferedImage image2 = ImageIO.read(new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\Pos.png"));

            // 创建Graphics2D对象以绘制在图像上
            Graphics2D g2d = image2.createGraphics();

            // 设置线条颜色和宽度
            g2d.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(2)); // 设置线条宽度

            // 绘制线条
            for (int i = 0; i < 4; i++) {
                if (i < 3) {
                    g2d.drawLine(num[i][0], num[i][1], num[i + 1][0], num[i + 1][1]);
                } else {
                    g2d.drawLine(num[i][0], num[i][1], num[0][0], num[0][1]);
                }
            }

            // 释放Graphics2D对象
            g2d.dispose();

            // 保存修改后的图像
            ImageIO.write(image2, "PNG", new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\output.png"));

            System.out.println("线条已绘制到图像上并保存成功！");
            Image img = new Image("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\output.png");
            camera_return2.setImage(img);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean recognizeBarCode() {
        String code = BarCode.readCode(new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\1.jpg"));
        if (code != null && !isRecognized) {
            isRecognized = true;
            BookService bookService = new BookServiceImpl();

            Book book = new Book();
            book.setBarcode(code);
            Result result = bookService.findBookByBarCode(book);
            if (result.getCode() == 200) {
                System.out.println("ok");
                //正确
                Book book2 = (Book) result.getData();
                Image image3 = new Image("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\database_source\\" + book2.getId() + ".jpg");

                Platform.runLater(() -> {
                    book_name.setText(book2.getName());
                    book_author.setText(book2.getAuthor());
                    book_view.setImage(image3);
                });

                //success.setText("借书成功");
                return true;
            }else {isRecognized = false;}
        }
        return false;
    }
}
