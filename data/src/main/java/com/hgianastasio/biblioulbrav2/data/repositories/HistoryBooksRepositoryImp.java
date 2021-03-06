package com.hgianastasio.biblioulbrav2.data.repositories;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;
import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache;
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntityMapper;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class HistoryBooksRepositoryImp implements HistoryBooksRepository {

    private HistoryBooksOnCloud cloud;
    private HistoryBooksOnCache cache;
    private HistoryBookEntityMapper mapper;
    private UserOnCache userOnCache;


    @Inject
    public HistoryBooksRepositoryImp(
            HistoryBooksOnCloud cloud,
            HistoryBooksOnCache cache,
            HistoryBookEntityMapper mapper, UserOnCache userOnCache) {
        this.cloud = cloud;
        this.cache = cache;
        this.mapper = mapper;
        this.userOnCache = userOnCache;
    }


    @Override
    public void get(String cgu, OnResultListener<List<HistoryBook>> onResultListener) throws IOException {
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
    public void reload(String cgu, OnResultListener<List<HistoryBook>> onResultListener) throws IOException {

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
