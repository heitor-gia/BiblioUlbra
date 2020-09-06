package com.hgianastasio.biblioulbrav2.core.user.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException
import com.hgianastasio.biblioulbrav2.core.user.models.User
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by heitorgianastasio on 4/25/17.
 */
@Singleton
class LoginUser @Inject constructor(executor: ThreadPoolExecutor, var repository: UserRepository) : UseCase<User, String?>(executor) {
    override fun process(cgu: String?, resultListener: OnResultListener<User>, errorListener: OnErrorListener) {
        try {
            repository.login(cgu) { user: User ->
                if (repository.save(user)) {
                    resultListener(user)
                } else {
                    errorListener(ConnectionException())
                }
            }
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}