package com.hgianastasio.biblioulbrav2.data.models.search.searchresult

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import com.hgianastasio.biblioulbrav2.data.models.search.searchbook.SearchBookEntityMapper
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class SearchResultEntityMapper @Inject constructor(var entityMapper: SearchBookEntityMapper) : Mapper<SearchResult, SearchResultEntity>() {
    override fun transform(input: SearchResult): SearchResultEntity? {
        return null
    }

    override fun transformBack(input: SearchResultEntity): SearchResult {
        val output = SearchResult()
        if (input.scalar == null) {
            output.result = entityMapper.tansformBack(input.result!!)
            output.cookie = input.cookie
            output.itens = input.itens
            output.pages = input.pages
            output.currentPage = input.currentPage
        }
        output.isEmptyResult = input.scalar != null
        return output
    }

}