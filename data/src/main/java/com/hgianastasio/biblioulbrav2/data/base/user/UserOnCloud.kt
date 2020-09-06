package com.hgianastasio.biblioulbrav2.data.base.user

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity

/**
 * Created by heitorgianastasio on 4/28/17.
 */
interface UserOnCloud {
    @Throws(FailLoginException::class, ConnectionException::class)
    fun login(cgu: String?, listener: OnResultListener<UserEntity>)

    @Throws(ConnectionException::class, FailRenewException::class)
    fun renew(cgu: String?, listener: OnResultListener<RenewLoansResponseEntity>)
}