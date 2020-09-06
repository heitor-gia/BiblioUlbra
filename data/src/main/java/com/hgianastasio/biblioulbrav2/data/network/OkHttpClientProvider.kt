package com.hgianastasio.biblioulbrav2.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by heitor_12 on 12/05/17.
 */
@Singleton
class OkHttpClientProvider @Inject constructor() {
    var client: OkHttpClient
    fun get(): OkHttpClient {
        return client
    }

    init {
        client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }
}