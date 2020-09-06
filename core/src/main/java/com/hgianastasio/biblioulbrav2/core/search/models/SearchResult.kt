package com.hgianastasio.biblioulbrav2.core.search.models

/**
 * Created by heitor_12 on 23/11/16.
 */
class SearchResult {
    var result: List<SearchBook>? = null
    var itens = 0
    var pages = 0
    var currentPage = 0
    var cookie: String? = null
    var status = 0
    var isEmptyResult = false

    fun nextPage() {
        currentPage++
    }

    fun prevPage() {
        currentPage--
    }

    fun hasMorePages(): Boolean {
        return currentPage <= pages
    }

    val isTheFirstPage: Boolean
        get() = currentPage == 1

}