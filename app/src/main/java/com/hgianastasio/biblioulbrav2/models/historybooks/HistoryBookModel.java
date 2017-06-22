package com.hgianastasio.biblioulbrav2.models.historybooks;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public class HistoryBookModel {
    private long id;
    private String title;
    private String deadline;
    private String devolution;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDevolution() {
        return devolution;
    }

    public void setDevolution(String devolution) {
        this.devolution = devolution;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
