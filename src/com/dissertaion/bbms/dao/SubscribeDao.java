package com.dissertaion.bbms.dao;

import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.BookInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/10/18.
 */
public interface SubscribeDao {
    List<BookInfo> query(int accountId);

    List<BookInfo> query(Account account);

    int delete(int accountId, String bookIsbn);

    int append(int accountId, String bookIsbn);


}
