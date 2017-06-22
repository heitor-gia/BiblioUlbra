package com.hgianastasio.biblioulbrav2.di.components;

import com.hgianastasio.biblioulbrav2.di.PerActivity;
import com.hgianastasio.biblioulbrav2.di.modules.ActivityModule;
import com.hgianastasio.biblioulbrav2.views.activities.LoginActivity;
import com.hgianastasio.biblioulbrav2.views.activities.MainActivity;
import com.hgianastasio.biblioulbrav2.views.fragments.HistoryBooksListFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.HomeFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.LoanBooksListFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.RenewLoansDialogFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.SearchFragment;

import dagger.Component;

/**
 * Created by heitor_12 on 04/05/17.
 */
@PerActivity
@Component(
        modules = ActivityModule.class,
        dependencies = ApplicationComponent.class
)
public interface ActivityComponent{
    void inject(LoginActivity activity);
    void inject(MainActivity activity);

    void inject(HistoryBooksListFragment fragment);
    void inject(LoanBooksListFragment fragment);
    void inject(HomeFragment fragment);
    void inject(RenewLoansDialogFragment fragment);
    void inject(SearchFragment fragment);



}
