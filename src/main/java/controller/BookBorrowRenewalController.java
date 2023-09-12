package controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import domain.Book;
import domain.User;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import service.BookService;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;
import utils.Result;
import zxing.BarCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static camera.CameraCaptureApp.doExecuteFrame;


public class BookBorrowRenewalController {

    private static int[][] num;
    public Date currentTime;
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
    @FXML
    private ImageView camera2;
    private FrameGrabber grabber;
    private Java2DFrameConverter converter;
    private boolean capturing = true;

    private boolean isRecognized = false;

    public static void main(String[] args) {
        try {
            // 调用CMD命令
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "python E:\\Java\\LibraryManageSystem\\src\\main\\main.py 1.jpg"); // /c参数表示执行后关闭CMD窗口
            processBuilder.redirectErrorStream(true); // 将错误输出流与标准输出流合并
            Process process = processBuilder.start();

            /*
            // 获取命令输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK")); // 设置编码为GBK
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
             */


            // 获取命令输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK")); // 设置编码为GBK
            String line;

            // 定义正则表达式来匹配坐标信息的行
            Pattern pattern = Pattern.compile("(\\d+) (\\d+)");

            num = new int[4][2];
            int t = 0;
            while ((line = reader.readLine()) != null) {
                // 使用正则表达式匹配坐标信息的行
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // 提取坐标信息
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    num[t][0] = x;
                    num[t][1] = y;
                    t = t + 1;


                    // 在这里处理坐标信息，可以将它们存储到数据结构中或进行其他操作
                    System.out.println("X: " + x + ", Y: " + y);
                } else {
                    // 输出警告信息或其他提示信息
                    System.out.println(line);
                }
            }
            System.out.println(num);

            // 等待命令执行完成
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

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

            Platform.runLater(() -> {
                Thread captureThread = new Thread(() -> {
                    try {
                        while (capturing) {
                            Frame frame = grabber.grab();
                            if (frame != null) {
                                BufferedImage bufferedImage = converter.convert(frame);
                                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                                Platform.runLater(() -> camera.setImage(image));
                                // 创建两个Date对象
                                Date date2 = new Date(); // 当前时间
                                // 计算两个时间的间隔（毫秒）
                                long milliseconds = date2.getTime() - currentTime.getTime();
                                // 将毫秒转换为秒
                                long seconds = milliseconds / 1000;
                                if (seconds > 1) {
                                    Platform.runLater(() -> {
                                        try {
                                            doExecuteFrame(grabber.grab(), "src/main/resources/img/1.jpg");
                                            try {
                                                // 调用CMD命令
                                                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "python E:\\Java\\LibraryManageSystem\\src\\main\\main.py 1.jpg"); // /c参数表示执行后关闭CMD窗口
                                                processBuilder.redirectErrorStream(true); // 将错误输出流与标准输出流合并
                                                Process process = processBuilder.start();
                                                InputStream inputStream = process.getInputStream();
                                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK")); // 设置编码为GBK
                                                String line;
                                                // 定义正则表达式来匹配坐标信息的行
                                                Pattern pattern = Pattern.compile("(\\d+) (\\d+)");
                                                num = new int[4][2];
                                                int t = 0;
                                                while ((line = reader.readLine()) != null) {
                                                    // 使用正则表达式匹配坐标信息的行
                                                    Matcher matcher = pattern.matcher(line);
                                                    if (matcher.find()) {
                                                        // 提取坐标信息
                                                        int x = Integer.parseInt(matcher.group(1));
                                                        int y = Integer.parseInt(matcher.group(2));
                                                        num[t][0] = x;
                                                        num[t][1] = y;
                                                        t = t + 1;
                                                        // 在这里处理坐标信息，可以将它们存储到数据结构中或进行其他操作
                                                        System.out.println("X: " + x + ", Y: " + y);
                                                    } else {
                                                        // 输出警告信息或其他提示信息
                                                        System.out.println(line);
                                                    }
                                                }
                                                System.out.println(num);
                                                // 等待命令执行完成
                                                process.waitFor();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                // 读取PNG图片
                                                BufferedImage image2 = ImageIO.read(new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\barPos.png"));

                                                // 创建Graphics2D对象以绘制在图像上
                                                Graphics2D g2d = image2.createGraphics();

                                                // 设置线条颜色和宽度
                                                g2d.setColor(Color.GREEN);
                                                g2d.setStroke(new BasicStroke(2)); // 设置线条宽度

                                                // 绘制线条（示例：从点(50, 50)到点(200, 200)）
                                                //g2d.drawLine(50, 50, 200, 200);
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
                                                camera2.setImage(img);

                                                String code = BarCode.readCode(new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\1.jpg"));
                                                if (code != null && !isRecognized) {
                                                    isRecognized = true;
                                                    BookService bookService = new BookServiceImpl();

                                                    Book book = new Book();
                                                    book.setBarcode(code);
                                                    try {
                                                        Result result = bookService.findBookByBarCode(book);
                                                        if (result.getCode() == 200) {
                                                            System.out.println("ok");
                                                            //正确

                                                            Book book2 = (Book) result.getData();
                                                            book_name.setText(book2.getName());
                                                            book_author.setText(book2.getAuthor());
                                                            Image image3 = new Image("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\database_source\\" + book2.getId() + ".jpg");
                                                            book_view.setImage(image3);
                                                        }else {isRecognized = false;}
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                    currentTime = new Date();
                                }
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                captureThread.setDaemon(true);
                currentTime = new Date();
                captureThread.start();
                    });

        }catch (Exception e) {
            e.printStackTrace();
        }
    ControllerManager.controllers.put("bookBorrowRenewalController",this);
    }

}
