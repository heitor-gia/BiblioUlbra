package com.hgianastasio.biblioulbrav2.di.modules

import com.hgianastasio.biblioulbrav2.di.ModuleProvider
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModelMapper
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModelMapper
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModelMapper
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModelMapper
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModelMapper
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModelMapper
import com.hgianastasio.biblioulbrav2.models.user.UserModelMapper
import com.hgianastasio.biblioulbrav2.presenters.HistoryBooksListPresenter
import com.hgianastasio.biblioulbrav2.presenters.LoadCachePresenter
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter
import com.hgianastasio.biblioulbrav2.presenters.RenewLoansPresenter
import com.hgianastasio.biblioulbrav2.presenters.SearchPresenter
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter

/**
 * Created by holiveira at 07/09/20
 */
object PresentationModuleProvider : ModuleProvider({
    factory {
        HistoryBooksListPresenter(
                executor = get(),
                getHistoryBooks = get(),
                loadCache = get(),
                mapper = get(),
                reloadHitoryBooks = get()
        )
    }
    factory {
        LoadCachePresenter(
                loadCache = get(),
                executor = get()
        )
    }
    factory {
        LoanBooksListPresenter(
                loadCache = get(),
                mapper = get(),
                getLoanBooks = get(),
                reloadLoanBooks = get(),
                useCaseExecutor = get(),
        )
    }
    factory {
        RenewLoansPresenter(
                mapper = get(),
                executor = get(),
                renewLoans = get()
        )
    }
    factory {
        SearchPresenter(
                useCaseExecutor = get(),
                modelMapper = get(),
                nextPage = get(),
                prevPage = get(),
                resultMapper = get(),
                searchBooks = get()
        )
    }
    factory {
        UserModelPresenter(
                executor = get(),
                mapper = get(),
                getUser = get(),
                loginUser = get(),
                logoutUser = get()
        )
    }

    single { UserModelMapper() }
    single { RenewLoansResponseModelMapper() }
    single { SearchResultModelMapper(bookModelMapper = get()) }
    single { SearchModelModelMapper() }
    single { SearchBookModelMapper() }
    single { LoanBookModelMapper() }
    single { HistoryBookModelMapper() }

})