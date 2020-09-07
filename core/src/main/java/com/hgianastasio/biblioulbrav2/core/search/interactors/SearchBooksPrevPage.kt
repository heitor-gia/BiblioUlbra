package com.hgianastasio.biblioulbrav2.core.search.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.search.exceptions.PageOutOfBoundsException
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository
import java.util.concurrent.ThreadPoolExecutor
/**
 * Created by heitorgianastasio on 4/25/17.
 */
class SearchBooksPrevPage constructor(executor: ThreadPoolExecutor, var repository: SearchBookRepository) : UseCase<SearchResult, SearchResult>(executor) {
    override fun process(result: SearchResult, resultListener: OnResultListener<SearchResult>, errorListener: OnErrorListener) {
        try {
            if (!result.isTheFirstPage) result.prevPage() else {
                errorListener(PageOutOfBoundsException(result))
                return
            }
            repository.search(SearchModel(result.cookie, result.currentPage), resultListener)
        } catch (e: Exception) {
            errorListener(ConnectionException(e))
        }
    }

}