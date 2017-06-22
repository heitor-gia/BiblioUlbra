package com.hgianastasio.biblioulbrav2.data.network.cloud;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity;
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public class LoanBooksOnCloudImpl implements LoanBooksOnCloud {
    BiblioUlbraApi api;

    @Inject
    public LoanBooksOnCloudImpl(BiblioUlbraApi api) {
        this.api = api;
    }


    @Override
    public void get(String cgu, OnResultListener<List<LoanBookEntity>> listener) throws IOException {
        listener.onResult(api.getLoansFromUser(cgu).execute().body());

    }
}
