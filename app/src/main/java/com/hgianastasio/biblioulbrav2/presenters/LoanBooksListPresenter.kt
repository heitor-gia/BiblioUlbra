package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.GetLoanBooks
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.ReloadLoanBooks
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModelMapper
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
import java.io.IOException

/**
 * Created by heitor_12 on 09/05/17.
 */
class LoanBooksListPresenter constructor(
        useCaseExecutor: UseCaseExecutor,
        var getLoanBooks: GetLoanBooks,
        var reloadLoanBooks: ReloadLoanBooks,
        var loadCache: LoadCache,
        var mapper: LoanBookModelMapper) : BasePresenter(useCaseExecutor) {
    var loadCacheListener: OnResultListener<List<LoanBook>>? = null
    private fun executeHistoryBooksUseCase(useCase: UseCase<List<LoanBook>, Void?>, onResultListener: OnResultListener<List<LoanBookModel>>, onErrorListener: OnErrorListener) {
        if (progressListener != null) progressListener!!.showProgress()
        if (loadCache.isLoansCacheLoading) {
            loadCache.addLoanBooksResultListener(loadCacheListener(onResultListener, onErrorListener).also { loadCacheListener = it })
        } else useCaseExecutor.execute(useCase, null,
                { result: List<LoanBook> -> processResult(result, onResultListener) },
                { error: Exception -> processError(error, onErrorListener) }
        )
    }

    private fun loadCacheListener(resultListener: OnResultListener<List<LoanBookModel>>, errorListener: OnErrorListener): OnResultListener<List<LoanBook>> {
        return { result: List<LoanBook> ->
            useCaseExecutor.resultToUiThread(
                    result,
                    { list: List<LoanBook> -> processResult(list, resultListener) },
                    { error: Exception -> processError(error, errorListener) }
            )
        }
    }

    @Throws(IOException::class)
    private fun processResult(result: List<LoanBook>, onResultListener: OnResultListener<List<LoanBookModel>>) {
        if (progressListener != null) progressListener!!.hideProgress()
        onResultListener(mapper.trasform(result))
    }

    private fun processError(error: Exception, onErrorListener: OnErrorListener) {
        if (progressListener != null) {
            progressListener!!.showRetry()
            progressListener!!.hideProgress()
        }
        onErrorListener(error)
    }

    fun getLoanBooks(onResultListener: OnResultListener<List<LoanBookModel>>, onErrorListener: OnErrorListener) {
        executeHistoryBooksUseCase(getLoanBooks, onResultListener, onErrorListener)
    }

    fun reloadLoanBooks(onResultListener: OnResultListener<List<LoanBookModel>>, onErrorListener: OnErrorListener) {
        if (loadCache.isLoansCacheLoading) loadCache.addLoanBooksResultListener { result -> useCaseExecutor.resultToUiThread(mapper.trasform(result), onResultListener, onErrorListener) } else executeHistoryBooksUseCase(reloadLoanBooks, onResultListener, onErrorListener)
    }

    override fun destroy() {
        super.destroy()
        loadCacheListener?.let { loadCache.removeLoanBooksResultListeners(it) }
    }

}