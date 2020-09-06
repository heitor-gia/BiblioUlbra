package com.hgianastasio.biblioulbrav2.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Singleton

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Module
class ApplicationModule(var application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideExecutor(): ThreadPoolExecutor {
        return Executors.newFixedThreadPool(15) as ThreadPoolExecutor
    }

}