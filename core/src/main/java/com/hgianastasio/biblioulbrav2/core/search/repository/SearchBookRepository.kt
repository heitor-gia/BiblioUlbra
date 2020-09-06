package com.hgianastasio.biblioulbrav2.core.search.repository

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import java.io.IOException

/**
 * Created by heitorgianastasio on 4/24/17.
 */
interface SearchBookRepository {
    @Throws(IOException::class)
    fun search(model: SearchModel?, onResultListener: OnResultListener<SearchResult>)
}