package com.hgianastasio.biblioulbrav2.core.search.exceptions;

import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class EmptyResultsException extends RuntimeException {
    private SearchResult result;

    public EmptyResultsException(SearchResult result) {
        this.result = result;
    }

    public EmptyResultsException(String s, SearchResult result) {
        super(s);
        this.result = result;
    }

    @Override
    public String getMessage() {
        return "Pesquisa sem resultados";
    }

    public SearchResult getResult() {
        return result;
    }
}
