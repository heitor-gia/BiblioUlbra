package com.hgianastasio.biblioulbrav2.data.base.loanbooks;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity;

import java.io.IOException;
import java.util.List;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public interface LoanBooksOnCloud {
    void get(String cgu, OnResultListener<List<LoanBookEntity>> listener) throws IOException;
}
