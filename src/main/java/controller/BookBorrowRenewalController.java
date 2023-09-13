package controller;

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
import utils.FaceUtil;
import utils.ImageUtil;
import utils.Result;
import zxing.BarCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static camera.CameraCaptureApp.doExecuteFrame;
import static utils.BarCodeUtil.getBarCodePos;


public class BookBorrowRenewalController {

    private static int[][] barcodePos;
    private static double[][] facePos;
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
    @FXML
    private Label success;
    private FrameGrabber grabber;
    private Java2DFrameConverter converter;
    private boolean capturing = true;

    private boolean isRecognizedBook = false;
    private boolean isRecognizedFace = false;


    public static void main(String[] args) {
        try {
            // 调用CMD命令
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "python E:\\Java\\LibraryManageSystem\\src\\main\\main.py 1.jpg"); // /c参数表示执行后关闭CMD窗口
            processBuilder.redirectErrorStream(true); // 将错误输出流与标准输出流合并
            Process process = processBuilder.start();

            // 获取命令输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK")); // 设置编码为GBK
            String line;

            // 定义正则表达式来匹配坐标信息的行
            Pattern pattern = Pattern.compile("(\\d+) (\\d+)");

            barcodePos = new int[4][2];
            int t = 0;
            while ((line = reader.readLine()) != null) {
                // 使用正则表达式匹配坐标信息的行
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // 提取坐标信息
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    barcodePos[t][0] = x;
                    barcodePos[t][1] = y;
                    t = t + 1;


                    // 在这里处理坐标信息，可以将它们存储到数据结构中或进行其他操作
                    System.out.println("X: " + x + ", Y: " + y);
                } else {
                    // 输出警告信息或其他提示信息
                    System.out.println(line);
                }
            }
            System.out.println(barcodePos);

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
                        Platform.runLater(() -> camera.setImage(image));
                        // 创建两个Date对象
                        Date date2 = new Date(); // 当前时间
                        // 计算两个时间的间隔（毫秒）
                        long milliseconds = date2.getTime() - currentTime.getTime();
                        // 将毫秒转换为秒
                        long seconds = milliseconds / 1000;

                        Thread barCodeThread = new Thread(() -> {
                            showBarCodePos();
                            isRecognizedBook = recognizeBarCode();
                        });

                        Thread faceThread = new Thread(() -> {
                            showFacePos();
                            try {
                                isRecognizedFace = recognizeFace();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        // 先检测条形码，再检测人脸
                        if (seconds > 1.2) {
                            if (!isRecognizedBook && !isRecognizedFace) {
                                barCodeThread.start();
                            } else if (isRecognizedBook && !isRecognizedFace) {
                                faceThread.start();
                            } else if (isRecognizedBook && isRecognizedFace) {
                                showFacePos();
                            }
                            currentTime  = new Date();
                        }
                    }
                }
            });

            Platform.runLater(() -> {
                captureThread.setDaemon(true);
                currentTime = new Date();
                captureThread.start();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ControllerManager.controllers.put("bookBorrowRenewalController", this);
    }

    private void showFacePos() {
        try {
            doExecuteFrame(grabber.grab(), "src/main/resources/img/1.jpg");
            String imageName = "E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\1.jpg";
            String imageBase64 = ImageUtil.convertImageToBase64Str(imageName);
            facePos = FaceUtil.faceDetect(imageBase64);
            drawFacePos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void drawFacePos() {
        try {
            // 读取PNG图片
            BufferedImage image2 = ImageIO.read(new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\Pos.png"));

            // 创建Graphics2D对象以绘制在图像上
            Graphics2D g2d = image2.createGraphics();

            // 设置线条颜色和宽度
            g2d.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(2)); // 设置线条宽度

            // 绘制线条
            if (facePos != null) {
                for (int i = 0; i < 4; i++) {
                    if (i < 3) {
                        g2d.drawLine((int) facePos[i][0], (int) facePos[i][1], (int) facePos[i + 1][0], (int) facePos[i + 1][1]);
                    } else {
                        g2d.drawLine((int) facePos[i][0], (int) facePos[i][1], (int) facePos[0][0], (int) facePos[0][1]);
                    }
                }
                // 释放Graphics2D对象
                g2d.dispose();

                // 保存修改后的图像
                ImageIO.write(image2, "PNG", new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\output.png"));

                System.out.println("线条已绘制到图像上并保存成功！");
                Image img = new Image("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\output.png");
                camera2.setImage(img);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean recognizeFace() throws IOException {
        String imageName = "E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\1.jpg";
        String imageBase64 = ImageUtil.convertImageToBase64Str(imageName);
        return FaceUtil.faceMatch(imageBase64);
    }


    /**
     * 显示条形码检测框
     */
    private void showBarCodePos() {
        try {
            doExecuteFrame(grabber.grab(), "src/main/resources/img/1.jpg");
            barcodePos = getBarCodePos();
            drawBarCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制条形码的检测框
     */
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
                    g2d.drawLine(barcodePos[i][0], barcodePos[i][1], barcodePos[i + 1][0], barcodePos[i + 1][1]);
                } else {
                    g2d.drawLine(barcodePos[i][0], barcodePos[i][1], barcodePos[0][0], barcodePos[0][1]);
                }
            }

            // 释放Graphics2D对象
            g2d.dispose();

            // 保存修改后的图像
            ImageIO.write(image2, "PNG", new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\output.png"));

            System.out.println("线条已绘制到图像上并保存成功！");
            Image img = new Image("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\output.png");
            camera2.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取条形码，并在数据库查找，成功则显示书籍信息
     *
     * @return
     */
    private boolean recognizeBarCode() {
        String code = BarCode.readCode(new File("E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\1.jpg"));
        if (code != null && !isRecognizedBook) {
            BookService bookService = new BookServiceImpl();

            Book book = new Book();
            book.setBarcode(code);
            Result result = bookService.findBookByBarCode(book);
            if (result.getCode() == 200) {
                //System.out.println("ok");
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
            }
        }
        return false;
    }
}
