package com.hgianastasio.biblioulbrav2.core.loanbooks.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class ReloadLoanBooks extends UseCase<List<LoanBook>,Void > {
    LoanBooksRepository booksRepository;
    UserRepository userRepository;

    @Inject
    public ReloadLoanBooks(ThreadPoolExecutor executor, LoanBooksRepository booksRepository, UserRepository userRepository) {
        super(executor);
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void process(Void unused, OnResultListener<List<LoanBook>> resultListener, OnErrorListener errorListener) {
        try{
            userRepository.get((user)->
                booksRepository.reload(user.getCgu(),resultListener)
            );
        }catch (Exception e){
            errorListener.onError(e);
        }
    }
}
