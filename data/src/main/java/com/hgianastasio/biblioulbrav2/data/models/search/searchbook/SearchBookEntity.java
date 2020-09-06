package com.hgianastasio.biblioulbrav2.data.models.search.searchbook;

import com.google.gson.annotations.SerializedName;


/**
 * Created by heitorgianastasio on 4/24/17.
 */
public class SearchBookEntity {

    @SerializedName("titulo")
    private String title;

    @SerializedName("numero")
    private String number;

    @SerializedName("autor")
    private String author;

    @SerializedName("ano")
    private String year;

    @SerializedName("exsemprestados")
    private String catchedExps;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getCatchedExps() {
        return catchedExps;
    }

    public void setCatchedExps(String catchedExps) {
        this.catchedExps = catchedExps;
    }
}
