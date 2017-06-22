package com.hgianastasio.biblioulbrav2.data.models.search.searchbook;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by heitorgianastasio on 4/24/17.
 */
@JsonObject
public class SearchBookEntity {

    @JsonField(name = "titulo")
    private String title;

    @JsonField(name = "numero")
    private String number;

    @JsonField(name = "autor")
    private String author;

    @JsonField(name = "ano")
    private String year;

    @JsonField(name = "exsemprestados")
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
