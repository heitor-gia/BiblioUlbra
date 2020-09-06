package com.hgianastasio.biblioulbrav2.data.models.loanbooks;

import com.google.gson.annotations.SerializedName;


/**
 * Created by heitorgianastasio on 4/24/17.
 */
public class LoanBookEntity {
    private long id;

    @SerializedName("titulo")
    private String title;

    @SerializedName("agendado")
    private String deadline;

    @SerializedName("taxa")
    private String penalty;

    @SerializedName("biblioteca")
    private String library;

    @SerializedName("chamada")
    private String code;

    @SerializedName("descricao")
    private String description;


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

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
