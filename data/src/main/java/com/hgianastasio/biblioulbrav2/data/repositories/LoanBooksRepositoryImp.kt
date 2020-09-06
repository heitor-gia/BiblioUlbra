package com.hgianastasio.biblioulbrav2.data.repositories

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntityMapper
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class LoanBooksRepositoryImp @Inject constructor(
        private val cloud: LoanBooksOnCloud,
        private val cache: LoanBooksOnCache,
        private val mapper: LoanBookEntityMapper,
        private val userOnCache: UserOnCache
) : LoanBooksRepository {
    @Throws(IOException::class)
    override fun get(cgu: String?, onResultListener: OnResultListener<List<LoanBook>>) {
        if (cache.isUpdated) {
            onResultListener(mapper.tansformBack(cache.get()))
        } else {
            cloud.get(cgu) { list: List<LoanBookEntity> ->
                if (validateCgu(cgu)) {
                    onResultListener(mapper.tansformBack(list))
                    cache.save(list)
                } else cache.clearCache()
            }
        }
    }

    @Throws(IOException::class)
    override fun reload(cgu: String?, onResultListener: OnResultListener<List<LoanBook>>) {
        cloud.get(cgu) { list: List<LoanBookEntity> ->
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