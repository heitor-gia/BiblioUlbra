package com.hgianastasio.biblioulbrav2.core.search.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.search.exceptions.EmptyResultsException
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class SearchBooks @Inject constructor(executor: ThreadPoolExecutor, var repository: SearchBookRepository) : UseCase<SearchResult, SearchModel?>(executor) {
    override fun process(params: SearchModel?, resultListener: OnResultListener<SearchResult>, errorListener: OnErrorListener) {
        try {
            repository.search(params) { result: SearchResult ->
                if (result.isEmptyResult)
                    errorListener(EmptyResultsException(result))
                else resultListener(result)
            }
        } catch (e: Exception) {
            errorListener(ConnectionException(e))
        }
    }

}