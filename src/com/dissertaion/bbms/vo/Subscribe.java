package com.dissertaion.bbms.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author TOAOK
 * @version 1.0  2017/10/18.
 */
@Entity
public class Subscribe implements Serializable{

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_ISBN")
    private BookInfo bookInfo;

    @Basic
    @Column(name = "subscribe_status")
    private boolean subscribeStatus;


    public boolean getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(boolean subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribe subscribe = (Subscribe) o;

        return subscribeStatus == subscribe.subscribeStatus;
    }

    @Override
    public int hashCode() {
        return (subscribeStatus ? 1 : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"account\":")
                .append(account);
        sb.append(",\"bookInfo\":")
                .append(bookInfo);
        sb.append(",\"subscribeStatus\":")
                .append(subscribeStatus);
        sb.append('}');
        return sb.toString();
    }
}
