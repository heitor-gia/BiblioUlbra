package com.hgianastasio.biblioulbrav2.core.search.models;


import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia;


/**
 * Created by heitor_12 on 23/11/16.
 */
public class SearchModel {

    private String search;
    private String cookie;
    private SearchBase base;
    private int page;
    private boolean exact;
    private SearchLanguage language;
    private SearchField field;
    private SearchMedia media;

    public SearchModel(){}

    public SearchModel(String search){
        this.search = search;
    }

    public SearchModel(String cookie,int page){
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

    public SearchBase getBase() {
        return base;
    }

    public void setBase(SearchBase base) {
        this.base = base;
    }

    public boolean isExact() {
        return exact;
    }

    public void setExact(boolean exact) {
        this.exact = exact;
    }

    public SearchField getField() {
        return field;
    }

    public void setField(SearchField field) {
        this.field = field;
    }

    public SearchMedia getMedia() {
        return media;
    }

    public void setMedia(SearchMedia media) {
        this.media = media;
    }

    public SearchLanguage getLanguage() {
        return language;
    }

    public void setLanguage(SearchLanguage language) {
        this.language = language;
    }
}
