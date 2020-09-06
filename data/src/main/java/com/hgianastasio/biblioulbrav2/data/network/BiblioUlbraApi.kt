package com.hgianastasio.biblioulbrav2.data.network

import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntity
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by heitorgianastasio on 4/25/17.
 */
@Singleton
interface BiblioUlbraApi {
    @GET("?funcao=h")
    fun getHistoryFromUser(@Query("cgu") cgu: String?): Call<List<HistoryBookEntity>>

    @GET("?funcao=e")
    fun getLoansFromUser(@Query("cgu") cgu: String?): Call<List<LoanBookEntity>>

    @GET("?funcao=l")
    fun login(@Query("cgu") cgu: String?): Call<UserEntity>

    @GET("?funcao=r")
    fun renew(@Query("cgu") cgu: String?): Call<RenewLoansResponseEntity>

    @GET("?funcao=b")
    fun search(
            @Query("busca") search: String?,
            @Query("cookie") cookie: String?,
            @Query("pag") page: Int,
            @Query("idioma") language: String?,
            @Query("mater") media: String?,
            @Query("campo") field: String?,
            @Query("local") base: String?
    ): Call<SearchResultEntity>
}