package com.dissertaion.bbms.service;

import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.BookInfo;

import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/10/17.
 */
public interface SubscribeService {

    List<BookInfo> query(int accountId);
    List<BookInfo> query(Account account);

    int delete(int accountId, String bookIsbn);

    int append(int accountId, String bookIsbn);
}
