package com.hgianastasio.biblioulbrav2.di.modules

import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApiProvider
import com.hgianastasio.biblioulbrav2.data.network.OkHttpClientProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideApi(provider: BiblioUlbraApiProvider): BiblioUlbraApi {
        return provider.get()
    }

    @Provides
    @Singleton
    fun provider(provider: OkHttpClientProvider): OkHttpClient {
        return provider.get()
    }
}