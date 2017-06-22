package com.hgianastasio.biblioulbrav2.data.repositories;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntityMapper;

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


    @Inject
    public LoanBooksRepositoryImp(
            LoanBooksOnCloud cloud,
            LoanBooksOnCache cache,
            LoanBookEntityMapper mapper) {
        this.cloud = cloud;
        this.cache = cache;
        this.mapper = mapper;
    }


    @Override
    public void get(String cgu, OnResultListener<List<LoanBook>> onResultListener) throws IOException {
        if(cache.isUpdated()){
            onResultListener.onResult(mapper.tansformBack(cache.get()));
        }else{
            cloud.get(cgu,(list)->{
                onResultListener.onResult(mapper.tansformBack(list));
                cache.save(list);
            });
        }
    }

    @Override
    public void reload(String cgu, OnResultListener<List<LoanBook>> onResultListener) throws IOException {
        cloud.get(cgu,(list)->{
            onResultListener.onResult(mapper.tansformBack(list));
            cache.save(list);
        });
    }

}
