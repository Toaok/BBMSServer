package com.dissertaion.bbms.dao.impl;

import com.dissertaion.bbms.dao.AccountDao;
import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.vo.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


/**
 * @author TOAOK
 * @version 1.0  2017/10/16.
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {
    @Override
    public Account query(Account account) {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("from Account where account=? and password=?");
        query.setParameter(0,account.getAccount());
        query.setParameter(1,account.getPassword());
        query.setMaxResults(1);
        List result=query.list();
        account=null;
        if(result.size()>0){
            account= (Account) result.get(0);
        }
        transaction.commit();
        session.close();
        return account;
    }
}
