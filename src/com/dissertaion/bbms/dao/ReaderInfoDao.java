package com.dissertaion.bbms.dao;

import com.dissertaion.bbms.vo.ReaderInfo; /**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public interface ReaderInfoDao {
    void saveOrUpdate(String readerId, ReaderInfo readerInfo);

    int delete(String readerId);

    ReaderInfo query(String readerId);
}
