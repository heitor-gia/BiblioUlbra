package com.hgianastasio.biblioulbrav2.views.activities

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
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.adapters.HistoryBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class HistoryBooksListActivity : BaseActivity(), OnRefreshListener, OnProgressListener {
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

    override fun preBind() {
        super.preBind()
        activityComponent.inject(this)
        setContentView(R.layout.activity_simple_books)
    }

    override fun postBind() {
        super.postBind()
        presenter!!.progressListener = (this)
        supportActionBar?.title = "Histórico"
        refreshLayout!!.setOnRefreshListener(this)
        rvHistoryBooks!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = HistoryBookAdapter(bookModelList, this)
        rvHistoryBooks!!.adapter = adapter
        presenter!!.getHistoryBooks(
                { list: List<HistoryBookModel?> -> renderHistoryBookList(list) },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onRefresh() {
        refreshLayout!!.isRefreshing = false
        presenter!!.reloadHistoryBooks(
                { list: List<HistoryBookModel?> -> renderHistoryBookList(list) },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onDestroy() {
        presenter!!.unsetProgressListener()
        presenter!!.destroy()
        super.onDestroy()
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