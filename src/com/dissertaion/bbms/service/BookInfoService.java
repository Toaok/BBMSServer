package com.dissertaion.bbms.service;

import com.dissertaion.bbms.vo.BookInfo;

import java.util.List; /**
 * @author TOAOK
 * @version 1.0  2017/10/19.
 */
public interface BookInfoService {
    void saveOrUpdate(List<BookInfo> list);

    void saveOrUpdate(BookInfo bookInfo);
}
