package com.hgianastasio.biblioulbrav2.di.modules;

import com.hgianastasio.biblioulbrav2.core.historybooks.repository.HistoryBooksRepository;
import com.hgianastasio.biblioulbrav2.core.loanbooks.repository.LoanBooksRepository;
import com.hgianastasio.biblioulbrav2.core.search.repository.SearchBookRepository;
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.base.search.SearchBooksOnCloud;
import com.hgianastasio.biblioulbrav2.data.base.setting.SettingOnDisk;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCloud;
import com.hgianastasio.biblioulbrav2.data.disk.HistoryBooksOnCacheImpl;
import com.hgianastasio.biblioulbrav2.data.disk.LoanBooksOnCacheImpl;
import com.hgianastasio.biblioulbrav2.data.disk.SettingOnDiskImpl;
import com.hgianastasio.biblioulbrav2.data.disk.UserOnCacheImpl;
import com.hgianastasio.biblioulbrav2.data.network.cloud.HistoryBooksOnCloudImpl;
import com.hgianastasio.biblioulbrav2.data.network.cloud.LoanBooksOnCloudImpl;
import com.hgianastasio.biblioulbrav2.data.network.cloud.SearchBooksOnCloudImpl;
import com.hgianastasio.biblioulbrav2.data.network.cloud.UserOnCloudImpl;
import com.hgianastasio.biblioulbrav2.data.repositories.HistoryBooksRepositoryImp;
import com.hgianastasio.biblioulbrav2.data.repositories.LoanBooksRepositoryImp;
import com.hgianastasio.biblioulbrav2.data.repositories.SearchBookRepositoryImpl;
import com.hgianastasio.biblioulbrav2.data.repositories.SettingRepositoryImpl;
import com.hgianastasio.biblioulbrav2.data.repositories.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

@Module
public class DataModule {
    @Provides
    @Singleton
    UserOnCloud provideUserOnCloud(UserOnCloudImpl cloud){return cloud;}

    @Provides
    @Singleton
    UserOnCache provideUserOnCache(UserOnCacheImpl cache){return  cache;}

    @Provides
    @Singleton
    HistoryBooksOnCache provideHBOnCache(HistoryBooksOnCacheImpl cache){return cache;}

    @Provides
    @Singleton
    HistoryBooksOnCloud provideHBOnCloud(HistoryBooksOnCloudImpl cloud){return cloud;}

    @Provides
    @Singleton
    LoanBooksOnCache provideLBOnCache(LoanBooksOnCacheImpl cache){return cache;}

    @Provides
    @Singleton
    LoanBooksOnCloud provideLBOnCloud(LoanBooksOnCloudImpl cloud){return cloud;}

    @Provides
    @Singleton
    HistoryBooksRepository provideHBRepository(HistoryBooksRepositoryImp repository){return repository;}


    @Provides
    @Singleton
    LoanBooksRepository provideLBRepository(LoanBooksRepositoryImp repository){return repository;}

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserRepositoryImpl repository){return repository;}

    @Provides
    @Singleton
    SearchBooksOnCloud provideSearchOnCloud(SearchBooksOnCloudImpl cloud){return cloud;}

    @Provides
    @Singleton
    SearchBookRepository provideSearchRepository(SearchBookRepositoryImpl repository){return repository;}

    @Provides
    @Singleton
    SettingRepository provideSettingRepository(SettingRepositoryImpl repository){return repository;}

    @Provides
    @Singleton
    SettingOnDisk provideSettingOnDisk(SettingOnDiskImpl repository){return repository;}
}
