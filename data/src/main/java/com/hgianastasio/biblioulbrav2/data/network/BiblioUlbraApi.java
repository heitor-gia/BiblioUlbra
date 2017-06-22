package com.hgianastasio.biblioulbrav2.data.network;

import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity;
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntity;
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by heitorgianastasio on 4/25/17.
 */
@Singleton
public interface BiblioUlbraApi {

    @GET("?funcao=h")
    Call<List<HistoryBookEntity>> getHistoryFromUser(@Query("cgu") String cgu);

    @GET("?funcao=e")
    Call<List<LoanBookEntity>> getLoansFromUser(@Query("cgu") String cgu);

    @GET("?funcao=l")
    Call<UserEntity> login(@Query("cgu") String cgu);

    @GET("?funcao=r")
    Call<RenewLoansResponseEntity> renew(@Query("cgu") String cgu);

    @GET("?funcao=b")
    Call<SearchResultEntity> search(
            @Query("busca") String search,
            @Query("cookie") String cookie,
            @Query("pag") int page,
            @Query("idioma") String language,
            @Query("mater") String media,
            @Query("campo") String field,
            @Query("local") String base
    );

}
