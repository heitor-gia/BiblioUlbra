package com.hgianastasio.biblioulbrav2.core.user.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse
import com.hgianastasio.biblioulbrav2.core.user.models.User
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class RenewLoans @Inject constructor(executor: ThreadPoolExecutor, private val repository: UserRepository, private val loanBooksRepository: LoanBooksRepository) : UseCase<RenewLoansResponse, Void?>(executor) {
    override fun process(unused: Void?, resultListener: OnResultListener<RenewLoansResponse>, errorListener: OnErrorListener) {
        try {
            repository.get { user: User ->
                try {
                    repository.renewLoans(user.cgu) { result: RenewLoansResponse ->
                        loanBooksRepository.reload(user.cgu) { resultListener(result) }
                    }
                } catch (e: FailRenewException) {
                    errorListener(e)
                }
            }
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}