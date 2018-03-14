package com.dissertaion.bbms.test;

import com.dissertaion.bbms.dao.BaseDao;
import com.dissertaion.bbms.service.BookService;
import com.dissertaion.bbms.service.impl.BookServiceImpl;
import com.dissertaion.bbms.utils.BBMSUtils;
import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.Book;
import com.dissertaion.bbms.vo.BookInfo;
import com.dissertaion.bbms.vo.Subscribe;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Set;

import static com.dissertaion.bbms.utils.HibernateSessionFactory.getSession;

/**
 * @author TOAOK
 * @version 1.0  2017/9/30.
 */
public class MyTest {

    @Test
    public void test() {

        /*
        * subscribe包含Acount表，Acount表包含subsribe
        * */
        //JSONArray jsonArray = JSONArray.fromObject(query(1),jsonConfig);
//        String result = jsonArray.toString();

//
//        String result = query(1).toString();
//        System.out.println(result);
//        delete(1,"10093·675");
//        result = query(1).toString();
//        System.out.println(result);


        try {
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void parse() throws IOException {
        URL url = new URL("http://iwtoaok.iask.in:11892/BBMSServer/BookServlet?cmd=search&pages=1&sw=%E5%95%8A");
        System.out.println("Authority:" + url.getAuthority()+"\nRef"+url.getPath());
        String path=url.getPath();
        String model=path.substring(path.lastIndexOf("/")+1);
        System.out.println(model);
    }


    public List query(int accountId) {


        Session session = LoadConfigurationFile.getSession();
        Transaction transaction = session.beginTransaction();

        Account account = session.load(Account.class, accountId);

        Query query = session.createQuery("from Subscribe subscribe where subscribe.account=?");

        query.setParameter(0, account);
        List<Subscribe> subscribes = query.list();
        transaction.commit();
        session.close();
        return subscribes;
    }

    public void delete(int accountId, String bookIsbn) {
        Session session = LoadConfigurationFile.getSession();
        Transaction transaction = session.beginTransaction();

        Account account = session.load(Account.class, accountId);

        Query query = session.createQuery("from BookInfo bookinfo where bookinfo.bookIsbn=?");
        query.setParameter(0, bookIsbn);
        query.setMaxResults(1);
        BookInfo bookInfo = (BookInfo) query.list().get(0);

        account.removeBookinfo(bookInfo);

        transaction.commit();
        session.close();
    }
}
