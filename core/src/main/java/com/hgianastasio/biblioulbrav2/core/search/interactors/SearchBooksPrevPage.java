package com.hgianastasio.biblioulbrav2.core.search.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.search.exceptions.PageOutOfBoundsException;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class SearchBooksPrevPage extends UseCase<SearchResult,SearchResult> {

    SearchBookRepository repository;

    @Inject
    public SearchBooksPrevPage(ThreadPoolExecutor executor, SearchBookRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    protected void process(SearchResult result, OnResultListener<SearchResult> resultListener, OnErrorListener errorListener) {
        try {
            if(!result.isTheFirstPage())
                result.prevPage();
            else {
                errorListener.onError(new PageOutOfBoundsException(result));
                return;
            }
            repository.search(new SearchModel(result.getCookie(),result.getCurrentPage()), resultListener);
        } catch (Exception e){
            errorListener.onError(new ConnectionException(e));
        }
    }
}
