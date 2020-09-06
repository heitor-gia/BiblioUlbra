package com.hgianastasio.biblioulbrav2.data.base.historybooks

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity
import java.io.IOException

/**
 * Created by heitorgianastasio on 4/29/17.
 */
interface HistoryBooksOnCloud {
    @Throws(IOException::class)
    fun get(cgu: String?, listener: OnResultListener<List<HistoryBookEntity>>)
}