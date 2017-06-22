package com.hgianastasio.biblioulbrav2.core.base;


import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public abstract class UseCase<T,P> {

    protected ThreadPoolExecutor executor;

    protected UseCase(ThreadPoolExecutor executor){
        this.executor = executor;
    }

    protected abstract void process(P params, OnResultListener<T> resultListener, OnErrorListener errorListener);

    public void execute(P params, OnResultListener<T> resultListener, OnErrorListener errorListener){
        executor.execute(()->process(params,resultListener,errorListener));
    }





}
