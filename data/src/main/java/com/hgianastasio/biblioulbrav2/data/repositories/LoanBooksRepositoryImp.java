package com.hgianastasio.biblioulbrav2.data.repositories;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository;
import com.hgianastasio.biblioulbrav2.core.user.models.User;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntityMapper;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class LoanBooksRepositoryImp implements LoanBooksRepository {

    private LoanBooksOnCloud cloud;
    private LoanBooksOnCache cache;
    private LoanBookEntityMapper mapper;
    private UserOnCache userOnCache;

    @Inject
    public LoanBooksRepositoryImp(
            LoanBooksOnCloud cloud,
            LoanBooksOnCache cache,
            LoanBookEntityMapper mapper, UserOnCache userOnCache) {
        this.cloud = cloud;
        this.cache = cache;
        this.mapper = mapper;
        this.userOnCache = userOnCache;
    }


    @Override
    public void get(String cgu, OnResultListener<List<LoanBook>> onResultListener) throws IOException {
        if (cache.isUpdated()) {
            onResultListener.onResult(mapper.tansformBack(cache.get()));
        } else {
            cloud.get(cgu, (list) -> {
                if (validateCgu(cgu)) {
                    onResultListener.onResult(mapper.tansformBack(list));
                    cache.save(list);
                } else cache.clearCache();
            });
        }
    }

    @Override
    public void reload(String cgu, OnResultListener<List<LoanBook>> onResultListener) throws IOException {
        cloud.get(cgu, (list) -> {
            if (validateCgu(cgu)) {
                onResultListener.onResult(mapper.tansformBack(list));
                cache.save(list);
            } else cache.clearCache();
        });
    }

    private boolean validateCgu(String cgu) throws IOException {
        UserEntity user =  userOnCache.get();
        if(user==null) return false;
        else return user.getCgu().equals(cgu);
    }

}
