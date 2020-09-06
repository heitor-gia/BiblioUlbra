package com.hgianastasio.biblioulbrav2.core.loanbooks.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class GetLoanBooks @Inject constructor(executor: ThreadPoolExecutor, var booksRepository: LoanBooksRepository, var userRepository: UserRepository) : UseCase<List<LoanBook>, Void?>(executor) {
    override fun process(unused: Void?, resultListener: OnResultListener<List<LoanBook>>, errorListener: OnErrorListener) {
        try {
            userRepository.get {
                booksRepository.get(it.cgu, resultListener)
            }
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}