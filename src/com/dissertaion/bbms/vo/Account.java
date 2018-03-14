package com.dissertaion.bbms.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TOAOK
 * @version 1.0  2017/10/10.
 */
@Entity
public class Account implements Serializable {

    public static final String ACCOUNT="account";
    public static final String PASSWORD="password";

    @Id
    @Column(name = "account_id")
    private Integer accountId;

    @Basic
    @Column(name = "account")
    private String account;

    @Basic
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Subscribe> bookInfos = new HashSet<>();

//    @OneToOne(mappedBy = "readerId",cascade = CascadeType.ALL)
//    private ReaderInfo readerInfo;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Subscribe> getBookInfos() {
        return bookInfos;
    }

    public boolean addBookInfo(BookInfo bookInfo) {
        Subscribe subscribe = new Subscribe();
        subscribe.setAccount(this);
        subscribe.setBookInfo(bookInfo);
        return bookInfo.getAccounts().add(subscribe);
    }

    public boolean removeBookinfo(BookInfo bookInfo) {
        boolean status = false;
        Subscribe subscribe = new Subscribe();
        subscribe.setAccount(this);
        subscribe.setBookInfo(bookInfo);
        if (bookInfo.getAccounts().remove(subscribe) && bookInfos.remove(subscribe)) {
            status = true;
        } else {
            status = false;
        }
        subscribe.setBookInfo(null);
        subscribe.setAccount(null);
        return status;
    }


//    public ReaderInfo getReaderInfo() {
//        return readerInfo;
//    }
//
//    public void setReaderInfo(ReaderInfo readerInfo) {
//        this.readerInfo = readerInfo;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (this.account != null ? !this.account.equals(account.account) : account.account != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"accountId\":")
                .append(accountId);
        sb.append(",\"account\":\"")
                .append(account).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
