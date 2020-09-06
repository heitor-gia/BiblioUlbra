package com.hgianastasio.biblioulbrav2.data.network.cloud;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.data.base.search.SearchBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.models.search.searchmodel.SearchModelEntity;
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntity;
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class SearchBooksOnCloudImpl implements SearchBooksOnCloud {

    BiblioUlbraApi api;

    @Inject
    public SearchBooksOnCloudImpl(BiblioUlbraApi api) {
        this.api = api;
    }

    @Override
    public void search(SearchModelEntity searchModelEntity, OnResultListener<SearchResultEntity> onResultListener) throws IOException {
        Response<SearchResultEntity> entity = api.search(
                searchModelEntity.getSearch(),
                searchModelEntity.getCookie(),
                searchModelEntity.getPage(),
                searchModelEntity.getLanguage(),
                searchModelEntity.getMedia(),
                searchModelEntity.getField(),
                searchModelEntity.getBase()
        ).execute();
        String oi = entity.toString();
        onResultListener.onResult(
                entity.body()
        );
    }
}
