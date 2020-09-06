package com.hgianastasio.biblioulbrav2.data.repositories

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository
import com.hgianastasio.biblioulbrav2.data.base.search.SearchBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.models.search.searchmodel.SearchModelEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntityMapper
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class SearchBookRepositoryImpl @Inject constructor(
        var cloud: SearchBooksOnCloud,
        var resultEntityMapper: SearchResultEntityMapper,
        var modelEntityMapper: SearchModelEntityMapper) : SearchBookRepository {
    @Throws(IOException::class)
    override fun search(model: SearchModel?, onResultListener: OnResultListener<SearchResult>) {
        cloud.search(modelEntityMapper.transform(model)
        ) { result ->
            onResultListener(resultEntityMapper.transformBack(result))
        }
    }

}