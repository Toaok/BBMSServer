package com.dissertaion.bbms.dao.impl;

import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.dao.BookDao;
import com.dissertaion.bbms.vo.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/9/28.
 */
public class BookDaoImpl  extends BaseDao implements BookDao {

    @Override
    public void saveOrUpdate(Book book) {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        session.saveOrUpdate(book);
        transaction.commit();
        session.close();
    }

    @Override
    public String getValue(String path, String defaultValue) {

        Session session=getSession();
        Transaction transaction=session.beginTransaction();

        Query query=session.createQuery("select value from Book where path=?");
        query.setParameter(0,path);
        List result=query.list();
        if(result.size()>0){
            defaultValue= (String) result.get(0);
        }
        transaction.commit();
        session.close();
        return defaultValue;
    }
}
