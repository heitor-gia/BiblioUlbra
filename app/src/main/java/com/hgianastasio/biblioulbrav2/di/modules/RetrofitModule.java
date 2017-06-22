package com.hgianastasio.biblioulbrav2.di.modules;

import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi;
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApiProvider;
import com.hgianastasio.biblioulbrav2.data.network.OkHttpClientProvider;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    BiblioUlbraApi provideApi(BiblioUlbraApiProvider provider){
        return provider.get();
    }

    @Provides
    @Singleton
    OkHttpClient provider(OkHttpClientProvider provider){return provider.get();}
}
