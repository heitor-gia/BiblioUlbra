package com.hgianastasio.biblioulbrav2.core.user.repository

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse
import com.hgianastasio.biblioulbrav2.core.user.models.User
import java.io.IOException

/**
 * Created by heitorgianastasio on 4/24/17.
 */
interface UserRepository {
    @Throws(FailLoginException::class, ConnectionException::class)
    fun login(cgu: String?, onResultListener: OnResultListener<User>)
    fun logout()

    @Throws(IOException::class)
    fun save(user: User?): Boolean
    fun get(onResultListener: OnResultListener<User>)

    @Throws(FailRenewException::class, ConnectionException::class)
    fun renewLoans(cgu: String?, onResultListener: OnResultListener<RenewLoansResponse>)
}