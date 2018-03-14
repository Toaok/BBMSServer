package com.dissertaion.bbms.service;

import com.dissertaion.bbms.vo.Book;

/**
 * @author TOAOK
 * @version 1.0  2017/9/28.
 */
public interface BookService {

    //将图书信息存入数据库；
    void saveOrUpdate(Book book);
    //根据键值获取数据
    String getValue(String path, String defaultValue);
}
