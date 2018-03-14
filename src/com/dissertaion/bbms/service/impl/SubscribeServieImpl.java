package com.dissertaion.bbms.service.impl;

import com.dissertaion.bbms.dao.SubscribeDao;
import com.dissertaion.bbms.service.SubscribeService;
import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.BookInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/10/18.
 */
public class SubscribeServieImpl implements SubscribeService {

    private SubscribeDao subscribeDao;

    @Override
    public List<BookInfo> query(int accountId) {
        return subscribeDao.query(accountId);
    }

    @Override
    public List<BookInfo> query(Account account) {
        return subscribeDao.query(account);

    }


    @Override
    public int delete(int accountId, String bookIsbn) {
        return subscribeDao.delete(accountId,bookIsbn);
    }

    @Override
    public int append(int accountId, String bookIsbn) {
        return subscribeDao.append(accountId,bookIsbn);
    }

    public SubscribeDao getSubscribeDao() {
        return subscribeDao;
    }

    public void setSubscribeDao(SubscribeDao subscribeDao) {
        this.subscribeDao = subscribeDao;
    }
}
