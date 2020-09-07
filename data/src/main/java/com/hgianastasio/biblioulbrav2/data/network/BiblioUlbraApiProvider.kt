package com.hgianastasio.biblioulbrav2.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by heitorgianastasio on 4/26/17.
 */
class BiblioUlbraApiProvider constructor(client: OkHttpClient?) {
    var biblioUlbraApi: BiblioUlbraApi
    fun get(): BiblioUlbraApi {
        return biblioUlbraApi
    }

    init {
        biblioUlbraApi = Retrofit.Builder()
                .baseUrl("http://www.ulbratorres.com.br/biblioUlbra/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BiblioUlbraApi::class.java)
    }
}