package com.hgianastasio.biblioulbrav2.data.models.search.searchmodel;


/**
 * Created by heitor_12 on 23/11/16.
 */
public class SearchModelEntity {


    private String search;
    private String cookie;
    private String base;
    private int page;
    private String exact;
    private String field;
    private String media;
    private String language;

    public SearchModelEntity(){}

    public SearchModelEntity(String search){
        this.search = search;
    }

    public SearchModelEntity(String cookie, int page){
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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String isExact() {
        return exact;
    }

    public void setExact(String exact) {
        this.exact = exact;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
