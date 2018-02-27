package org.example.greeting;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by robin on 2/27/18.
 */

public class Library implements Serializable {
    private String name;
    private String libraryDetail;
    private Integer bookCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibraryDetail() {
        return libraryDetail;
    }

    public void setLibraryDetail(String libraryDetail) {
        this.libraryDetail = libraryDetail;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Library(String name) {
        this.name = name;
    }

    public Library(String name, String libraryDetail, Integer bookCount) {
        this.name = name;
        this.libraryDetail = libraryDetail;
        this.bookCount = bookCount;
    }
}
