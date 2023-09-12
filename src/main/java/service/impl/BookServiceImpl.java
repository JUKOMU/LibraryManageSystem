package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.Book;
import domain.User;
import service.BookService;
import utils.Result;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public Result findBookByAuthor(String author) {
        List<Book> books = bookDao.selectBookByAuthor(author);
        //定义Result对象
        Result result=new Result();
        //判断一下集合中有没有数据
        if(books!=null) {
            result.setData(books);
        }else {
            result.setCode(-1);  //-1 是一个状态码
        }
        return result;
    }

    @Override
    public Result findBookByName(String name) {
        List<Book> books = bookDao.selectBookByName(name);
        //定义Result对象
        Result result=new Result();
        //判断一下集合中有没有数据
        if(books!=null) {
            result.setData(books);
        }else {
            result.setCode(-1);  //-1 是一个状态码
        }
        return result;
    }

    @Override
    public Result findBookByNameByAuthor(String name, String author) {
        List<Book> books = bookDao.selectBookByNameByAuthor(name,author);
        //定义Result对象
        Result result=new Result();
        //判断一下集合中有没有数据
        if(books!=null) {
            result.setData(books);
        }else {
            result.setCode(-1);  //-1 是一个状态码
        }
        return result;
    }

    @Override
    public Result findBookByBarCode(Book book) {
        Book book1=bookDao.selectBookByBarCode(book.getBarcode());
        System.out.println(book.getBarcode());
        //产生result对象
        Result result=new Result();
        //判断一下user1 是否为空
        if(book1!=null) {
            //保存数据到result
            result.setData(book1);
            //返回
        }else {
            result.setCode(-1);
        }
        return result;
    }

    @Override
    public Result findAllBook() {
        List<Book> books = bookDao.selectAllBook();
        //定义Result对象
        Result result=new Result();
        //判断一下集合中有没有数据
        if(books!=null) {
            result.setData(books);
        }else {
            result.setCode(-1);  //-1 是一个状态码
        }
        return result;
    }
}
