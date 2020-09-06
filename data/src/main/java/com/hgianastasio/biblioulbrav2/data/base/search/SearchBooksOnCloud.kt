package com.hgianastasio.biblioulbrav2.data.base.search

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.data.models.search.searchmodel.SearchModelEntity
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntity
import java.io.IOException

/**
 * Created by heitor_12 on 09/05/17.
 */
interface SearchBooksOnCloud {
    @Throws(IOException::class)
    fun search(searchModelEntity: SearchModelEntity?, onResultListener: OnResultListener<SearchResultEntity>)
}