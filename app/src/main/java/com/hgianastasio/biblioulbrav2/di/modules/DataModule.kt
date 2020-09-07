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
import com.hgianastasio.biblioulbrav2.data.network.cloud.HistoryBooksOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.network.cloud.LoanBooksOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.network.cloud.SearchBooksOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.network.cloud.UserOnCloudImpl
import com.hgianastasio.biblioulbrav2.data.repositories.HistoryBooksRepositoryImp
import com.hgianastasio.biblioulbrav2.data.repositories.LoanBooksRepositoryImp
import com.hgianastasio.biblioulbrav2.data.repositories.SearchBookRepositoryImpl
import com.hgianastasio.biblioulbrav2.data.repositories.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Module
class DataModule {
    @Provides
    @Singleton
    fun provideUserOnCloud(cloud: UserOnCloudImpl): UserOnCloud {
        return cloud
    }

    @Provides
    @Singleton
    fun provideUserOnCache(cache: UserOnCacheImpl): UserOnCache {
        return cache
    }

    @Provides
    @Singleton
    fun provideHBOnCache(cache: HistoryBooksOnCacheImpl): HistoryBooksOnCache {
        return cache
    }

    @Provides
    @Singleton
    fun provideHBOnCloud(cloud: HistoryBooksOnCloudImpl): HistoryBooksOnCloud {
        return cloud
    }

    @Provides
    @Singleton
    fun provideLBOnCache(cache: LoanBooksOnCacheImpl): LoanBooksOnCache {
        return cache
    }

    @Provides
    @Singleton
    fun provideLBOnCloud(cloud: LoanBooksOnCloudImpl): LoanBooksOnCloud {
        return cloud
    }

    @Provides
    @Singleton
    fun provideHBRepository(repository: HistoryBooksRepositoryImp): HistoryBooksRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideLBRepository(repository: LoanBooksRepositoryImp): LoanBooksRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideSearchOnCloud(cloud: SearchBooksOnCloudImpl): SearchBooksOnCloud {
        return cloud
    }

    @Provides
    @Singleton
    fun provideSearchRepository(repository: SearchBookRepositoryImpl): SearchBookRepository {
        return repository
    }
}