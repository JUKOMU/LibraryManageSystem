package domain;

import javafx.scene.image.Image;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 书籍实体类
 */
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String barcode;
    private Image cover_image;
    // 总馆藏
    private Integer total_library_copies;
    // 馆藏剩余
    private Integer available_copies;
    public Book() {
    }

    public Book(Integer id, String name, String author, String barcode, Image cover_image, Integer total_library_copies, Integer available_copies) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.cover_image = cover_image;
        this.total_library_copies = total_library_copies;
        this.available_copies = available_copies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Image getCover_image() {
        return cover_image;
    }

    public void setCover_image(Image cover_image) {
        this.cover_image = cover_image;
    }

    public Integer getTotal_library_copies() {
        return total_library_copies;
    }

    public void setTotal_library_copies(Integer total_library_copies) {
        this.total_library_copies = total_library_copies;
    }

    public Integer getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(Integer available_copies) {
        this.available_copies = available_copies;
    }
}