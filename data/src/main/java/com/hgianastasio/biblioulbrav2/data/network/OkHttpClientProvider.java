package com.hgianastasio.biblioulbrav2.data.network;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by heitor_12 on 12/05/17.
 */
@Singleton
public class OkHttpClientProvider {
    OkHttpClient client;

    @Inject
    public OkHttpClientProvider() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5,TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public OkHttpClient get(){
        return client;
    }
}
