package com.hgianastasio.biblioulbrav2.data.repositories

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook
import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntityMapper
import java.io.IOException
/**
 * Created by heitorgianastasio on 4/25/17.
 */
class HistoryBooksRepositoryImp constructor(
        private val cloud: HistoryBooksOnCloud,
        private val cache: HistoryBooksOnCache,
        private val mapper: HistoryBookEntityMapper, private val userOnCache: UserOnCache) : HistoryBooksRepository {
    @Throws(IOException::class)
    override fun get(cgu: String?, onResultListener: OnResultListener<List<HistoryBook>>) {
        if (cache.isUpdated) {
            onResultListener(mapper.tansformBack(cache.get()))
        } else {
            cloud.get(cgu) { list: List<HistoryBookEntity> ->
                if (validateCgu(cgu)) {
                    onResultListener(mapper.tansformBack(list))
                    cache.save(list)
                } else cache.clearCache()
            }
        }
    }

    @Throws(IOException::class)
    override fun reload(cgu: String?, onResultListener: OnResultListener<List<HistoryBook>>) {
        cloud.get(cgu) { list: List<HistoryBookEntity> ->
            if (validateCgu(cgu)) {
                onResultListener(mapper.tansformBack(list))
                cache.save(list)
            } else cache.clearCache()
        }
    }

    @Throws(IOException::class)
    private fun validateCgu(cgu: String?): Boolean {
        val user = userOnCache.get()
        return user.cgu == cgu
    }

}