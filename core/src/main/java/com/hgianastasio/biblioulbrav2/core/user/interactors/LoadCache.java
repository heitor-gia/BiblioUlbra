package com.hgianastasio.biblioulbrav2.core.user.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;
import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by heitor_12 on 13/05/17.
 */
@Singleton
public class LoadCache extends UseCase<Void,Void> {
    private UserRepository userRepository;
    private LoanBooksRepository loanBooksRepository;
    private HistoryBooksRepository historyBooksRepository;
    private ArrayList<OnResultListener<List<HistoryBook>>> historyBooksResultListeners = new ArrayList<>();
    private ArrayList<OnResultListener<List<LoanBook>>> loanBooksResultListeners = new ArrayList<>();
    private boolean historyCacheLoading = false;
    private boolean loansCacheLoading = false;

    @Inject
    public LoadCache(
            ThreadPoolExecutor executor,
            UserRepository userRepository,
            LoanBooksRepository loanBooksRepository,
            HistoryBooksRepository historyBooksRepository) {
        super(executor);
        this.userRepository = userRepository;
        this.loanBooksRepository = loanBooksRepository;
        this.historyBooksRepository = historyBooksRepository;

    }

    @Override
    protected void process(Void params, OnResultListener<Void> resultListener, OnErrorListener unused) {
        userRepository.get(
            user->{
                loadHistoryCache(user.getCgu(),resultListener);
                loadLoansCache(user.getCgu(),resultListener);
            }
        );
    }

    private void loadHistoryCache(String cgu, OnResultListener<Void> resultListener){
        executor.execute(() ->{
            try {
                historyCacheLoading = true;
                historyBooksRepository.get(cgu, result -> processHistoryResult(result,resultListener));
            } catch (IOException e) {
                historyCacheLoading = false;
                e.printStackTrace();
            }
        });

    }

    private void processHistoryResult(List<HistoryBook> result,OnResultListener<Void> resultListener) throws IOException {
        for (OnResultListener<List<HistoryBook>> listener : historyBooksResultListeners){
            listener.onResult(result);
        }
        historyCacheLoading = false;
        if (!loansCacheLoading) resultListener.onResult(null);
    }


    private void loadLoansCache(String cgu, OnResultListener<Void> resultListener){
        executor.execute(() ->{
            try {
                loansCacheLoading = true;
                loanBooksRepository.get(cgu,result -> processLoansResult(result,resultListener));
            } catch (IOException e) {
                loansCacheLoading = false;
                e.printStackTrace();
            }
        });
    }

    private void processLoansResult(List<LoanBook> result,OnResultListener<Void> resultListener) throws IOException {
        for (OnResultListener<List<LoanBook>> listener : loanBooksResultListeners){
            listener.onResult(result);
        }
        loansCacheLoading = false;
        if (!historyCacheLoading) resultListener.onResult(null);
    }

    public void addHistoryBooksResultListener(OnResultListener<List<HistoryBook>> historyBooksResultListener) {
        historyBooksResultListeners.add(historyBooksResultListener);
    }

    public void addLoanBooksResultListener(OnResultListener<List<LoanBook>> loanBooksResultListener) {
        loanBooksResultListeners.add(loanBooksResultListener);
    }

    public void removeHistoryBooksResultListener(OnResultListener<List<HistoryBook>> historyBooksResultListener) {
        historyBooksResultListeners.remove(historyBooksResultListener);
    }

    public void removeLoanBooksResultListeners(OnResultListener<List<LoanBook>> loanBooksResultListener) {
        loanBooksResultListeners.remove(loanBooksResultListener);
    }

    public boolean isHistoryCacheLoading() {
        return historyCacheLoading;
    }

    public boolean isLoansCacheLoading() {
        return loansCacheLoading;
    }
}
