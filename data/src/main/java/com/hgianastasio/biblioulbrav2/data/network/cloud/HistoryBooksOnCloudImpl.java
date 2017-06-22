package com.hgianastasio.biblioulbrav2.data.network.cloud;

import android.util.Log;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity;
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public class HistoryBooksOnCloudImpl implements HistoryBooksOnCloud {
    BiblioUlbraApi api;

    @Inject
    public HistoryBooksOnCloudImpl(BiblioUlbraApi api) {
        this.api = api;
    }

    @Override
    public void get(String cgu, OnResultListener<List<HistoryBookEntity>> listener) throws IOException {
        List<HistoryBookEntity> list = api.getHistoryFromUser(cgu).execute().body();
        listener.onResult(list);
    }
}
