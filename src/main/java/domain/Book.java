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
    private String name;
    private String author;
    private Image coverImage;
    // 总馆藏
    private Integer totalLibraryCopies;
    // 馆藏剩余
    private Integer availableCopies;
    public Book() {
    }
    public Book(String name, String author, Image coverImage, Integer totalLibraryCopies, Integer availableCopies) {
        this.name = name;
        this.author = author;
        this.coverImage = coverImage;
        this.totalLibraryCopies = totalLibraryCopies;
        this.availableCopies = availableCopies;
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

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }
    public Integer getTotalLibraryCopies() {
        return totalLibraryCopies;
    }

    public void setTotalLibraryCopies(Integer totalLibraryCopies) {
        this.totalLibraryCopies = totalLibraryCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", coverImage=" + coverImage +
                ", totalLibraryCopies=" + totalLibraryCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
