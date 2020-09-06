package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooks
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksNextPage
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksPrevPage
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModel
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModelMapper
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModel
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModelMapper
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
import javax.inject.Inject

/**
 * Created by heitor_12 on 31/05/17.
 */
class SearchPresenter @Inject constructor(useCaseExecutor: UseCaseExecutor,
                                          var searchBooks: SearchBooks,
                                          var nextPage: SearchBooksNextPage,
                                          var prevPage: SearchBooksPrevPage,
                                          var modelMapper: SearchModelModelMapper,
                                          var resultMapper: SearchResultModelMapper) : BasePresenter(useCaseExecutor) {
    private fun <T> execute(useCase: UseCase<SearchResult, T>, param: T, resultListener: OnResultListener<SearchResultModel>, errorListener: OnErrorListener) {
        if (progressListener != null) progressListener!!.showProgress()
        useCaseExecutor.execute(useCase, param,
                { result: SearchResult ->
                    if (progressListener != null) progressListener!!.hideProgress()
                    resultListener(resultMapper.transform(result))
                },
                { e: Exception? ->
                    if (progressListener != null) progressListener!!.hideProgress()
                    e?.let { errorListener(it) }
                })
    }

    fun search(searchModel: SearchModelModel, resultListener: OnResultListener<SearchResultModel>, errorListener: OnErrorListener) {
        execute(
                searchBooks,
                modelMapper.transformBack(searchModel),
                resultListener,
                errorListener
        )
    }

    fun nextPage(resultModel: SearchResultModel, resultListener: OnResultListener<SearchResultModel>, errorListener: OnErrorListener) {
        execute(
                nextPage,
                resultMapper.transformBack(resultModel),
                resultListener,
                errorListener
        )
    }

    fun prevPage(resultModel: SearchResultModel, resultListener: OnResultListener<SearchResultModel>, errorListener: OnErrorListener) {
        execute(
                prevPage,
                resultMapper.transformBack(resultModel),
                resultListener,
                errorListener
        )
    }

}