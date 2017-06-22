package com.hgianastasio.biblioulbrav2.core.historybooks.repository;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;

import java.io.IOException;
import java.util.List;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public interface HistoryBooksRepository {
    void get(String cgu, OnResultListener<List<HistoryBook>> onResultListener) throws IOException;
    void reload(String cgu, OnResultListener<List<HistoryBook>> onResultListener) throws IOException;
}
