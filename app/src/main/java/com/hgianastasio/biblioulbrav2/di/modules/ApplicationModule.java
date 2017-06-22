package com.hgianastasio.biblioulbrav2.di.modules;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Module
public class ApplicationModule {
    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Context provideContext(){return this.application;}

    @Provides @Singleton
    ThreadPoolExecutor provideExecutor(){return (ThreadPoolExecutor) Executors.newFixedThreadPool(15);}
}
