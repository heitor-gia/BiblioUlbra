package com.hgianastasio.biblioulbrav2.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import butterknife.BindView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel
import com.hgianastasio.biblioulbrav2.presenters.HistoryBooksListPresenter
import com.hgianastasio.biblioulbrav2.views.adapters.HistoryBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class HistoryBooksListFragment : BaseFragment(), OnRefreshListener, OnProgressListener {
    @kotlin.jvm.JvmField
    @BindView(R.id.progress)
    var progress: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.noBooksFound)
    var noBooksView: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.bookList)
    var rvHistoryBooks: RecyclerView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.refreshLayout)
    var refreshLayout: SwipeRefreshLayout? = null
    var adapter: HistoryBookAdapter? = null
    var bookModelList: MutableList<HistoryBookModel?> = ArrayList()

    @kotlin.jvm.JvmField
    @Inject
    var presenter: HistoryBooksListPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component?.inject(this)
    }

    override fun postBind() {
        presenter!!.progressListener = (this)
        refreshLayout!!.setOnRefreshListener(this)
        rvHistoryBooks!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = HistoryBookAdapter(bookModelList, context)
        rvHistoryBooks!!.adapter = adapter
        presenter!!.getHistoryBooks(
                { list: List<HistoryBookModel?> -> renderHistoryBookList(list) },
                { e: Exception -> Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    protected override val layoutID: Int
        protected get() = R.layout.fragment_simple_books

    override val fragTag: String
        get() = "HistoryBooks"

    override val title: String
        get() = "Histórico"

    override fun onRefresh() {
        refreshLayout!!.isRefreshing = false
        presenter!!.reloadHistoryBooks(
                { list: List<HistoryBookModel?> -> renderHistoryBookList(list) },
                { e: Exception -> Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onDestroyView() {
        presenter!!.unsetProgressListener()
        presenter!!.destroy()
        super.onDestroyView()
    }

    private fun renderHistoryBookList(list: List<HistoryBookModel?>) {
        if (list.isEmpty()) {
            if (noBooksView != null) noBooksView!!.visibility = View.VISIBLE
        } else {
            if (noBooksView != null) noBooksView!!.visibility = View.GONE
            bookModelList.clear()
            bookModelList.addAll(list)
            adapter!!.notifyDataSetChanged()
        }
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