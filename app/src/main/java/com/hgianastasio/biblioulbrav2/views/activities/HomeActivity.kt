package com.hgianastasio.biblioulbrav2.views.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import butterknife.BindView
import butterknife.OnClick
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.navigation.Navigator
import com.hgianastasio.biblioulbrav2.presenters.LoadCachePresenter
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseDrawerActivity
import com.hgianastasio.biblioulbrav2.views.fragments.RenewLoansDialogFragment
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import javax.inject.Inject

/**
 * Created by heitor_12 on 11/05/17.
 */
class HomeActivity : BaseDrawerActivity(), OnRefreshListener, OnProgressListener {
    @kotlin.jvm.JvmField
    @BindView(R.id.tvName)
    var tvName: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvPenalty)
    var tvPenalty: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvBookings)
    var tvBookings: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvHistory)
    var tvHistory: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvLoans)
    var tvLoans: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.btnRenewLoans)
    var btnRenewLoans: FloatingActionButton? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.mainContainer)
    var refreshLayout: SwipeRefreshLayout? = null

    @kotlin.jvm.JvmField
    @Inject
    var loadCachePresenter: LoadCachePresenter? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.mainProgress)
    var mainProgress: ProgressBar? = null

    @kotlin.jvm.JvmField
    @Inject
    var presenter: UserModelPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        presenter!!.getUser(
                { userModel: UserModel? -> renderUserModel(userModel) },
                { obj: Exception -> obj.printStackTrace() }
        )
    }

    override fun preBind() {
        super.preBind()
        activityComponent.inject(this)
        setContentView(R.layout.main_drawer_layout)
    }

    override fun postBind() {
        super.postBind()
        toolbar?.setTitle(R.string.app_name)
        refreshLayout!!.setOnRefreshListener(this)
        btnRenewLoans!!.setOnClickListener { v: View? ->
            val fragment = RenewLoansDialogFragment()
            fragment.show(supportFragmentManager, "oi")
        }
    }

    private fun renderUserModel(userModel: UserModel?) {
        tvName?.text = userModel?.nameLastName
        tvPenalty!!.text = String.format("Dívida:\nR$%s", userModel?.debt)
        tvPenalty!!.setTextColor(ContextCompat.getColor(this, if (userModel!!.isOverdue) R.color.redPenalty else R.color.greenPenalty))
        tvBookings!!.text = """
            Reservas:
            ${resources.getQuantityString(R.plurals.books, userModel.bookings, userModel.bookings)}
            """.trimIndent()
        tvHistory!!.text = """
            Histórico:
            ${resources.getQuantityString(R.plurals.books, userModel.history, userModel.history)}
            """.trimIndent()
        tvLoans!!.text = """
            Empréstimos:
            ${resources.getQuantityString(R.plurals.books, userModel.loans, userModel.loans)}
            """.trimIndent()
        if (userModel.loans == 0) {
            btnRenewLoans!!.isClickable = false
            btnRenewLoans!!.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.greyDisabled))
        }
    }

    override fun onRefresh() {
        refreshLayout!!.isRefreshing = false
    }

    override fun onDestroy() {
        presenter!!.unsetProgressListener()
        super.onDestroy()
    }

    @OnClick(R.id.loansCard, R.id.historyCard)
    fun cardClick(view: View) {
        when (view.id) {
            R.id.loansCard -> startActivity(Intent(this, LoanBooksListActivity::class.java))
            R.id.historyCard ->  startActivity(Intent(this, HistoryBooksListActivity::class.java))
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHistory ->  startActivity(Intent(this, HistoryBooksListActivity::class.java))
            R.id.navLoans -> startActivity(Intent(this, LoanBooksListActivity::class.java))
            R.id.navAdvancedSearch -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.navLogout -> logout()
        }
        drawer!!.closeDrawers()
        return false
    }

    private fun logout() {
        presenter!!.logout(
                {
                    Navigator.toLoginActivity(this)
                    finish()
                },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun showProgress() {
        refreshLayout!!.isRefreshing = true
    }

    override fun hideProgress() {
        refreshLayout!!.isRefreshing = false
    }

    override fun showRetry() {}
    override fun hideRetry() {}
}