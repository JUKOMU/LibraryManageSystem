package domain;

/**
 * 用户实体类
 */
public class User {
    public static final int ADMINISTRATOR = 0; // 管理员账号为0
    public static final int NORMAL_USER = 1;   // 普通用户为1
    private int id;
    private String user_name;
    private String password;
    private String user_face_image; // 用户人脸信息
    private int user_role;        // 用户权限
    private int borrowed_count;  // 总借阅次数
    private int current_borrowed_books;   // 正在借阅的书籍数量
    public User() {
    }

    public User(int id, String user_name, String password, String user_face_image, int user_role, int borrowed_count, int current_borrowed_books) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.user_face_image = user_face_image;
        this.user_role = user_role;
        this.borrowed_count = borrowed_count;
        this.current_borrowed_books = current_borrowed_books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_face_image() {
        return user_face_image;
    }

    public void setUser_face_image(String user_face_image) {
        this.user_face_image = user_face_image;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public int getBorrowed_count() {
        return borrowed_count;
    }

    public void setBorrowed_count(int borrowed_count) {
        this.borrowed_count = borrowed_count;
    }

    public int getCurrent_borrowed_books() {
        return current_borrowed_books;
    }

    public void setCurrent_borrowed_books(int current_borrowed_books) {
        this.current_borrowed_books = current_borrowed_books;
    }
}
