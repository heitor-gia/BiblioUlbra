package com.hgianastasio.biblioulbrav2.data.base.historybooks;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity;

import java.io.IOException;
import java.util.List;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public interface HistoryBooksOnCloud {
    void get(String cgu, OnResultListener<List<HistoryBookEntity>> listener) throws IOException;
}
