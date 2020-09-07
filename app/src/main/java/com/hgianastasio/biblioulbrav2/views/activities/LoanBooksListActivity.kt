package com.hgianastasio.biblioulbrav2.views.activities

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.hgianastasio.biblioulbrav2.databinding.ActivitySimpleBooksBinding
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.adapters.LoanBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class LoanBooksListActivity : BaseActivity(), OnRefreshListener, OnProgressListener {
    val binding by viewBinding (ActivitySimpleBooksBinding::inflate)
    var adapter: LoanBookAdapter? = null
    var bookModelList: MutableList<LoanBookModel?> = ArrayList()

    @kotlin.jvm.JvmField
    @Inject
    var presenter: LoanBooksListPresenter? = null


    override fun preBind() {
        super.preBind()
        activityComponent.inject(this)
        setContentView(binding.root)
    }

    override fun postBind() {
        super.postBind()
        presenter!!.progressListener = this
        supportActionBar?.title = "Livros Alugados"
        binding.refreshLayout.setOnRefreshListener(this)
        binding.bookList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = LoanBookAdapter(bookModelList, this)
        adapter!!.setOnItemClickListener(object : LoanBookAdapter.OnItemClickListener {
            override fun onItemClick(loanBookModel: LoanBookModel?) = createLoanBookDialog(loanBookModel!!)
        })
        binding.bookList.adapter = adapter
        presenter!!.getLoanBooks(
                { list: List<LoanBookModel?> -> renderLoanBookList(list) },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override val toolbar: Toolbar?
        get() = binding.toolbar


    override fun onRefresh() {
        binding.refreshLayout.isRefreshing = false
        presenter!!.reloadLoanBooks(
                { list: List<LoanBookModel?> -> renderLoanBookList(list) },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onDestroy() {
        presenter!!.unsetProgressListener()
        presenter!!.destroy()
        super.onDestroy()
    }

    private fun renderLoanBookList(list: List<LoanBookModel?>) {
        if (list.isEmpty()) {
            binding.noBooksFound.visibility = View.VISIBLE
        } else {
            binding.noBooksFound.visibility = View.GONE
            bookModelList.clear()
            bookModelList.addAll(list)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun createLoanBookDialog(model: LoanBookModel) {
        AlertDialog.Builder(this)
                .setTitle(model.title)
                .setMessage(String.format(
                        """
                            Biblioteca: %s
                            Prazo: %s
                            Multa: R$%s
                            Código: %s
                            Descrição: %s
                            """.trimIndent(),
                        model.library,
                        model.deadline,
                        model.penalty,
                        model.code,
                        model.description
                ))
                .setNeutralButton("Ok", null)
                .show()
    }

    override fun showProgress() {
        binding.progress.root.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.root.visibility = View.GONE
    }

    override fun showRetry() {}
    override fun hideRetry() {}
}