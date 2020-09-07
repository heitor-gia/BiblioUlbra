package com.hgianastasio.biblioulbrav2.data.repositories

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse
import com.hgianastasio.biblioulbrav2.core.user.models.User
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCloud
import com.hgianastasio.biblioulbrav2.data.exceptions.UserNotCachedException
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntityMapper
import java.io.IOException
/**
 * Created by heitorgianastasio on 4/26/17.
 */
class UserRepositoryImpl constructor(
        private val cache: UserOnCache,
        private val cloud: UserOnCloud,
        private val loanBooksOnCache: LoanBooksOnCache,
        private val historyBooksOnCache: HistoryBooksOnCache,
        private val userMapper: UserEntityMapper,
        private val renewMapper: RenewLoansResponseEntityMapper) : UserRepository {
    @Throws(FailLoginException::class, ConnectionException::class)
    override fun login(cgu: String?, onResultListener: OnResultListener<User>) {
        cloud.login(cgu) { u: UserEntity ->
            u.cgu = cgu
            onResultListener(userMapper.transformBack(u))
        }
    }

    override fun logout() {
        cache.clearCache()
        loanBooksOnCache.clearCache()
        historyBooksOnCache.clearCache()
    }

    @Throws(IOException::class)
    override fun save(user: User?): Boolean {
        return cache.save(userMapper.transform(user))
    }

    override fun get(onResultListener: OnResultListener<User>) {
        try {
            if (cache.isUpdated)
                onResultListener(userMapper.transformBack(cache.get()))
            else cloud.login(cache.get().cgu) { result: UserEntity -> onResultListener(userMapper.transformBack(result)) }
        } catch (e: Exception) {
            throw UserNotCachedException(e)
        }
    }

    @Throws(FailRenewException::class, ConnectionException::class)
    override fun renewLoans(cgu: String?, onResultListener: OnResultListener<RenewLoansResponse>) {
        cloud.renew(cgu) { response: RenewLoansResponseEntity -> onResultListener(renewMapper.transformBack(response)) }
    }

}