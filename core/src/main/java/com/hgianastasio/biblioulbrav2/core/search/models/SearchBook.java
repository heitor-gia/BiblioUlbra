package com.hgianastasio.biblioulbrav2.core.search.models;

import java.util.Date;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public class SearchBook {
    private String title;
    private long number;
    private String author;
    private Date year;
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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String[] getCatchedExps() {
        return catchedExps;
    }

    public void setCatchedExps(String[] catchedExps) {
        this.catchedExps = catchedExps;
    }
}
