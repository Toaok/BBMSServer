package com.dissertaion.bbms.service.impl;

import com.dissertaion.bbms.dao.AccountDao;
import com.dissertaion.bbms.service.AccountService;
import com.dissertaion.bbms.vo.Account;

/**
 * @author TOAOK
 * @version 1.0  2017/10/16.
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    @Override
    public Account query(Account account) {
        return accountDao.query(account);
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
