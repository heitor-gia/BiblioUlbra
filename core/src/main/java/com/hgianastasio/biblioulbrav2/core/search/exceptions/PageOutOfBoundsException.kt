package com.hgianastasio.biblioulbrav2.core.search.exceptions

import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class PageOutOfBoundsException : RuntimeException {
    var result: SearchResult

    constructor(result: SearchResult) {
        this.result = result
    }

    constructor(s: String?, result: SearchResult) : super(s) {
        this.result = result
    }
}