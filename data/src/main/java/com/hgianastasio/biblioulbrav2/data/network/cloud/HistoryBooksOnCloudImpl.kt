package com.hgianastasio.biblioulbrav2.data.network.cloud

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/29/17.
 */
class HistoryBooksOnCloudImpl @Inject constructor(var api: BiblioUlbraApi) : HistoryBooksOnCloud {
    @Throws(IOException::class)
    override fun get(cgu: String?, listener: OnResultListener<List<HistoryBookEntity>>) {
        val list = api.getHistoryFromUser(cgu).execute().body()
        listener(list ?: emptyList())
    }

}