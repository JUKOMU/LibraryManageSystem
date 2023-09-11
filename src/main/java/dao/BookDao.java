package dao;

import domain.Book;

import java.util.List;

/**
 * 检索图书接口
 */
public interface BookDao {
    /**
     * 根据书名检索
     */
    List<Book> selectBookByName(String name);

    /**
     * 根据作者检索
     */
    List<Book> selectBookByAuthor(String author);

    /**
     * 根据书名和作者联合检索
     */
    List<Book> selectBookByNameByAuthor(String name,String author);
}
