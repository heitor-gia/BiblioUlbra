package com.hgianastasio.biblioulbrav2.data.models.historybooks;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by heitorgianastasio on 4/24/17.
 */
@JsonObject
public class HistoryBookEntity {


    private long id;

    @JsonField(name = "titulo")
    private String title;

    @JsonField(name = "agendado")
    private String deadline;

    @JsonField(name = "devolvido")
    private String devolution;

    @JsonField(name = "biblioteca")
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
