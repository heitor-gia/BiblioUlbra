package com.hgianastasio.biblioulbrav2.core.historybooks.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook
import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class ReloadHitoryBooks @Inject constructor(executor: ThreadPoolExecutor, var repository: HistoryBooksRepository, var userRepository: UserRepository) : UseCase<List<HistoryBook>, Void?>(executor) {
    override fun process(unused: Void?, resultListener: OnResultListener<List<HistoryBook>>, errorListener: OnErrorListener) {
        try {
            userRepository.get {
                repository.reload(it.cgu, resultListener)
            }
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}