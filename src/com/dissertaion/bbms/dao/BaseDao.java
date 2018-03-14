package com.dissertaion.bbms.dao;

import com.dissertaion.bbms.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author TOAOK
 * @version 1.0  2017/9/28.
 */
public class BaseDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        Session session =sessionFactory.openSession();
        return session;

    }


}

