package com.hgianastasio.biblioulbrav2.core.search.interactors;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.search.exceptions.EmptyResultsException;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class SearchBooks extends UseCase<SearchResult,SearchModel> {

    SearchBookRepository repository;

    @Inject
    public SearchBooks(ThreadPoolExecutor executor, SearchBookRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    protected void process(SearchModel params, OnResultListener<SearchResult> resultListener, OnErrorListener errorListener) {
        try {
            repository.search(params,
                    result ->{
                        if(result.isEmptyResult())
                            errorListener.onError(new EmptyResultsException(result));
                        else
                            resultListener.onResult(result);
                    }
            );
        } catch (Exception e){
            errorListener.onError(new ConnectionException(e));
        }
    }
}
