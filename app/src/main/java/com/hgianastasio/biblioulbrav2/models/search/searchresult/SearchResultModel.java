package com.hgianastasio.biblioulbrav2.models.search.searchresult;

import com.hgianastasio.biblioulbrav2.core.search.models.SearchBook;
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel;

import java.util.List;

/**
 * Created by heitor_12 on 23/11/16.
 */
public class SearchResultModel {
    private List<SearchBookModel> result;
    private int itens;
    private int pages;
    private int currentPage;
    private String cookie;
    private int status;

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

    public List<SearchBookModel> getResult() {
        return result;
    }

    public void setResult(List<SearchBookModel> result) {
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
}
