package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.GetHistoryBooks;
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.ReloadHitoryBooks;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache;
import com.hgianastasio.biblioulbrav2.di.PerActivity;
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel;
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModelMapper;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */
@PerActivity
public class HistoryBooksListPresenter extends BasePresenter {
    GetHistoryBooks getHistoryBooks;
    ReloadHitoryBooks reloadHitoryBooks;
    HistoryBookModelMapper mapper;
    LoadCache loadCache;
    OnResultListener<List<HistoryBook>> loadCacheListener;



    @Inject
    public HistoryBooksListPresenter(
            UseCaseExecutor executor,
            GetHistoryBooks getHistoryBooks,
            ReloadHitoryBooks reloadHitoryBooks,
            LoadCache loadCache,
            HistoryBookModelMapper mapper) {
        super(executor);
        this.getHistoryBooks = getHistoryBooks;
        this.reloadHitoryBooks = reloadHitoryBooks;
        this.mapper = mapper;
        this.loadCache = loadCache;
    }


    private void executeHistoryBooksUseCase(UseCase<List<HistoryBook>,?> useCase, OnResultListener<List<HistoryBookModel>> onResultListener, OnErrorListener onErrorListener) {
        if(progressListener!=null) progressListener.showProgress();
        if(loadCache.isHistoryCacheLoading()){
            loadCache.addHistoryBooksResultListener((loadCacheListener=loadCacheListener(onResultListener,onErrorListener)));
        }
        else {
            useCaseExecutor.execute(useCase, null,
                    result -> processResult(result, onResultListener),
                    error  -> processError(error, onErrorListener)
            );
        }
    }

    private OnResultListener<List<HistoryBook>> loadCacheListener(OnResultListener<List<HistoryBookModel>> resultListener,OnErrorListener errorListener){
        return result ->
                useCaseExecutor.resultToUiThread(
                        result,
                        list  -> processResult(list,resultListener),
                        error -> processError(error,errorListener)
                );
    }

    private void processError(Exception error, OnErrorListener onErrorListener){
        if(progressListener!=null){
            progressListener.showRetry();
            progressListener.hideProgress();
        }
        onErrorListener.onError(error);
    }

    private void processResult(List<HistoryBook> result, OnResultListener<List<HistoryBookModel>> onResultListener) throws IOException {
        if (progressListener!=null)progressListener.hideProgress();
        onResultListener.onResult(mapper.trasform(result));
    }

    public void getHistoryBooks(OnResultListener<List<HistoryBookModel>> onResultListener, OnErrorListener onErrorListener) {
        executeHistoryBooksUseCase(getHistoryBooks,onResultListener,onErrorListener);
    }

    public void reloadHistoryBooks(OnResultListener<List<HistoryBookModel>> onResultListener, OnErrorListener onErrorListener) {
        executeHistoryBooksUseCase(reloadHitoryBooks,onResultListener,onErrorListener);
    }

    @Override
    public void destroy() {
        super.destroy();
        loadCache.removeHistoryBooksResultListener(loadCacheListener);
    }
}