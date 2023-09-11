package dao;

import domain.Book;

/**
 * 检索图书接口
 */
public interface BookDao {
    /**
     * 根据书名检索
     */
    Book selectBookByName(String name);

    /**
     * 根据作者检索
     */
    Book selectBookByAuthor(String author);
}
