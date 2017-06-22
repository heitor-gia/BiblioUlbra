package com.hgianastasio.biblioulbrav2.di.components;

import android.content.Context;

import com.hgianastasio.biblioulbrav2.BiblioUlbraApplication;
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.GetHistoryBooks;
import com.hgianastasio.biblioulbrav2.core.historybooks.interactors.ReloadHitoryBooks;
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.GetLoanBooks;
import com.hgianastasio.biblioulbrav2.core.loanbooks.interactors.ReloadLoanBooks;
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooks;
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksNextPage;
import com.hgianastasio.biblioulbrav2.core.search.interactors.SearchBooksPrevPage;
import com.hgianastasio.biblioulbrav2.core.settings.interactors.GetAllSettings;
import com.hgianastasio.biblioulbrav2.core.settings.interactors.GetSetting;
import com.hgianastasio.biblioulbrav2.core.settings.interactors.SetSetting;
import com.hgianastasio.biblioulbrav2.core.settings.interactors.SetSettingToDefault;
import com.hgianastasio.biblioulbrav2.core.user.interactors.GetUser;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoginUser;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LogoutUser;
import com.hgianastasio.biblioulbrav2.core.user.interactors.RenewLoans;
import com.hgianastasio.biblioulbrav2.di.modules.ApplicationModule;
import com.hgianastasio.biblioulbrav2.di.modules.DataModule;
import com.hgianastasio.biblioulbrav2.di.modules.RetrofitModule;
import com.hgianastasio.biblioulbrav2.system.SettingsManager;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by heitorgianastasio on 4/29/17.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                RetrofitModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {

    void inject(SettingsManager manager);

    //Application Context
    Context context();

    //UseCases
    LoginUser loginUser();
    LogoutUser logoutUser();
    GetUser getUser();
    RenewLoans renewLoans();
    LoadCache loadCache();
    GetHistoryBooks getHistoryBooks();
    ReloadHitoryBooks reloadHistoryBooks();
    GetLoanBooks getLoanBooks();
    ReloadLoanBooks reloadLoanBooks();
    SearchBooks searchBooks();
    SearchBooksNextPage searchBooksNP();
    SearchBooksPrevPage searchBooksPP();
    GetSetting getSetting();
    SetSetting setSetting();
    SetSettingToDefault setSettingToDefault();
    GetAllSettings getAllSettings();

}
