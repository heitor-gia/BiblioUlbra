package com.hgianastasio.biblioulbrav2.data.network.cloud

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCloud
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/28/17.
 */
class UserOnCloudImpl @Inject constructor(var api: BiblioUlbraApi) : UserOnCloud {
    @Throws(FailLoginException::class, ConnectionException::class)
    override fun login(cgu: String?, listener: OnResultListener<UserEntity>) {
        try {
            val result = api.login(cgu).execute().body()
            if (!result?.name.equals("falso", ignoreCase = true)) {
                result?.cgu = cgu
                listener(result ?: throw FailLoginException(cgu.orEmpty()))
            } else throw FailLoginException(cgu.orEmpty())
        } catch (e: IOException) {
            throw ConnectionException(e)
        }
    }

    @Throws(ConnectionException::class, FailRenewException::class)
    override fun renew(cgu: String?, listener: OnResultListener<RenewLoansResponseEntity>) {
        try {
            val result = api.renew(cgu).execute().body()
            if (result?.status.equals("sim", ignoreCase = true)) {
                listener(result ?: throw FailRenewException(result?.message))
            } else throw FailRenewException(result?.message)
        } catch (e: IOException) {
            throw ConnectionException(e)
        }
    }

}