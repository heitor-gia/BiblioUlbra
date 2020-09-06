package com.hgianastasio.biblioulbrav2.core.search.exceptions

import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class EmptyResultsException : RuntimeException {
    var result: SearchResult
        private set

    constructor(result: SearchResult) {
        this.result = result
    }

    constructor(s: String?, result: SearchResult) : super(s) {
        this.result = result
    }

    override val message: String
        get() = "Pesquisa sem resultados"

}