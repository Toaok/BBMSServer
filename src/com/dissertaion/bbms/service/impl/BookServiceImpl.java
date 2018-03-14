package com.dissertaion.bbms.service.impl;

import com.dissertaion.bbms.dao.BookDao;
import com.dissertaion.bbms.dao.impl.BookDaoImpl;
import com.dissertaion.bbms.service.BookService;
import com.dissertaion.bbms.vo.Book;

/**
 * @author TOAOK
 * @version 1.0  2017/9/28.
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void saveOrUpdate(Book book) {
        bookDao.saveOrUpdate(book);
    }

    @Override
    public String getValue(String path, String defaultValue) {
        return bookDao.getValue(path, defaultValue);
    }
}
