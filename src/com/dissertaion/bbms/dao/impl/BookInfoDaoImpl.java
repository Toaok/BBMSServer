package com.dissertaion.bbms.dao.impl;

import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.dao.BookInfoDao;
import com.dissertaion.bbms.vo.BookInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/10/19.
 */
public class BookInfoDaoImpl extends BaseDao implements BookInfoDao {
    @Override
    public void saveOrUpdate(List<BookInfo> list) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        for (BookInfo bookInfo : list) {
            if (bookInfo.getBookIsbn() != null && !bookInfo.getBookIsbn().equals("")) {
                session.saveOrUpdate(bookInfo);
            }
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(BookInfo bookInfo) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(bookInfo);

        transaction.commit();
        session.close();
    }
}
