package com.hgianastasio.biblioulbrav2.core.historybooks;

import java.util.Date;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public class HistoryBook {
    private long id;
    private String title;
    private Date deadline;
    private Date devolution;
    private String library;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDevolution() {
        return devolution;
    }

    public void setDevolution(Date devolution) {
        this.devolution = devolution;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
