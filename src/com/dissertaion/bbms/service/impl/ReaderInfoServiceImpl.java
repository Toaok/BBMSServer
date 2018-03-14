package com.dissertaion.bbms.service.impl;

import com.dissertaion.bbms.dao.ReaderInfoDao;
import com.dissertaion.bbms.service.ReaderInfoService;
import com.dissertaion.bbms.vo.ReaderInfo;

/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public class ReaderInfoServiceImpl implements ReaderInfoService {

    private ReaderInfoDao readerInfoDao;

    @Override
    public void saveOrUpdate(String readerId, ReaderInfo readerInfo) {
        readerInfoDao.saveOrUpdate(readerId,readerInfo);
    }

    @Override
    public int delete(String readerId) {
        return readerInfoDao.delete(readerId);
    }

    @Override
    public ReaderInfo query(String readerId) {
        return readerInfoDao.query(readerId);
    }

    public ReaderInfoDao getReaderInfoDao() {
        return readerInfoDao;
    }

    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }
}
