package com.hgianastasio.biblioulbrav2.views.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import butterknife.BindView
import butterknife.OnClick
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import javax.inject.Inject

/**
 * Created by heitor_12 on 11/05/17.
 */
class HomeFragment : BaseFragment(), OnRefreshListener, OnProgressListener {
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
    @BindView(R.id.refreshLayout)
    var refreshLayout: SwipeRefreshLayout? = null
    var onCardClickListener: OnCardClickListener? = null

    @kotlin.jvm.JvmField
    @Inject
    var presenter: UserModelPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component?.inject(this)
        presenter!!.getUser(
                { userModel: UserModel? -> renderUserModel(userModel) },
                { obj: Exception -> obj.printStackTrace() }
        )
    }

    override fun postBind() {
        super.postBind()
        refreshLayout!!.setOnRefreshListener(this)
        btnRenewLoans!!.setOnClickListener { v: View? ->
            val fragment = RenewLoansDialogFragment()
            fragment.show(childFragmentManager, "oi")
        }
    }

    protected override val layoutID: Int
        protected get() = R.layout.fragment_home

    private fun renderUserModel(userModel: UserModel?) {
        tvName?.text = userModel?.nameLastName
        tvPenalty!!.text = String.format("Dívida:\nR$%s", userModel?.debt)
        tvPenalty!!.setTextColor(ContextCompat.getColor(context!!, if (userModel!!.isOverdue) R.color.redPenalty else R.color.greenPenalty))
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
            btnRenewLoans!!.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.greyDisabled))
        }
    }

    override val fragTag: String
        get() = "HomeFragment"

    override val title: String
        get() = "Home"

    override fun onRefresh() {
        refreshLayout!!.isRefreshing = false
    }

    override fun onDestroyView() {
        presenter!!.unsetProgressListener()
        onCardClickListener = null
        super.onDestroyView()
    }

    @OnClick(R.id.loansCard, R.id.historyCard)
    fun cardClick(view: View) {
        if (onCardClickListener != null) when (view.id) {
            R.id.loansCard -> onCardClickListener!!.onCardClick(R.id.navLoans)
            R.id.historyCard -> onCardClickListener!!.onCardClick(R.id.navHistory)
        }
    }


    override fun showProgress() {
        refreshLayout!!.isRefreshing = true
    }

    override fun hideProgress() {
        refreshLayout!!.isRefreshing = false
    }

    override fun showRetry() {}
    override fun hideRetry() {}
    interface OnCardClickListener {
        fun onCardClick(id: Int)
    }

    companion object {
        fun createInstance(listener: OnCardClickListener?): HomeFragment {
            val fragment = HomeFragment()
            fragment.onCardClickListener = (listener)
            return fragment
        }
    }
}