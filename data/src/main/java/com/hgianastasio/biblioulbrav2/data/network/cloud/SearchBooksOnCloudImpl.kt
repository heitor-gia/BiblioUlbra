package com.hgianastasio.biblioulbrav2.data.network.cloud

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.data.base.search.SearchBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.models.search.searchmodel.SearchModelEntity
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntity
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class SearchBooksOnCloudImpl @Inject constructor(var api: BiblioUlbraApi) : SearchBooksOnCloud {
    @Throws(IOException::class)
    override fun search(searchModelEntity: SearchModelEntity?, onResultListener: OnResultListener<SearchResultEntity>) {
        val entity = api.search(
                searchModelEntity?.search,
                searchModelEntity?.cookie,
                searchModelEntity?.page ?: 0,
                searchModelEntity?.language,
                searchModelEntity?.media,
                searchModelEntity?.field,
                searchModelEntity?.base
        ).execute()
        val oi = entity.toString()
        onResultListener(
                entity.body() ?: SearchResultEntity()
        )
    }

}