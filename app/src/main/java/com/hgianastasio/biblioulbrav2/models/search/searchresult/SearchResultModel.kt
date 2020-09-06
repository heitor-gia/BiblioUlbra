package com.hgianastasio.biblioulbrav2.models.search.searchresult

import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel

/**
 * Created by heitor_12 on 23/11/16.
 */
class SearchResultModel {
    var result: List<SearchBookModel?>? = null
    var itens = 0
    var pages = 0
    var currentPage = 0
    var cookie: String? = null
    var status = 0

}