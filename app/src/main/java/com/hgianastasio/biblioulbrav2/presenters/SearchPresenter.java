package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooks;
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksNextPage;
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksPrevPage;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModel;
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModelMapper;
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModel;
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModelMapper;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 31/05/17.
 */

public class SearchPresenter extends BasePresenter {
    SearchBooks searchBooks;
    SearchBooksNextPage nextPage;
    SearchBooksPrevPage prevPage;
    SearchModelModelMapper modelMapper;
    SearchResultModelMapper resultMapper;

    @Inject
    public SearchPresenter(UseCaseExecutor useCaseExecutor,
                           SearchBooks searchBooks,
                           SearchBooksNextPage nextPage,
                           SearchBooksPrevPage prevPage,
                           SearchModelModelMapper modelMapper,
                           SearchResultModelMapper resultMapper) {
        super(useCaseExecutor);
        this.searchBooks = searchBooks;
        this.nextPage = nextPage;
        this.prevPage = prevPage;
        this.modelMapper = modelMapper;
        this.resultMapper = resultMapper;
    }

    private <T> void execute(UseCase<SearchResult,T> useCase, T param, OnResultListener<SearchResultModel> resultListener, OnErrorListener errorListener){
        if (progressListener!=null)progressListener.showProgress();
        useCaseExecutor.execute(useCase,param,
                result -> {
                    if (progressListener!=null)progressListener.hideProgress();
                    resultListener.onResult(resultMapper.transform(result));
                },
                e -> {
                    if (progressListener!=null)progressListener.hideProgress();
                    errorListener.onError(e);
                });
    }

    public void search(SearchModelModel searchModel, OnResultListener<SearchResultModel> resultListener, OnErrorListener errorListener){
        execute(
                searchBooks,
                modelMapper.transformBack(searchModel),
                resultListener,
                errorListener
        );
    }

    public void nextPage(SearchResultModel resultModel, OnResultListener<SearchResultModel> resultListener,OnErrorListener errorListener){
        execute(
                nextPage,
                resultMapper.transformBack(resultModel),
                resultListener,
                errorListener
        );
    }
    public void prevPage(SearchResultModel resultModel, OnResultListener<SearchResultModel> resultListener,OnErrorListener errorListener){
        execute(
                prevPage,
                resultMapper.transformBack(resultModel),
                resultListener,
                errorListener
        );
    }

}
