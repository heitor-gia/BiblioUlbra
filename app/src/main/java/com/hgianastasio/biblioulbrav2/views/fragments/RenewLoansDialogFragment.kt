package com.hgianastasio.biblioulbrav2.views.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.di.components.ActivityComponent
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModel
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter
import com.hgianastasio.biblioulbrav2.presenters.RenewLoansPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.adapters.RenewLoanBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import javax.inject.Inject

/**
 * Created by heitor_12 on 11/05/17.
 */
class RenewLoansDialogFragment : DialogFragment() {
    @kotlin.jvm.JvmField
    @Inject
    var renewPresenter: RenewLoansPresenter? = null

    @kotlin.jvm.JvmField
    @Inject
    var booksListPresenter: LoanBooksListPresenter? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.rvBookList)
    var rvBookList: RecyclerView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.errorLayout)
    var errorLayout: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvError)
    var tvError: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.btnRenewLoans)
    var btnRenewLoans: Button? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvWarningMessage)
    var tvWarningMessage: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvOverdueMessage)
    var tvOverdueMessage: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvSingleCopyMessage)
    var tvSingleCopyMessage: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.renewFormLayout)
    var renewFormLayout: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.progress)
    var progress: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_renew_loans, container, false)
        ButterKnife.bind(this, view)
        renewPresenter!!.progressListener = (object : OnProgressListener {
            override fun showProgress() {
                btnRenewLoans!!.isEnabled = false
                progress!!.visibility = View.VISIBLE
            }

            override fun hideProgress() {
                btnRenewLoans!!.isEnabled = true
                progress!!.visibility = View.GONE
            }

            override fun showRetry() {}
            override fun hideRetry() {}
        })
        rvBookList!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        btnRenewLoans!!.setOnClickListener {
            renewPresenter!!.renewLoans(
                    { model: RenewLoansResponseModel? -> processRenewResult(model) },
                    { e: Exception -> processError(e) }
                    )
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        setupDialog(dialog)
        booksListPresenter!!.getLoanBooks(
                { list: List<LoanBookModel?> -> processLoanBooksResult(list) },
                { e: Exception -> processError(e) }
        )
    }

    private fun processRenewResult(model: RenewLoansResponseModel?) {
        AlertDialog.Builder(context!!)
                .setTitle("Pronto!")
                .setMessage("Seus empréstimos foram renovados")
                .setNeutralButton("OK") { dialog: DialogInterface?, which: Int -> dismiss() }
                .show()
    }

    private fun processLoanBooksResult(list: List<LoanBookModel?>) {
        rvBookList!!.adapter = RenewLoanBookAdapter(list, context)
        validateBooks(list)
        btnRenewLoans!!.isClickable = true
    }

    private fun validateBooks(list: List<LoanBookModel?>) {
        var thereAreNoValidBooks = true
        var thereAreJustRenewedBooks = false
        var thereAreOverdueBooks = false
        var thereAreSingleCopies = false
        for (model in list) {
            thereAreNoValidBooks = thereAreNoValidBooks and (model!!.isOverdue || model.wasJustRenewed())
            thereAreJustRenewedBooks = thereAreJustRenewedBooks or model.wasJustRenewed()
            thereAreOverdueBooks = thereAreOverdueBooks or model.isOverdue
            thereAreSingleCopies = thereAreSingleCopies or model.isSingleCopy
        }
        if (thereAreNoValidBooks) btnRenewLoans!!.visibility = View.GONE
        if (thereAreJustRenewedBooks) tvWarningMessage!!.visibility = View.VISIBLE
        if (thereAreOverdueBooks) tvOverdueMessage!!.visibility = View.VISIBLE
        if (thereAreSingleCopies) tvSingleCopyMessage!!.visibility = View.VISIBLE
    }

    private fun processError(e: Exception) {
        renewFormLayout!!.visibility = View.GONE
        errorLayout!!.visibility = View.VISIBLE
        tvError!!.text = e.message
    }

    private fun setupDialog(dialog: Dialog?) {
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setTitle("Renovar empréstimos")
    }

    val component: ActivityComponent?
        get() = (activity as? BaseActivity)?.activityComponent
}