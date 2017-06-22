package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

/**
 * Created by heitor_12 on 04/05/17.
 */
public abstract class BasePresenter {
    protected OnProgressListener progressListener;
    protected UseCaseExecutor useCaseExecutor;


    public BasePresenter(UseCaseExecutor useCaseExecutor) {
        this.useCaseExecutor = useCaseExecutor;
    }


    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }


    public void unsetProgressListener(){
        progressListener = null;
    }

    public void destroy(){}

}
