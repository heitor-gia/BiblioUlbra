package com.hgianastasio.biblioulbrav2.di.modules

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
import com.hgianastasio.biblioulbrav2.di.ModuleProvider

/**
 * Created by holiveira at 07/09/20
 */
object UseCaseModuleProvider : ModuleProvider({
    factory { GetHistoryBooks(repository = get(), executor = get(), userRepository = get()) }
    factory { ReloadHitoryBooks(repository = get(), userRepository = get(), executor = get()) }
    factory { GetLoanBooks(executor = get(), userRepository = get(), booksRepository = get()) }
    factory { ReloadLoanBooks(booksRepository = get(), userRepository = get(), executor = get()) }
    factory { SearchBooks(executor = get(), repository = get()) }
    factory { SearchBooksNextPage(repository = get(), executor = get()) }
    factory { SearchBooksPrevPage(executor = get(), repository = get()) }
    factory { GetUser(repository = get(), executor = get()) }
    factory { LoadCache(executor = get(), userRepository = get(), historyBooksRepository = get(), loanBooksRepository = get()) }
    factory { LogoutUser(executor = get(), repository = get()) }
    factory { RenewLoans(repository = get(), executor = get(), loanBooksRepository = get()) }
    factory { LoginUser(executor = get(), repository = get()) }
})