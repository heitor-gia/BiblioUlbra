package com.hgianastasio.biblioulbrav2.views.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.databinding.FragmentDialogRenewLoansBinding
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModel
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter
import com.hgianastasio.biblioulbrav2.presenters.RenewLoansPresenter
import com.hgianastasio.biblioulbrav2.views.adapters.RenewLoanBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import org.koin.android.ext.android.inject

/**
 * Created by heitor_12 on 11/05/17.
 */
class RenewLoansDialogFragment : DialogFragment() {
    val renewPresenter: RenewLoansPresenter by inject()
    val booksListPresenter: LoanBooksListPresenter by inject()
    val binding  by viewBinding(FragmentDialogRenewLoansBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_renew_loans, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        renewPresenter!!.progressListener = (object : OnProgressListener {
            override fun showProgress() {
                binding.btnRenewLoans.isEnabled = false
                binding.progress.visibility = View.VISIBLE
            }

            override fun hideProgress() {
                binding.btnRenewLoans.isEnabled = true
                binding.progress.visibility = View.GONE
            }

            override fun showRetry() {}
            override fun hideRetry() {}
        })
        binding.rvBookList!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.btnRenewLoans!!.setOnClickListener {
            renewPresenter!!.renewLoans(
                    { model: RenewLoansResponseModel? -> processRenewResult(model) },
                    { e: Exception -> processError(e) }
            )
        }
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
        binding.rvBookList!!.adapter = RenewLoanBookAdapter(list, context)
        validateBooks(list)
        binding.btnRenewLoans!!.isClickable = true
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
        if (thereAreNoValidBooks) binding.btnRenewLoans!!.visibility = View.GONE
        if (thereAreJustRenewedBooks) binding.tvWarningMessage!!.visibility = View.VISIBLE
        if (thereAreOverdueBooks) binding.tvOverdueMessage!!.visibility = View.VISIBLE
        if (thereAreSingleCopies) binding.tvSingleCopyMessage!!.visibility = View.VISIBLE
    }

    private fun processError(e: Exception) {
        binding.renewFormLayout!!.visibility = View.GONE
        binding.errorLayout!!.visibility = View.VISIBLE
        binding.tvError!!.text = e.message
    }

    private fun setupDialog(dialog: Dialog?) {
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setTitle("Renovar empréstimos")
    }

}