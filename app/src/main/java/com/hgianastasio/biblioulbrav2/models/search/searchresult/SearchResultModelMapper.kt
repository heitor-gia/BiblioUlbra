package com.hgianastasio.biblioulbrav2.models.search.searchresult

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModelMapper
/**
 * Created by heitor_12 on 31/05/17.
 */
class SearchResultModelMapper constructor(private val bookModelMapper: SearchBookModelMapper) : Mapper<SearchResult, SearchResultModel>() {
    override fun transform(input: SearchResult): SearchResultModel {
        val output = SearchResultModel()
        output.cookie = input.cookie
        output.currentPage = input.currentPage
        output.itens = input.itens
        output.status = input.status
        output.result = input.result?.let { bookModelMapper.trasform(it) }
        output.pages = input.pages
        return output
    }

    override fun transformBack(input: SearchResultModel): SearchResult {
        val output = SearchResult()
        output.currentPage = input.currentPage
        output.cookie = input.cookie
        output.pages = input.pages
        output.status = input.status
        output.itens = input.itens
        return output
    }

}