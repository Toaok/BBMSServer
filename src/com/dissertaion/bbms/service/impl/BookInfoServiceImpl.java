package com.dissertaion.bbms.service.impl;

import com.dissertaion.bbms.dao.BookDao;
import com.dissertaion.bbms.dao.BookInfoDao;
import com.dissertaion.bbms.service.BookInfoService;
import com.dissertaion.bbms.vo.BookInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/10/19.
 */
public class BookInfoServiceImpl implements BookInfoService {
    private BookInfoDao bookInfoDao;

    @Override
    public void saveOrUpdate(List<BookInfo> list) {
        bookInfoDao.saveOrUpdate(list);
    }

    @Override
    public void saveOrUpdate(BookInfo bookInfo) {
        bookInfoDao.saveOrUpdate(bookInfo);
    }

    public BookInfoDao getBookInfoDao() {
        return bookInfoDao;
    }

    public void setBookInfoDao(BookInfoDao bookInfoDao) {
        this.bookInfoDao = bookInfoDao;
    }
}
