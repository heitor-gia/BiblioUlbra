package com.hgianastasio.biblioulbrav2.core.user.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException;
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class RenewLoans extends UseCase<RenewLoansResponse,Void> {

    private UserRepository repository;
    private LoanBooksRepository loanBooksRepository;


    @Inject
    public RenewLoans(ThreadPoolExecutor executor, UserRepository repository, LoanBooksRepository loanBooksRepository) {
        super(executor);
        this.repository = repository;
        this.loanBooksRepository = loanBooksRepository;
    }

    @Override
    protected void process(Void unused, OnResultListener<RenewLoansResponse> resultListener, OnErrorListener errorListener) {
        try{
            repository
            .get(user->{
                try {
                    repository.renewLoans(user.getCgu(),
                        result ->
                            loanBooksRepository.reload(user.getCgu(),unused2->resultListener.onResult(result))
                        );
                } catch (FailRenewException e) {
                    errorListener.onError(e);
                }
            }
            );
        }catch (Exception e){
            errorListener.onError(e);
        }
    }

}
