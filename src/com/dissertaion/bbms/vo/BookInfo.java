package com.dissertaion.bbms.vo;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TOAOK on 2017/9/19.
 */
@Entity
public class BookInfo  implements Serializable{

    @Id
    @Column(name = "book_ISBN")
    private String bookIsbn;

    @Basic
    @Column(name = "book_name")
    private String bookName;

    @Basic
    @Column(name = "book_author")
    private String bookAuthor;

    @Basic
    @Column(name = "book_publish")
    private String bookPublish;

    @Basic
    @Column(name = "book_price")
    private String bookPrice;

    @Basic
    @Column(name = "book_cnum")
    private int bookCnum;//库存量
    @Basic
    @Column(name = "book_snum")
    private int bookSnum;//复本量
    @Basic
    @Column(name = "book_classify")
    private String bookClassify;
    @Basic
    @Column(name = "book_summary")
    private String bookSummary;
    @Basic
    @Column(name = "book_cover")
    private String bookCover;
    @OneToMany(mappedBy = "bookInfo", cascade = CascadeType.ALL)
    private Set<Subscribe> accounts = new HashSet<>();
    @OneToMany(mappedBy = "bookInfo",cascade = CascadeType.ALL)
    private Set<BorrowInfo> reader=new HashSet<>();

    /*
    * mappedBy的值是该表在第三张表中的映射对象
    * */

    public void setBookCnum(Integer bookCnum) {
        this.bookCnum = bookCnum;
    }

    public void setBookSnum(Integer bookSnum) {
        this.bookSnum = bookSnum;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookCnum() {
        return bookCnum;
    }

    public void setBookCnum(int bookCnum) {
        this.bookCnum = bookCnum;
    }

    public int getBookSnum() {
        return bookSnum;
    }

    public void setBookSnum(int bookSnum) {
        this.bookSnum = bookSnum;
    }

    public String getBookClassify() {
        return bookClassify;
    }

    public void setBookClassify(String bookClassify) {
        this.bookClassify = bookClassify;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }


    public Set<Subscribe> getAccounts() {
        return accounts;
    }

    public void setAccount(Set<Subscribe> accounts) {
        this.accounts = accounts;
    }

    public Set<BorrowInfo> getReader() {
        return reader;
    }

    public void setReader(Set<BorrowInfo> reader) {
        this.reader = reader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookInfo bookInfo = (BookInfo) o;

        if (bookCnum != bookInfo.bookCnum) return false;
        if (bookSnum != bookInfo.bookSnum) return false;
        if (bookIsbn != null ? !bookIsbn.equals(bookInfo.bookIsbn) : bookInfo.bookIsbn != null) return false;
        if (bookName != null ? !bookName.equals(bookInfo.bookName) : bookInfo.bookName != null) return false;
        if (bookAuthor != null ? !bookAuthor.equals(bookInfo.bookAuthor) : bookInfo.bookAuthor != null) return false;
        if (bookPublish != null ? !bookPublish.equals(bookInfo.bookPublish) : bookInfo.bookPublish != null)
            return false;
        if (bookPrice != null ? !bookPrice.equals(bookInfo.bookPrice) : bookInfo.bookPrice != null) return false;
        if (bookClassify != null ? !bookClassify.equals(bookInfo.bookClassify) : bookInfo.bookClassify != null)
            return false;
        if (bookSummary != null ? !bookSummary.equals(bookInfo.bookSummary) : bookInfo.bookSummary != null)
            return false;
        if (bookCover != null ? !bookCover.equals(bookInfo.bookCover) : bookInfo.bookCover != null) return false;
        return accounts != null ? accounts.equals(bookInfo.accounts) : bookInfo.accounts == null;
    }

    @Override
    public int hashCode() {
        int result = bookIsbn != null ? bookIsbn.hashCode() : 0;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        result = 31 * result + (bookPublish != null ? bookPublish.hashCode() : 0);
        result = 31 * result + (bookPrice != null ? bookPrice.hashCode() : 0);
        result = 31 * result + bookCnum;
        result = 31 * result + bookSnum;
        result = 31 * result + (bookClassify != null ? bookClassify.hashCode() : 0);
        result = 31 * result + (bookSummary != null ? bookSummary.hashCode() : 0);
        result = 31 * result + (bookCover != null ? bookCover.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"bookIsbn\":\"")
                .append(bookIsbn).append('\"');
        sb.append(",\"bookName\":\"")
                .append(bookName).append('\"');
        sb.append(",\"bookAuthor\":\"")
                .append(bookAuthor).append('\"');
        sb.append(",\"bookPublish\":\"")
                .append(bookPublish).append('\"');
        sb.append(",\"bookPrice\":\"")
                .append(bookPrice).append('\"');
        sb.append(",\"bookCnum\":")
                .append(bookCnum);
        sb.append(",\"bookSnum\":")
                .append(bookSnum);
        sb.append(",\"bookClassify\":\"")
                .append(bookClassify).append('\"');
        sb.append(",\"bookSummary\":\"")
                .append(bookSummary).append('\"');
        sb.append(",\"bookCover\":\"")
                .append(bookCover).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
