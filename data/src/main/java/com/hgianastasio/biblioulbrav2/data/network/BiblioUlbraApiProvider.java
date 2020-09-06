package com.hgianastasio.biblioulbrav2.data.network;


import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heitorgianastasio on 4/26/17.
 */
public class BiblioUlbraApiProvider {
    BiblioUlbraApi biblioUlbraApi;

    @Inject
    public BiblioUlbraApiProvider(OkHttpClient client) {
         biblioUlbraApi = new Retrofit.Builder()
                        .baseUrl("http://www.ulbratorres.com.br/biblioUlbra/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(BiblioUlbraApi.class);
    }

    public BiblioUlbraApi get(){
        return biblioUlbraApi;
    }
}
