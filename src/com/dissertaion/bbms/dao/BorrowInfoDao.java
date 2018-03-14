package com.dissertaion.bbms.dao;

import com.dissertaion.bbms.vo.BorrowInfo;
import com.dissertaion.bbms.vo.ReaderInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public interface BorrowInfoDao  {
    int add(String readerId, String bookIsbn);

    int delete(String readerId, String bookIsbn);

    List<BorrowInfo> query(ReaderInfo readerInfo);

    List<BorrowInfo> query(String readerId);
}
