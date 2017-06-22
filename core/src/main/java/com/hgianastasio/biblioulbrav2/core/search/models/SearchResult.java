package com.hgianastasio.biblioulbrav2.core.search.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heitor_12 on 23/11/16.
 */
public class SearchResult {
    private List<SearchBook> result;
    private int itens;
    private int pages;
    private int currentPage;
    private String cookie;
    private int status;
    private boolean emptyResult;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public List<SearchBook> getResult() {
        return result;
    }

    public void setResult(List<SearchBook> result) {
        this.result = result;
    }

    public int getItens() {
        return itens;
    }

    public void setItens(int itens) {
        this.itens = itens;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void nextPage(){
        this.currentPage++;
    }

    public void prevPage(){
        this.currentPage--;
    }

    public boolean hasMorePages(){
        return currentPage<=pages;
    }

    public boolean isTheFirstPage(){return currentPage==1;}

    public boolean isEmptyResult() {
        return emptyResult;
    }

    public void setEmptyResult(boolean emptyResult) {
        this.emptyResult = emptyResult;
    }
}
