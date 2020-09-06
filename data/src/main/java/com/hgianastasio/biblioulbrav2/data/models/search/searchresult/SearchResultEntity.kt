package com.hgianastasio.biblioulbrav2.data.models.search.searchresult

import com.google.gson.annotations.SerializedName
import com.hgianastasio.biblioulbrav2.data.models.search.searchbook.SearchBookEntity

/**
 * Created by heitor_12 on 23/11/16.
 */
class SearchResultEntity {
    @SerializedName("resultados")
    var result: List<SearchBookEntity>? = null

    @SerializedName("itens")
    var itens = 0

    @SerializedName("paginas")
    var pages = 0

    @SerializedName("pagina")
    var currentPage = 0

    @SerializedName("cookie")
    var cookie: String? = null

    @SerializedName("scalar")
    var scalar: String? = null

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