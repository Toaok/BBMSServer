package com.dissertaion.bbms.vo;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author TOAOK
 * @version 1.0  2017/9/30.
 */
@Entity
public class Book  implements Serializable{

    @Id
    @Column(name = "path")
    private String path;

    @Basic
    @Column(name = "value")
    private String value;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (path != null ? !path.equals(book.path) : book.path != null) return false;
        return value != null ? value.equals(book.value) : book.value == null;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
