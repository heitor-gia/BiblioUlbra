package com.hgianastasio.biblioulbrav2.di.components

import android.content.Context
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.GetHistoryBooks
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.ReloadHitoryBooks
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.GetLoanBooks
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.ReloadLoanBooks
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooks
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksNextPage
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksPrevPage
import com.hgianastasio.biblioulbrav2.core.user.interactors.GetUser
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoginUser
import com.hgianastasio.biblioulbrav2.core.user.interactors.LogoutUser
import com.hgianastasio.biblioulbrav2.core.user.interactors.RenewLoans
import com.hgianastasio.biblioulbrav2.di.modules.ApplicationModule
import com.hgianastasio.biblioulbrav2.di.modules.DataModule
import com.hgianastasio.biblioulbrav2.di.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class, DataModule::class])
interface ApplicationComponent {
    //Application Context
    fun context(): Context

    //UseCases
    fun loginUser(): LoginUser
    fun logoutUser(): LogoutUser
    val user: GetUser
    fun renewLoans(): RenewLoans
    fun loadCache(): LoadCache
    val historyBooks: GetHistoryBooks
    fun reloadHistoryBooks(): ReloadHitoryBooks
    val loanBooks: GetLoanBooks
    fun reloadLoanBooks(): ReloadLoanBooks
    fun searchBooks(): SearchBooks
    fun searchBooksNP(): SearchBooksNextPage
    fun searchBooksPP(): SearchBooksPrevPage
}