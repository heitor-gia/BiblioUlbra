package com.hgianastasio.biblioulbrav2.core.user.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook
import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository
import com.hgianastasio.biblioulbrav2.core.user.models.User
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import java.io.IOException
import java.util.*
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by heitor_12 on 13/05/17.
 */
@Singleton
class LoadCache @Inject constructor(
        executor: ThreadPoolExecutor,
        private val userRepository: UserRepository,
        private val loanBooksRepository: LoanBooksRepository,
        private val historyBooksRepository: HistoryBooksRepository) : UseCase<Unit, Void?>(executor) {
    private val historyBooksResultListeners = ArrayList<OnResultListener<List<HistoryBook>>>()
    private val loanBooksResultListeners = ArrayList<OnResultListener<List<LoanBook>>>()
    var isHistoryCacheLoading = false
        private set
    var isLoansCacheLoading = false
        private set

    override fun process(params: Void?, resultListener: OnResultListener<Unit>, unused: OnErrorListener) {
        userRepository.get{ user: User ->
            loadHistoryCache(user.cgu, resultListener)
            loadLoansCache(user.cgu, resultListener)
        }
    }

    private fun loadHistoryCache(cgu: String?, resultListener: OnResultListener<Unit>) {
        executor.execute {
            try {
                isHistoryCacheLoading = true
                historyBooksRepository.get(cgu){ result: List<HistoryBook> -> processHistoryResult(result, resultListener) }
            } catch (e: IOException) {
                isHistoryCacheLoading = false
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    private fun processHistoryResult(result: List<HistoryBook>, resultListener: OnResultListener<Unit>) {
        for (listener in historyBooksResultListeners) {
            listener(result)
        }
        isHistoryCacheLoading = false
        if (!isLoansCacheLoading) resultListener(Unit)
    }

    private fun loadLoansCache(cgu: String?, resultListener: OnResultListener<Unit>) {
        executor.execute {
            try {
                isLoansCacheLoading = true
                loanBooksRepository.get(cgu) { result: List<LoanBook> -> processLoansResult(result, resultListener) }
            } catch (e: IOException) {
                isLoansCacheLoading = false
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    private fun processLoansResult(result: List<LoanBook>, resultListener: OnResultListener<Unit>) {
        for (listener in loanBooksResultListeners) {
            listener(result)
        }
        isLoansCacheLoading = false
        if (!isHistoryCacheLoading) resultListener(Unit)
    }

    fun addHistoryBooksResultListener(historyBooksResultListener: OnResultListener<List<HistoryBook>>) {
        historyBooksResultListeners.add(historyBooksResultListener)
    }

    fun addLoanBooksResultListener(loanBooksResultListener: OnResultListener<List<LoanBook>>) {
        loanBooksResultListeners.add(loanBooksResultListener)
    }

    fun removeHistoryBooksResultListener(historyBooksResultListener: OnResultListener<List<HistoryBook>>) {
        historyBooksResultListeners.remove(historyBooksResultListener)
    }

    fun removeLoanBooksResultListeners(loanBooksResultListener: OnResultListener<List<LoanBook>>) {
        loanBooksResultListeners.remove(loanBooksResultListener)
    }

}