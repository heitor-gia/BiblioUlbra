package com.hgianastasio.biblioulbrav2.views.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.navigation.NavigationView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.databinding.MainDrawerLayoutBinding
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.navigation.Navigator
import com.hgianastasio.biblioulbrav2.presenters.LoadCachePresenter
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter
import com.hgianastasio.biblioulbrav2.views.fragments.RenewLoansDialogFragment
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import org.koin.android.ext.android.inject

/**
 * Created by heitor_12 on 11/05/17.
 */
class HomeActivity : AppCompatActivity(), OnRefreshListener, OnProgressListener, NavigationView.OnNavigationItemSelectedListener {
    val binding by viewBinding(MainDrawerLayoutBinding::inflate)
    val loadCachePresenter: LoadCachePresenter by inject()
    val presenter: UserModelPresenter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.mainContainer.historyCard.setOnClickListener(::cardClick)
        binding.mainContainer.loansCard.setOnClickListener(::cardClick)
        binding.mainContainer.refreshLayout.setOnRefreshListener(this)
        binding.mainContainer.btnRenewLoans.setOnClickListener { v: View? ->
            val fragment = RenewLoansDialogFragment()
            fragment.show(supportFragmentManager, "oi")
        }
        loadCachePresenter!!.progressListener = object : OnProgressListener {
            override fun showProgress() {
                binding.mainProgress.visibility = View.VISIBLE
            }

            override fun hideProgress() {
                binding.mainProgress.visibility = View.GONE
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


    private fun renderUserModel(userModel: UserModel?) = binding.mainContainer.run {
        configureDrawer(userModel)
        tvName.text = userModel?.nameLastName
        tvPenalty.text = String.format("Dívida:\nR$%s", userModel?.debt)
        tvPenalty.setTextColor(ContextCompat.getColor(root.context, if (userModel!!.isOverdue) R.color.redPenalty else R.color.greenPenalty))
        tvBookings.text = """
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
            btnRenewLoans.isClickable = false
            btnRenewLoans.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.greyDisabled))
        }
    }

    override fun onRefresh() {
        binding.mainContainer.refreshLayout.isRefreshing = false
    }

    override fun onDestroy() {
        presenter!!.unsetProgressListener()
        super.onDestroy()
    }


    fun cardClick(view: View) {
        when (view.id) {
            R.id.loansCard -> startActivity(Intent(this, LoanBooksListActivity::class.java))
            R.id.historyCard ->  startActivity(Intent(this, HistoryBooksListActivity::class.java))
        }
    }

    private fun configureDrawer(model: UserModel?) {
        ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0)
                .also { binding.drawerLayout.addDrawerListener(it) }
                .also { it.syncState() }
        binding.navView.run {
            setNavigationItemSelectedListener(this@HomeActivity)
            getHeaderView(0).run {
                findViewById<TextView>(R.id.tvName).text = model?.name
                findViewById<TextView>(R.id.tvCGU).text = model?.cgu
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHistory ->  startActivity(Intent(this, HistoryBooksListActivity::class.java))
            R.id.navLoans -> startActivity(Intent(this, LoanBooksListActivity::class.java))
            R.id.navAdvancedSearch -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.navLogout -> logout()
        }
        binding.drawerLayout.closeDrawers()
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
        binding.mainContainer.refreshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        binding.mainContainer.refreshLayout.isRefreshing = false
    }

    override fun showRetry() {}
    override fun hideRetry() {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.default_menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miAbout) createAboutDialog().show()
        return super.onOptionsItemSelected(item)
    }

    private fun createAboutDialog(): AlertDialog {
        val message = LayoutInflater.from(this).inflate(R.layout.dialog_about_layout, null) as TextView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) message.text = Html.fromHtml(getString(R.string.about_message, getString(R.string.app_version)), 0) else message.text = Html.fromHtml(getString(R.string.about_message, getString(R.string.app_version)))
        message.movementMethod = LinkMovementMethod.getInstance()
        return AlertDialog.Builder(this)
                .setTitle("Sobre")
                .setView(message)
                .setNeutralButton("OK", null)
                .create()
    }
}