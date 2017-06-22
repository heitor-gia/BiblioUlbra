package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.GetLoanBooks;
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.ReloadLoanBooks;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache;
import com.hgianastasio.biblioulbrav2.di.PerActivity;
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel;
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel;
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModelMapper;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */
@PerActivity
public class LoanBooksListPresenter extends BasePresenter {
    GetLoanBooks getLoanBooks;
    ReloadLoanBooks reloadLoanBooks;
    LoadCache loadCache;
    LoanBookModelMapper mapper;
    OnResultListener<List<LoanBook>> loadCacheListener;

    @Inject
    public LoanBooksListPresenter(
            UseCaseExecutor useCaseExecutor,
            GetLoanBooks getLoanBooks,
            ReloadLoanBooks reloadLoanBooks,
            LoadCache loadCache,
            LoanBookModelMapper mapper) {
        super(useCaseExecutor);
        this.getLoanBooks = getLoanBooks;
        this.reloadLoanBooks = reloadLoanBooks;
        this.loadCache = loadCache;
        this.mapper = mapper;
    }

    private void executeHistoryBooksUseCase(UseCase<List<LoanBook>, ?> useCase, OnResultListener<List<LoanBookModel>> onResultListener, OnErrorListener onErrorListener) {
        if(progressListener!=null)progressListener.showProgress();

        if (loadCache.isLoansCacheLoading()){
            loadCache.addLoanBooksResultListener((loadCacheListener = loadCacheListener(onResultListener,onErrorListener)));
        }
        else
            useCaseExecutor.execute(useCase, null,
                result -> processResult(result,onResultListener),
                error  -> processError(error,onErrorListener)
            );
    }

    private OnResultListener<List<LoanBook>> loadCacheListener(OnResultListener<List<LoanBookModel>> resultListener, OnErrorListener errorListener){
        return result ->
                useCaseExecutor.resultToUiThread(
                        result,
                        list  -> processResult(list,resultListener),
                        error -> processError(error,errorListener)
                );
    }

    private void processResult(List<LoanBook> result, OnResultListener<List<LoanBookModel>> onResultListener) throws IOException {
        if (progressListener!=null)progressListener.hideProgress();
        onResultListener.onResult(mapper.trasform(result));
    }

    private void processError(Exception error,OnErrorListener onErrorListener ){
        if(progressListener!=null){
            progressListener.showRetry();
            progressListener.hideProgress();
        }
        onErrorListener.onError(error);
    }

    public void getLoanBooks(OnResultListener<List<LoanBookModel>> onResultListener, OnErrorListener onErrorListener) {
        executeHistoryBooksUseCase(getLoanBooks,onResultListener,onErrorListener);
    }

    public void reloadLoanBooks(OnResultListener<List<LoanBookModel>> onResultListener, OnErrorListener onErrorListener) {
        if(loadCache.isLoansCacheLoading())
            loadCache.addLoanBooksResultListener(result -> useCaseExecutor.resultToUiThread(mapper.trasform(result),onResultListener,onErrorListener));
        else
            executeHistoryBooksUseCase(reloadLoanBooks,onResultListener,onErrorListener);
    }

    @Override
    public void destroy() {
        super.destroy();
        loadCache.removeLoanBooksResultListeners(loadCacheListener);
    }
}