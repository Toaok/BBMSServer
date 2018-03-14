package com.dissertaion.bbms.service;

import com.dissertaion.bbms.vo.BorrowInfo;
import com.dissertaion.bbms.vo.ReaderInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public interface BorrowInfoService {

    int add(String readerId,String bookIsbn);

    int delete(String readerId,String bookIsbn);

    List<BorrowInfo> query(ReaderInfo readerInfo);

    List<BorrowInfo> query(String readerId);
}
