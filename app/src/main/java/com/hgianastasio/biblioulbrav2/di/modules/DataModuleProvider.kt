package com.hgianastasio.biblioulbrav2.di.modules

import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.base.search.SearchBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCloud
import com.hgianastasio.biblioulbrav2.data.disk.HistoryBooksOnCacheImpl
import com.hgianastasio.biblioulbrav2.data.disk.LoanBooksOnCacheImpl
import com.hgianastasio.biblioulbrav2.data.disk.UserOnCacheImpl
import com.hgianastasio.biblioulbrav2.data.disk.db.DBHelper
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.search.searchbook.SearchBookEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.search.searchmodel.SearchModelEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.search.searchresult.SearchResultEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntityMapper
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntityMapper
import com.hgianastasio.biblioulbrav2.data.network.cloud.HistoryBooksOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.network.cloud.LoanBooksOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.network.cloud.SearchBooksOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.network.cloud.UserOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.repositories.HistoryBooksRepositoryImp
import com.hgianastasio.biblioulbrav2.data.repositories.LoanBooksRepositoryImp
import com.hgianastasio.biblioulbrav2.data.repositories.SearchBookRepositoryImpl
import com.hgianastasio.biblioulbrav2.data.repositories.UserRepositoryImpl
import com.hgianastasio.biblioulbrav2.di.ModuleProvider

/**
 * Created by heitorgianastasio on 4/29/17.
 */
object DataModuleProvider : ModuleProvider({

    single<UserOnCloud> { UserOnCloudImpl(api = get()) }
    single<UserOnCache> { UserOnCacheImpl(mContext = get()) }

    single<HistoryBooksOnCache> { HistoryBooksOnCacheImpl(dbHelper = get(), preferences = get()) }
    single { HistoryBooksOnCacheImpl.HistoryBookPreferences(mContext = get()) }
    single<HistoryBooksOnCloud> { HistoryBooksOnCloudImpl(api = get()) }

    single<LoanBooksOnCache> { LoanBooksOnCacheImpl(dbHelper = get(), preferences = get()) }
    single { LoanBooksOnCacheImpl.LoanBookPreferences(mContext = get()) }
    single<LoanBooksOnCloud> { LoanBooksOnCloudImpl(api = get()) }


    single<HistoryBooksRepository> {
        HistoryBooksRepositoryImp(
                cloud = get(),
                cache = get(),
                mapper = get(),
                userOnCache = get()
        )
    }


    single<LoanBooksRepository> {
        LoanBooksRepositoryImp(
                cloud = get(),
                cache = get(),
                mapper = get(),
                userOnCache = get()
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
                cloud = get(),
                cache = get(),
                historyBooksOnCache = get(),
                loanBooksOnCache = get(),
                renewMapper = get(),
                userMapper = get()
        )
    }


    single<SearchBooksOnCloud> { SearchBooksOnCloudImpl(api = get()) }
    single<SearchBookRepository> { SearchBookRepositoryImpl(cloud = get(), modelEntityMapper = get(), resultEntityMapper = get()) }

    single { DBHelper(mContext = get()) }

    single { SearchResultEntityMapper(entityMapper = get()) }
    single { SearchModelEntityMapper() }
    single { SearchBookEntityMapper() }
    single { LoanBookEntityMapper() }
    single { HistoryBookEntityMapper() }
    single { RenewLoansResponseEntityMapper() }
    single { UserEntityMapper() }
})