package com.hgianastasio.biblioulbrav2.models.search.searchmodel;


/**
 * Created by heitor_12 on 23/11/16.
 */
public class SearchModelModel {

    private String search;
    private String cookie;
    private int base;
    private int page;
    private boolean exact;
    private int language;
    private int field;
    private int media;

    public SearchModelModel(){}

    public SearchModelModel(String search){
        this.search = search;
    }

    public SearchModelModel(String cookie, int page){
        this.cookie = cookie;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public boolean isExact() {
        return exact;
    }

    public void setExact(boolean exact) {
        this.exact = exact;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
