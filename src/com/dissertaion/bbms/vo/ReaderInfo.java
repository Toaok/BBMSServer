package com.dissertaion.bbms.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TOAOK
 * @version 1.0  2017/11/8.
 */
@Entity
public class ReaderInfo implements Serializable {

    public static final String READER_ID = "readerId";
    public static final String READER_NAME = "readerName";
    public static final String READER_SEX = "readerSex";
    public static final String READER_SPEC = "readerSpec";
    public static final String READER_PHONE = "readerPhone";
    public static final String READER_HANDING_TIME = "readerHandingTime";
    public static final String READER_TYPE = "readerType";
    public static final String READER_STATUS = "readerStatus";
    public static final String READER_DEBIT_AMOUNT = "reader_debit_amount";
    public static final String READER_TIME_LIMIT = "reader_time_limit";
    public static final String READER_SCHOOL = "readerSchool";
    public static final String READER_QIANMING = "readerQianming";
    public static final String READER_IMAGE = "readerImage";


    @Id
    @Column(name = "reader_id")
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "reader_id")
    private String readerId;

    @Basic
    @Column(name = "reader_name")
    private String readerName;

    @Basic
    @Column(name = "reader_sex")
    private String readerSex;

    @Basic
    @Column(name = "reader_spec")
    private String readerSpec;

    @Basic
    @Column(name = "reader_phone")
    private String readerPhone;

    @Basic
    @Column(name = "reader_handing_time")
    private Timestamp readerHandingTime;

    @Basic
    @Column(name = "reader_type")
    private String readerType;

    @Basic
    @Column(name = "reader_status")
    private String readerStatus;

    @Basic
    @Column(name = "reader_debit_amount")
    private Integer readerDebitAmount;

    @Basic
    @Column(name = "reader_time_limit")
    private Integer readerTimeLimit;

    @Basic
    @Column(name = "reader_school")
    private String readerSchool;

    @Basic
    @Column(name = "reader_qianming")
    private String readerQianming;

    @Basic
    @Column(name = "reader_image")
    private byte[] readerImage;

    @OneToMany(mappedBy = "readerInfo", cascade = CascadeType.ALL)
    private Set<BorrowInfo> bookInfos = new HashSet<>();


//    public Account getReaderId() {
//        return readerId;
//    }
//
//    public void setReaderId(Account readerId) {
//        this.readerId = readerId;
//    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }


    public String getReaderSex() {
        return readerSex;
    }

    public void setReaderSex(String readerSex) {
        this.readerSex = readerSex;
    }


    public String getReaderSpec() {
        return readerSpec;
    }

    public void setReaderSpec(String readerSpec) {
        this.readerSpec = readerSpec;
    }


    public String getReaderPhone() {
        return readerPhone;
    }

    public void setReaderPhone(String readerPhone) {
        this.readerPhone = readerPhone;
    }


    public Timestamp getReaderHandingTime() {
        return readerHandingTime;
    }

    public void setReaderHandingTime(Timestamp readerHandingTime) {
        this.readerHandingTime = readerHandingTime;
    }


    public String getReaderType() {
        return readerType;
    }

    public void setReaderType(String readerType) {
        this.readerType = readerType;
    }


    public String getReaderStatus() {
        return readerStatus;
    }

    public void setReaderStatus(String readerStatus) {
        this.readerStatus = readerStatus;
    }


    public Integer getReaderDebitAmount() {
        return readerDebitAmount;
    }

    public void setReaderDebitAmount(Integer readerDebitAmount) {
        this.readerDebitAmount = readerDebitAmount;
    }


    public Integer getReaderTimeLimit() {
        return readerTimeLimit;
    }

    public void setReaderTimeLimit(Integer readerTimeLimit) {
        this.readerTimeLimit = readerTimeLimit;
    }


    public String getReaderSchool() {
        return readerSchool;
    }

    public void setReaderSchool(String readerSchool) {
        this.readerSchool = readerSchool;
    }

    public String getReaderQianming() {
        return readerQianming;
    }

    public void setReaderQianming(String readerQianming) {
        this.readerQianming = readerQianming;
    }

    public byte[] getReaderImage() {
        return readerImage;
    }

    public void setReaderImage(byte[] readerImage) {
        this.readerImage = readerImage;
    }

    public Set<BorrowInfo> getBookInfo() {
        return bookInfos;
    }

    public boolean addBookInfo(BookInfo bookInfo) {
        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setReaderInfo(this);
        borrowInfo.setBookInfo(bookInfo);
        return bookInfo.getReader().add(borrowInfo);
    }

    public boolean removeBookInfo(BookInfo bookInfo) {
        boolean status = false;

        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setReaderInfo(this);
        borrowInfo.setBookInfo(bookInfo);

        if (bookInfo.getReader().remove(borrowInfo) && bookInfos.remove(borrowInfo)) {
            status = true;
        } else {
            status = false;
        }

        borrowInfo.setReaderInfo(null);
        borrowInfo.setBookInfo(null);
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderInfo that = (ReaderInfo) o;

        if (readerId != null ? !readerId.equals(that.readerId) : that.readerId != null) return false;
        if (readerName != null ? !readerName.equals(that.readerName) : that.readerName != null) return false;
        if (readerSex != null ? !readerSex.equals(that.readerSex) : that.readerSex != null) return false;
        if (readerSpec != null ? !readerSpec.equals(that.readerSpec) : that.readerSpec != null) return false;
        if (readerPhone != null ? !readerPhone.equals(that.readerPhone) : that.readerPhone != null) return false;
        if (readerHandingTime != null ? !readerHandingTime.equals(that.readerHandingTime) : that.readerHandingTime != null)
            return false;
        if (readerType != null ? !readerType.equals(that.readerType) : that.readerType != null) return false;
        if (readerStatus != null ? !readerStatus.equals(that.readerStatus) : that.readerStatus != null) return false;
        if (readerDebitAmount != null ? !readerDebitAmount.equals(that.readerDebitAmount) : that.readerDebitAmount != null)
            return false;
        if (readerTimeLimit != null ? !readerTimeLimit.equals(that.readerTimeLimit) : that.readerTimeLimit != null)
            return false;
        if (readerSchool != null ? !readerSchool.equals(that.readerSchool) : that.readerSchool != null) return false;
        if (readerQianming != null ? !readerQianming.equals(that.readerQianming) : that.readerQianming != null)
            return false;
        return Arrays.equals(readerImage, that.readerImage);
    }

    @Override
    public int hashCode() {
        int result = readerId != null ? readerId.hashCode() : 0;
        result = 31 * result + (readerName != null ? readerName.hashCode() : 0);
        result = 31 * result + (readerSex != null ? readerSex.hashCode() : 0);
        result = 31 * result + (readerSpec != null ? readerSpec.hashCode() : 0);
        result = 31 * result + (readerPhone != null ? readerPhone.hashCode() : 0);
        result = 31 * result + (readerHandingTime != null ? readerHandingTime.hashCode() : 0);
        result = 31 * result + (readerType != null ? readerType.hashCode() : 0);
        result = 31 * result + (readerStatus != null ? readerStatus.hashCode() : 0);
        result = 31 * result + (readerDebitAmount != null ? readerDebitAmount.hashCode() : 0);
        result = 31 * result + (readerTimeLimit != null ? readerTimeLimit.hashCode() : 0);
        result = 31 * result + (readerSchool != null ? readerSchool.hashCode() : 0);
        result = 31 * result + (readerQianming != null ? readerQianming.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(readerImage);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"readerId\":\"")
                .append(readerId).append('\"');
        sb.append(",\"readerName\":\"")
                .append(readerName).append('\"');
        sb.append(",\"readerSex\":\"")
                .append(readerSex).append('\"');
        sb.append(",\"readerSpec\":\"")
                .append(readerSpec).append('\"');
        sb.append(",\"readerPhone\":\"")
                .append(readerPhone).append('\"');
        sb.append(",\"readerHandingTime\":\"")
                .append(readerHandingTime).append('\"');
        sb.append(",\"readerType\":\"")
                .append(readerType).append('\"');
        sb.append(",\"readerStatus\":\"")
                .append(readerStatus).append('\"');
        sb.append(",\"readerDebitAmount\":")
                .append(readerDebitAmount);
        sb.append(",\"readerTimeLimit\":")
                .append(readerTimeLimit);
        sb.append(",\"readerSchool\":\"")
                .append(readerSchool).append('\"');
        sb.append(",\"readerQianMing\":\"")
                .append(readerQianming).append('\"');
        sb.append(",\"readerImage\":")
                .append(Arrays.toString(readerImage));
        sb.append('}');
        return sb.toString();
    }
}
