package com.dissertaion.bbms.dao.impl;

import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.dao.BorrowInfoDao;
import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.BookInfo;
import com.dissertaion.bbms.vo.BorrowInfo;
import com.dissertaion.bbms.vo.ReaderInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/11/9.
 */
public class BorrowInfoDaoImpl extends BaseDao implements BorrowInfoDao {
    @Override
    public int add(String readerId, String bookIsbn) {
        int status = -1;

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        //根据readerId获取用户账户信息
        Query readerInfoQuery = session.createQuery("from ReaderInfo where readerId=?");
        readerInfoQuery.setParameter(0, readerId);
        readerInfoQuery.setMaxResults(1);
        List<ReaderInfo> readers = readerInfoQuery.list();

        //根据book_ISBN查询图书信息
        Query bookInfoQuery = session.createQuery("from BookInfo  where bookIsbn=?");
        bookInfoQuery.setParameter(0, bookIsbn);
        bookInfoQuery.setMaxResults(1);
        List<BookInfo> books = bookInfoQuery.list();
        if (readers.size() > 0 && books.size() > 0) {
            ReaderInfo readerInfo = readers.get(0);
            BookInfo bookInfo = books.get(0);
            if (readerInfo.addBookInfo(bookInfo)) {
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
    public int delete(String readerId, String bookIsbn) {
        int status = -1;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        //根据readerId获取用户账户信息
        Query readerInfoQuery = session.createQuery("from ReaderInfo where readerId=?");
        readerInfoQuery.setParameter(0, readerId);
        readerInfoQuery.setMaxResults(1);
        List<ReaderInfo> readers = readerInfoQuery.list();

        //根据book_ISBN查询图书信息
        Query bookInfoQuery = session.createQuery("from BookInfo  where bookIsbn=?");
        bookInfoQuery.setParameter(0, bookIsbn);
        bookInfoQuery.setMaxResults(1);
        List<BookInfo> books = bookInfoQuery.list();
        if (readers.size() > 0 && books.size() > 0) {
            ReaderInfo readerInfo = readers.get(0);
            BookInfo bookInfo = books.get(0);
            Query deleteQuery = session.createQuery("delete  from BorrowInfo where readerInfo=?and bookInfo=?");
            deleteQuery.setParameter(0, readerInfo);
            deleteQuery.setParameter(1, bookInfo);
            if (deleteQuery.executeUpdate() > 0) {
                status = 1;
            } else {
                status = 0;
            }
            transaction.commit();
            session.close();

            return status;
        }
        return 0;
    }

    @Override
    public List<BorrowInfo> query(ReaderInfo readerInfo) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BorrowInfo where readerInfo=?");
        List<BorrowInfo> borrows = query.list();
        transaction.commit();
        session.close();

        return borrows;
    }

    @Override
    public List<BorrowInfo> query(String readerId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query readerQuery = session.createQuery("from ReaderInfo  where readerId=?");
        readerQuery.setParameter(0, readerId);
        readerQuery.setMaxResults(1);
        List<ReaderInfo> readers = readerQuery.list();

        List<BorrowInfo> borrows=null;
        if (readers.size() > 0) {
            ReaderInfo readerInfo=readers.get(0);
            Query query=session.createQuery("from BorrowInfo where readerInfo=?");
            query.setParameter(0,readerInfo);
            borrows=query.list();
        }

        transaction.commit();
        session.close();


        return borrows;
    }
}
