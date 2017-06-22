package com.hgianastasio.biblioulbrav2.core.loanbooks.repository;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;

import java.io.IOException;
import java.util.List;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public interface LoanBooksRepository {
    void get(String cgu, OnResultListener<List<LoanBook>> onResultListener) throws IOException;
    void reload(String cgu, OnResultListener<List<LoanBook>> onResultListener) throws IOException;
}
