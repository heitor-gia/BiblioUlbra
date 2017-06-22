package com.hgianastasio.biblioulbrav2.data.network;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;

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
                .build();
    }

    public OkHttpClient get(){
        return client;
    }
}
