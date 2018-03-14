package com.dissertaion.bbms.dao.impl;

import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.dao.ReaderInfoDao;
import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.ReaderInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public class ReaderInfoDaoImpl extends BaseDao implements ReaderInfoDao {
    @Override
    public void saveOrUpdate(String readerId, ReaderInfo readerInfo) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query queryAccount = session.createQuery("from Account where accountId=?");
        queryAccount.setParameter(0, readerId);
        session.saveOrUpdate(readerInfo);
        transaction.commit();
        session.close();
    }

    @Override
    public int delete(String readerId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from ReaderInfo where readerId=?");

        query.setParameter(0, readerId);
        int status = query.executeUpdate();

        transaction.commit();
        session.close();

        return status;
    }

    @Override
    public ReaderInfo query(String readerId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
//        Query queryAccount = session.createQuery("from Account where account=?");
//        queryAccount.setParameter(0, readerId);
//        queryAccount.setMaxResults(1);
//        Account account = (Account) queryAccount.list().get(0);
        Query query = session.createQuery("from ReaderInfo where readerId=?");
        query.setParameter(0, readerId);
        query.setMaxResults(1);
        ReaderInfo readerInfo = (ReaderInfo) query.list().get(0);
        transaction.commit();
        session.close();

        return readerInfo;
    }
}
