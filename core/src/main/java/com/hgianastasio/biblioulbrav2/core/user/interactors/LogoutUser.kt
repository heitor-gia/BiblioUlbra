package com.hgianastasio.biblioulbrav2.core.user.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class LogoutUser @Inject constructor(executor: ThreadPoolExecutor, var repository: UserRepository) : UseCase<Void?, Void?>(executor) {
    override fun process(cgu: Void?, resultListener: OnResultListener<Void?>, errorListener: OnErrorListener) {
        try {
            repository.logout()
            resultListener(null)
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}