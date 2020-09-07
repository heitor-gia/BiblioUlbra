package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.GetHistoryBooks
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.ReloadHitoryBooks
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache
import com.hgianastasio.biblioulbrav2.di.PerActivity
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModelMapper
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
@PerActivity
class HistoryBooksListPresenter @Inject constructor(
        executor: UseCaseExecutor,
        var getHistoryBooks: GetHistoryBooks,
        var reloadHitoryBooks: ReloadHitoryBooks,
        var loadCache: LoadCache,
        var mapper: HistoryBookModelMapper) : BasePresenter(executor) {
    var loadCacheListener: OnResultListener<List<HistoryBook>>? = null

    private fun executeHistoryBooksUseCase(useCase: UseCase<List<HistoryBook>,Void?>, onResultListener: OnResultListener<List<HistoryBookModel>>, onErrorListener: OnErrorListener) {
        if (progressListener != null) progressListener!!.showProgress()
        if (loadCache.isHistoryCacheLoading) {
            loadCache.addHistoryBooksResultListener(loadCacheListener(onResultListener, onErrorListener).also { loadCacheListener = it })
        } else {
            useCaseExecutor.execute(useCase, null,
                    { result: List<HistoryBook> -> processResult(result, onResultListener) },
                    { error: Exception -> processError(error, onErrorListener) }
            )
        }
    }

    private fun loadCacheListener(resultListener: OnResultListener<List<HistoryBookModel>>, errorListener: OnErrorListener): OnResultListener<List<HistoryBook>> {
        return { result: List<HistoryBook> ->
            useCaseExecutor.resultToUiThread(
                    result,
                    { list: List<HistoryBook> -> processResult(list, resultListener) },
                    { error: Exception -> processError(error, errorListener) }
            )
        }
    }

    private fun processError(error: Exception, onErrorListener: OnErrorListener) {
        if (progressListener != null) {
            progressListener!!.showRetry()
            progressListener!!.hideProgress()
        }
        onErrorListener(error)
    }

    @Throws(IOException::class)
    private fun processResult(result: List<HistoryBook>, onResultListener: OnResultListener<List<HistoryBookModel>>) {
        if (progressListener != null) progressListener!!.hideProgress()
        onResultListener(mapper.trasform(result))
    }

    fun getHistoryBooks(onResultListener: OnResultListener<List<HistoryBookModel>>, onErrorListener: OnErrorListener) {
        executeHistoryBooksUseCase(getHistoryBooks, onResultListener, onErrorListener)
    }

    fun reloadHistoryBooks(onResultListener: OnResultListener<List<HistoryBookModel>>, onErrorListener: OnErrorListener) {
        executeHistoryBooksUseCase(reloadHitoryBooks, onResultListener, onErrorListener)
    }

    override fun destroy() {
        super.destroy()
        loadCacheListener?.let { loadCache.removeHistoryBooksResultListener(it) }
    }

}