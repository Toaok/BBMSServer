package com.dissertaion.bbms.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author TOAOK
 * @version 1.0  2017/11/8.
 */
@Entity
public class BorrowInfo implements Serializable{

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_ISBN")
    private BookInfo bookInfo;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    private ReaderInfo readerInfo;

    @Id
    @Column(name = "borrow_datetime")
    private Timestamp borrowDatetime;

    @Basic
    @Column(name = "borrow_duration")
    private Date borrowDuration;

    @Basic
    @Column(name = "borrow_forfeit")
    private Integer borrowForfeit;

    @Basic
    @Column(name = "book_status")
    private Integer bookStatus;

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public ReaderInfo getReaderInfo() {
        return readerInfo;
    }

    public void setReaderInfo(ReaderInfo readerInfo) {
        this.readerInfo = readerInfo;
    }


    public Timestamp getBorrowDatetime() {
        return borrowDatetime;
    }

    public void setBorrowDatetime(Timestamp borrowDatetime) {
        this.borrowDatetime = borrowDatetime;
    }



    public Date getBorrowDuration() {
        return borrowDuration;
    }

    public void setBorrowDuration(Date borrowDuration) {
        this.borrowDuration = borrowDuration;
    }

    public Integer getBorrowForfeit() {
        return borrowForfeit;
    }

    public void setBorrowForfeit(Integer borrowForfeit) {
        this.borrowForfeit = borrowForfeit;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowInfo that = (BorrowInfo) o;

        if (bookInfo != null ? !bookInfo.equals(that.bookInfo) : that.bookInfo != null) return false;
        if (readerInfo != null ? !readerInfo.equals(that.readerInfo) : that.readerInfo != null) return false;
        if (borrowDatetime != null ? !borrowDatetime.equals(that.borrowDatetime) : that.borrowDatetime != null)
            return false;
        if (borrowDuration != null ? !borrowDuration.equals(that.borrowDuration) : that.borrowDuration != null)
            return false;
        if (borrowForfeit != null ? !borrowForfeit.equals(that.borrowForfeit) : that.borrowForfeit != null)
            return false;
        return bookStatus != null ? bookStatus.equals(that.bookStatus) : that.bookStatus == null;
    }

    @Override
    public int hashCode() {
        int result = bookInfo != null ? bookInfo.hashCode() : 0;
        result = 31 * result + (readerInfo != null ? readerInfo.hashCode() : 0);
        result = 31 * result + (borrowDatetime != null ? borrowDatetime.hashCode() : 0);
        result = 31 * result + (borrowDuration != null ? borrowDuration.hashCode() : 0);
        result = 31 * result + (borrowForfeit != null ? borrowForfeit.hashCode() : 0);
        result = 31 * result + (bookStatus != null ? bookStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"bookInfo\":")
                .append(bookInfo);
        sb.append(",\"readerInfo\":")
                .append(readerInfo);
        sb.append(",\"borrowDatetime\":\"")
                .append(borrowDatetime).append('\"');
        sb.append(",\"borrowDuration\":\"")
                .append(borrowDuration).append('\"');
        sb.append(",\"borrowForfeit\":")
                .append(borrowForfeit);
        sb.append(",\"bookStatus\":")
                .append(bookStatus);
        sb.append('}');
        return sb.toString();
    }
}
