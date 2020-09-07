package com.hgianastasio.biblioulbrav2.di.components

import com.hgianastasio.biblioulbrav2.di.PerActivity
import com.hgianastasio.biblioulbrav2.di.modules.ActivityModule
import com.hgianastasio.biblioulbrav2.views.activities.HistoryBooksListActivity
import com.hgianastasio.biblioulbrav2.views.activities.HomeActivity
import com.hgianastasio.biblioulbrav2.views.activities.LoanBooksListActivity
import com.hgianastasio.biblioulbrav2.views.activities.LoginActivity
import com.hgianastasio.biblioulbrav2.views.activities.SearchActivity
import com.hgianastasio.biblioulbrav2.views.fragments.RenewLoansDialogFragment
import dagger.Component

/**
 * Created by heitor_12 on 04/05/17.
 */
@PerActivity
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {
    fun inject(activity: LoginActivity?)
    fun inject(activity: HistoryBooksListActivity?)
    fun inject(activity: LoanBooksListActivity?)
    fun inject(activity: HomeActivity?)
    fun inject(fragment: RenewLoansDialogFragment?)
    fun inject(activity: SearchActivity?)
}