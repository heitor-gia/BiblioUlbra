package com.hgianastasio.biblioulbrav2.di.modules

import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApiProvider
import com.hgianastasio.biblioulbrav2.data.network.OkHttpClientProvider
import com.hgianastasio.biblioulbrav2.di.ModuleProvider

/**
 * Created by heitorgianastasio on 4/29/17.
 */
object RetrofitModuleProvider: ModuleProvider( {
    single { BiblioUlbraApiProvider(client = get()).get() }
    single { OkHttpClientProvider().get() }
})