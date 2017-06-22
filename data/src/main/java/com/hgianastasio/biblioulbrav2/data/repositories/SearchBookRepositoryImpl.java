package com.hgianastasio.biblioulbrav2.data.repositories;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository;
import com.hgianastasio.biblioulbrav2.data.base.search.SearchBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.models.search.searchmodel.SearchModelEntityMapper;
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntityMapper;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class SearchBookRepositoryImpl implements SearchBookRepository {
    SearchBooksOnCloud cloud;
    SearchResultEntityMapper resultEntityMapper;
    SearchModelEntityMapper modelEntityMapper;

    @Inject
    public SearchBookRepositoryImpl(
            SearchBooksOnCloud cloud,
            SearchResultEntityMapper resultEntityMapper,
            SearchModelEntityMapper modelEntityMapper) {
        this.cloud = cloud;
        this.resultEntityMapper = resultEntityMapper;
        this.modelEntityMapper = modelEntityMapper;
    }

    @Override
    public void search(SearchModel model, OnResultListener<SearchResult> onResultListener) throws IOException {
        cloud.search(modelEntityMapper.transform(model),
                result -> onResultListener
                            .onResult(resultEntityMapper.transformBack(result)));
    }

}
