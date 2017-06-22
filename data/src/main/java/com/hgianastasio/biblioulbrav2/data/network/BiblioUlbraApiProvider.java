package com.hgianastasio.biblioulbrav2.data.network;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
                        .addConverterFactory(LoganSquareConverterFactory.create())
                        .build()
                        .create(BiblioUlbraApi.class);
    }

    public BiblioUlbraApi get(){
        return biblioUlbraApi;
    }
}
