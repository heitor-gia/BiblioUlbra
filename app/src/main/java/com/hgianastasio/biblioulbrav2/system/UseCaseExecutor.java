package com.hgianastasio.biblioulbrav2.system;

import android.app.Activity;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.di.PerActivity;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by heitor_12 on 24/05/17.
 */
@PerActivity
public class UseCaseExecutor {
    Activity activity;

    @Inject
    public UseCaseExecutor(Activity activity) {
        this.activity = activity;
    }

    public  <T,P> void execute(UseCase<T,P> useCase, P parameter, OnResultListener<T> resultListener, OnErrorListener errorListener){
        useCase.execute(parameter,
                result->resultToUiThread(result,resultListener,errorListener),
                error-> activity.runOnUiThread(()-> errorListener.onError(error))
        );
    }

    public <T>void resultToUiThread(T result, OnResultListener<T> onResultListener, OnErrorListener errorListener){
        activity.runOnUiThread(() -> {
            try {
                onResultListener.onResult(result);
            } catch (Exception e) {
                errorListener.onError(e);
            }
        });
        activity.runOnUiThread(this::init);
    }

    private void init(){}
}
