package com.hgianastasio.biblioulbrav2.core.user.interactors;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.user.models.User;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by heitorgianastasio on 4/25/17.
 */
@Singleton
public class LoginUser extends UseCase<User,String> {

    UserRepository repository;

    @Inject
    public LoginUser(ThreadPoolExecutor executor, UserRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    protected void process(String cgu, OnResultListener<User> resultListener, OnErrorListener errorListener) {
        try {
            repository.login(cgu,
                    (user)->{
                        if(repository.save(user)){
                            resultListener.onResult(user);
                        }else{
                            errorListener.onError(new ConnectionException());
                        }
            });
        } catch (Exception e){
            errorListener.onError(e);
        }
    }
}
