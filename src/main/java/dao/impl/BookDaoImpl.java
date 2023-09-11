package dao.impl;

import dao.BookDao;
import domain.Book;
import utils.DataSourceUtils;

import java.util.List;

public class BookDaoImpl extends DataSourceUtils implements BookDao {
    @Override
    public List<Book> selectBookByName(String name) {
        String sql = "SELECT * FROM books WHERE name = '" + name;
        return executeQueryList(sql,Book.class);
    }

    @Override
    public List<Book> selectBookByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author = '" + author;
        return executeQueryList(sql,Book.class);
    }

    @Override
    public List<Book> selectBookByNameByAuthor(String name,String author) {
        String sql = "SELECT * FROM books WHERE name = " + name + " AND author = " + author;
        return executeQueryList(sql,Book.class);
    }

}
