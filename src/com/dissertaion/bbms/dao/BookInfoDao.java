package com.dissertaion.bbms.dao;

import com.dissertaion.bbms.vo.BookInfo;

import java.util.List; /**
 * @author TOAOK
 * @version 1.0  2017/10/19.
 */
public interface BookInfoDao {
    void saveOrUpdate(List<BookInfo> list);

    void saveOrUpdate(BookInfo bookInfo);
}
