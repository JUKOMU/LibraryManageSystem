package service;

import domain.Book;
import utils.Result;

public interface BookService {

    /**
     * 查询所有书本
     */
    Result findAllBook();
    /**
     * 通过作者查询书本
     */
    Result findBookByAuthor(String author);
    /**
     * 通过书名查询书本
     */
    Result findBookByName(String name);
    /**
     * 通过书名和作者查询书本
     */
    Result findBookByNameByAuthor(String name,String author);

    Result findBookByBarCode(Book book);
}
