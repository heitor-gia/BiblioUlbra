package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 13/05/17.
 */

public class LoadCachePresenter extends BasePresenter {
    LoadCache loadCache;


    @Inject
    public LoadCachePresenter(UseCaseExecutor executor, LoadCache loadCache) {
        super(executor);
        this.loadCache = loadCache;
    }

    public void loadCache(){
        if (progressListener!=null) progressListener.showProgress();
        useCaseExecutor.execute(loadCache,null,
                unused->{
                    if (progressListener!=null) progressListener.hideProgress();
                },null);
    }
}
