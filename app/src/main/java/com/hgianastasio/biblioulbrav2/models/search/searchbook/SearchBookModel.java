package com.hgianastasio.biblioulbrav2.models.search.searchbook;

import java.util.Date;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public class SearchBookModel {
    private String title;
    private long number;
    private String author;
    private String year;
    private String[] catchedExps;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getCatchedExps() {
        return catchedExps;
    }

    public void setCatchedExps(String[] catchedExps) {
        this.catchedExps = catchedExps;
    }
}
