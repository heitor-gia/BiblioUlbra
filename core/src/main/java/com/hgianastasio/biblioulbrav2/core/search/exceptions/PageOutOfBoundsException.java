package com.hgianastasio.biblioulbrav2.core.search.exceptions;

import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class PageOutOfBoundsException extends RuntimeException {
    SearchResult result;

    public PageOutOfBoundsException(SearchResult result) {
        this.result = result;
    }

    public PageOutOfBoundsException(String s, SearchResult result) {
        super(s);
        this.result = result;
    }
}
