package com.hgianastasio.biblioulbrav2.core.user.interactors;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.user.models.User;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class GetUser extends UseCase<User,Void> {

    UserRepository repository;

    @Inject
    public GetUser(ThreadPoolExecutor executor, UserRepository repository) {
        super(executor);
        this.repository = repository;
    }


    @Override
    protected void process(Void unused, OnResultListener<User> resultListener, OnErrorListener errorListener) {
        try {
            repository.get(resultListener);
        } catch (Exception e){
            errorListener.onError(e);
        }
    }
}
