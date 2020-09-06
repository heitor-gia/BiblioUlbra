package com.hgianastasio.biblioulbrav2.views.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.navigation.Navigator
import com.hgianastasio.biblioulbrav2.presenters.LoadCachePresenter
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseDrawerActivity
import com.hgianastasio.biblioulbrav2.views.fragments.HistoryBooksListFragment
import com.hgianastasio.biblioulbrav2.views.fragments.HomeFragment
import com.hgianastasio.biblioulbrav2.views.fragments.HomeFragment.OnCardClickListener
import com.hgianastasio.biblioulbrav2.views.fragments.LoanBooksListFragment
import com.hgianastasio.biblioulbrav2.views.fragments.SearchFragment
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class MainActivity : BaseDrawerActivity(), OnCardClickListener {
    @kotlin.jvm.JvmField
    @Inject
    var userModelPresenter: UserModelPresenter? = null

    @kotlin.jvm.JvmField
    @Inject
    var loadCachePresenter: LoadCachePresenter? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.mainProgress)
    var mainProgress: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        loadCachePresenter!!.progressListener = object : OnProgressListener {
            override fun showProgress() {
                mainProgress!!.visibility = View.VISIBLE
            }

            override fun hideProgress() {
                mainProgress!!.visibility = View.GONE
            }

            override fun showRetry() {}
            override fun hideRetry() {}
        }

        loadCachePresenter!!.loadCache()
        callFragment(HomeFragment.Companion.createInstance(this))
    }

    override fun onStart() {
        super.onStart()
        userModelPresenter!!.getUser(
                { model: UserModel? -> model?.let(::configureDrawer) },
                {
                    Toast.makeText(this, "Sessão inválida", Toast.LENGTH_SHORT).show()
                    userModelPresenter!!.logout(
                            {
                                Navigator.toLoginActivity(this)
                                finish()
                            },
                            { error: Exception -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                    )
                }
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHistory -> callFragment(HistoryBooksListFragment())
            R.id.navLoans -> callFragment(LoanBooksListFragment())
            R.id.navHome -> callFragment(HomeFragment.Companion.createInstance(this))
            R.id.navAdvancedSearch -> callFragment(SearchFragment())
            R.id.navLogout -> logout()
        }
        drawer!!.closeDrawers()
        return false
    }

    private fun logout() {
        userModelPresenter!!.logout(
                {
                    Navigator.toLoginActivity(this)
                    finish()
                },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onBackPressed() {
        if (currentFragment !is HomeFragment) callFragment(HomeFragment.Companion.createInstance(this)) else super.onBackPressed()
    }

    override fun onCardClick(id: Int) {
        onNavigationItemSelected(navigationDrawer!!.menu.findItem(id))
    }
}