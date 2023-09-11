package dao.impl;

import dao.BookDao;
import domain.Book;
import utils.DataSourceUtils;

public class BookDaoImpl extends DataSourceUtils implements BookDao {
    @Override
    public Book selectBookByName(String name) {
        return null;
    }

    @Override
    public Book selectBookByAuthor(String author) {
        return null;
    }
}
