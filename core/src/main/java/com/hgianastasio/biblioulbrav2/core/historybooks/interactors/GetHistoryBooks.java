package com.hgianastasio.biblioulbrav2.core.historybooks.interactors;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;
import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository;
import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class GetHistoryBooks extends UseCase<List<HistoryBook>,Void > {
    HistoryBooksRepository repository;
    UserRepository userRepository;

    @Inject
    public GetHistoryBooks(ThreadPoolExecutor executor, HistoryBooksRepository repository,UserRepository userRepository) {
        super(executor);
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    protected void process(Void unused, OnResultListener<List<HistoryBook>> resultListener, OnErrorListener errorListener) {
        try{
            userRepository.get(
                    user-> repository.get(user.getCgu(),resultListener)
            );

        }catch (Exception e){
            errorListener.onError(e);
        }
    }
}
