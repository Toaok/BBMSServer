package com.dissertaion.bbms.dao.impl;

import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.dao.SubscribeDao;
import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.BookInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/10/18.
 */
public class SubscribeDaoImpl extends BaseDao implements SubscribeDao {
    @Override
    public List<BookInfo> query(int accountId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Account account = session.load(Account.class, accountId);
        Query query = session.createQuery("select bookinfo from Subscribe subscribe ,BookInfo bookinfo where subscribe.account=?and subscribe.bookInfo=bookinfo");
        query.setParameter(0, account);
        List<BookInfo> subscribes = query.list();
        transaction.commit();
        session.close();
        return subscribes;
    }

    @Override
    public List<BookInfo> query(Account account) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select bookinfo from Subscribe subscribe ,BookInfo bookinfo where subscribe.account=?and subscribe.bookInfo=bookinfo");

        query.setParameter(0, account);
        List<BookInfo> subscribes = query.list();
        transaction.commit();
        session.close();
        return subscribes;
    }

    @Override
    public int delete(int accountId, String bookIsbn) {
        int status = -1;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Account account = session.load(Account.class, accountId);

        Query bookInfoQuery = session.createQuery("from BookInfo bookinfo where bookinfo.bookIsbn=?");
        bookInfoQuery.setParameter(0, bookIsbn);
        bookInfoQuery.setMaxResults(1);
        List list = bookInfoQuery.list();

        if (list.size() > 0) {
            BookInfo bookInfo = (BookInfo) list.get(0);
            Query deleteQuery = session.createQuery("delete from Subscribe where account=?and bookInfo=?");
            deleteQuery.setParameter(0, account);
            deleteQuery.setParameter(1, bookInfo);
            if (deleteQuery.executeUpdate() > 0) {
                status = 1;
            } else {
                status = 0;
            }
        }
        transaction.commit();
        session.close();

        return status;
    }

    @Override
    public int append(int accountId, String bookIsbn) {

        int status = -1;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        //根据account_id获取用户账户信息
        Account account = session.load(Account.class, accountId);

        //根据book_ISBN查询图书信息
        Query bookInfoQuery = session.createQuery("from BookInfo where bookIsbn=?");
        bookInfoQuery.setParameter(0, bookIsbn);
        bookInfoQuery.setMaxResults(1);
        List<BookInfo> list = bookInfoQuery.list();
        if (list.size() > 0) {
            BookInfo bookInfo = list.get(0);
            //将图书设值到account中
            if (account.addBookInfo(bookInfo)) {
                status = 1;
            } else {
                status = 0;
            }
        }
        transaction.commit();
        session.close();
        return status;
    }
}
