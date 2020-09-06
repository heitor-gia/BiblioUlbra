package com.hgianastasio.biblioulbrav2.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import butterknife.BindView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter
import com.hgianastasio.biblioulbrav2.views.adapters.LoanBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class LoanBooksListFragment : BaseFragment(), OnRefreshListener, OnProgressListener {
    @kotlin.jvm.JvmField
    @BindView(R.id.progress)
    var progress: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.noBooksFound)
    var noBooksView: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.bookList)
    var rvLoanBooks: RecyclerView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.refreshLayout)
    var refreshLayout: SwipeRefreshLayout? = null
    var adapter: LoanBookAdapter? = null
    var bookModelList: MutableList<LoanBookModel?> = ArrayList()

    @kotlin.jvm.JvmField
    @Inject
    var presenter: LoanBooksListPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component?.inject(this)
    }

    override fun postBind() {
        presenter!!.progressListener = this
        refreshLayout!!.setOnRefreshListener(this)
        rvLoanBooks!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = LoanBookAdapter(bookModelList, context)
        adapter!!.setOnItemClickListener(object : LoanBookAdapter.OnItemClickListener {
            override fun onItemClick(loanBookModel: LoanBookModel?) = createLoanBookDialog(loanBookModel!!)
        })
        rvLoanBooks!!.adapter = adapter
        presenter!!.getLoanBooks(
                { list: List<LoanBookModel?> -> renderLoanBookList(list) },
                { e: Exception -> Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    protected override val layoutID: Int
        protected get() = R.layout.fragment_simple_books

    override val fragTag: String
        get() = "LoanBooks"

    override val title: String
        get() = "Empréstimos"

    override fun onRefresh() {
        refreshLayout!!.isRefreshing = false
        presenter!!.reloadLoanBooks(
                { list: List<LoanBookModel?> -> renderLoanBookList(list) },
                { e: Exception -> Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onDestroyView() {
        presenter!!.unsetProgressListener()
        presenter!!.destroy()
        super.onDestroyView()
    }

    private fun renderLoanBookList(list: List<LoanBookModel?>) {
        if (list.isEmpty()) {
            if (noBooksView != null) noBooksView!!.visibility = View.VISIBLE
        } else {
            if (noBooksView != null) noBooksView!!.visibility = View.GONE
            bookModelList.clear()
            bookModelList.addAll(list)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun createLoanBookDialog(model: LoanBookModel) {
        AlertDialog.Builder(context!!)
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
        progress!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress!!.visibility = View.GONE
    }

    override fun showRetry() {}
    override fun hideRetry() {}
}