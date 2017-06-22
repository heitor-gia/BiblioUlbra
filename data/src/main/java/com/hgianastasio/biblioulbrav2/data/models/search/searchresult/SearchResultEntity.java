package com.hgianastasio.biblioulbrav2.data.models.search.searchresult;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.hgianastasio.biblioulbrav2.data.models.search.searchbook.SearchBookEntity;

import java.util.ArrayList;

/**
 * Created by heitor_12 on 23/11/16.
 */
@JsonObject
public class SearchResultEntity {

    @JsonField(name = "resultados")
    private ArrayList<SearchBookEntity> result;

    @JsonField
    private int itens;

    @JsonField(name = "paginas")
    private int pages;

    @JsonField(name = "pagina")
    private int currentPage;

    @JsonField
    private String cookie;
    @JsonField
    private String scalar;


    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public ArrayList<SearchBookEntity> getResult() {
        return result;
    }

    public void setResult(ArrayList<SearchBookEntity> result) {
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

    public String getScalar() {
        return scalar;
    }

    public void setScalar(String scalar) {
        this.scalar = scalar;
    }
}
