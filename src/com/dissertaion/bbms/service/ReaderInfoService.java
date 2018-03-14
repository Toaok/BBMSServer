package com.dissertaion.bbms.service;

import com.dissertaion.bbms.vo.ReaderInfo;

/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public interface ReaderInfoService {

    void saveOrUpdate(String readerId, ReaderInfo readerInfo);

    int delete(String readerId);

    ReaderInfo query(String readerId);
}
