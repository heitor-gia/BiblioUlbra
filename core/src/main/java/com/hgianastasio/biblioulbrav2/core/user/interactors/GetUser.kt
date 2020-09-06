package com.hgianastasio.biblioulbrav2.core.user.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.user.models.User
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class GetUser @Inject constructor(executor: ThreadPoolExecutor, var repository: UserRepository) : UseCase<User, Void?>(executor) {
    override fun process(unused: Void?, resultListener: OnResultListener<User>, errorListener: OnErrorListener) {
        try {
            repository.get(resultListener)
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}