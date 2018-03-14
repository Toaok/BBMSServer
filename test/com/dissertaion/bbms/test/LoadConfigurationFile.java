package com.dissertaion.bbms.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LoadConfigurationFile {
    private static ApplicationContext sApplicationContext;

    static {
        sApplicationContext = new FileSystemXmlApplicationContext("E:\\Dissertation\\SourceCode\\BBMSServer\\src\\applicationContext.xml");
    }

    public static Session getSession() {
        SessionFactory sessionFactory= (SessionFactory) sApplicationContext.getBean("sessionFactory");
        return sessionFactory.openSession();
    }

}