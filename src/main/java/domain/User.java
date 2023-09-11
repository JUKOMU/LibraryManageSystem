package domain;

import java.awt.*;

/**
 * 用户实体类
 */
public class User {
    public static final int ADMINISTRATOR = 0; // 管理员账号为0
    public static final int NORMAL_USER = 1;   // 普通用户为1
    private String username;
    private String password;
    private Image userFaceImage; // 用户人脸信息
    private int userRole;        // 用户权限
    private int borrowedCount;  // 总借阅次数
    private int currentBorrowedBooks;   // 正在借阅的书籍数量
    public User() {
    }
    public User(String username, String password, Image userFaceImage, int userRole, int borrowedCount, int currentBorrowedBooks) {
        this.username = username;
        this.password = password;
        this.userFaceImage = userFaceImage;
        this.userRole = userRole;
        this.borrowedCount = borrowedCount;
        this.currentBorrowedBooks = currentBorrowedBooks;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public int getCurrentBorrowedBooks() {
        return currentBorrowedBooks;
    }

    public void setCurrentBorrowedBooks(int currentBorrowedBooks) {
        this.currentBorrowedBooks = currentBorrowedBooks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getUserFaceImage() {
        return userFaceImage;
    }

    public void setUserFaceImage(Image userFaceImage) {
        this.userFaceImage = userFaceImage;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userFaceImage=" + userFaceImage +
                ", userRole=" + userRole +
                ", borrowedCount=" + borrowedCount +
                ", currentBorrowedBooks=" + currentBorrowedBooks +
                '}';
    }
}
