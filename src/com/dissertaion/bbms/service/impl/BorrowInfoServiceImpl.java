package com.dissertaion.bbms.service.impl;

import com.dissertaion.bbms.dao.BorrowInfoDao;
import com.dissertaion.bbms.service.BorrowInfoService;
import com.dissertaion.bbms.vo.BorrowInfo;
import com.dissertaion.bbms.vo.ReaderInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public class BorrowInfoServiceImpl implements BorrowInfoService {

    private BorrowInfoDao borrowInfoDao;

    @Override
    public int add(String readerId, String bookIsbn) {
        return borrowInfoDao.add(readerId,bookIsbn);
    }

    @Override
    public int delete(String readerId, String bookIsbn) {
        return borrowInfoDao.delete(readerId,bookIsbn);
    }

    @Override
    public List<BorrowInfo> query(ReaderInfo readerInfo) {
        return borrowInfoDao.query(readerInfo);
    }

    @Override
    public List<BorrowInfo> query(String readerId) {
        return borrowInfoDao.query(readerId);
    }


    public BorrowInfoDao getBorrowInfoDao() {
        return borrowInfoDao;
    }

    public void setBorrowInfoDao(BorrowInfoDao borrowInfoDao) {
        this.borrowInfoDao = borrowInfoDao;
    }
}
