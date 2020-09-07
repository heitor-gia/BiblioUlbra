package com.hgianastasio.biblioulbrav2.views.activities

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.hgianastasio.biblioulbrav2.databinding.ActivitySimpleBooksBinding
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel
import com.hgianastasio.biblioulbrav2.presenters.HistoryBooksListPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.adapters.HistoryBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class HistoryBooksListActivity : BaseActivity(), OnRefreshListener, OnProgressListener {
    val binding by viewBinding(ActivitySimpleBooksBinding::inflate)
    var adapter: HistoryBookAdapter? = null
    var bookModelList: MutableList<HistoryBookModel?> = ArrayList()

    @JvmField
    @Inject
    var presenter: HistoryBooksListPresenter? = null

    override fun preBind() {
        super.preBind()
        activityComponent.inject(this)
        setContentView(binding.root)
    }

    override fun postBind() {
        super.postBind()
        presenter!!.progressListener = (this)
        supportActionBar?.title = "Histórico"
        binding.refreshLayout.setOnRefreshListener(this)
        binding.bookList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = HistoryBookAdapter(bookModelList, this)
        binding.bookList.adapter = adapter
        presenter!!.getHistoryBooks(
                { list: List<HistoryBookModel?> -> renderHistoryBookList(list) },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onRefresh() {
        binding.refreshLayout.isRefreshing = false
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
            binding.noBooksFound.visibility = View.VISIBLE
        } else {
            binding.noBooksFound.visibility = View.GONE
            bookModelList.clear()
            bookModelList.addAll(list)
            adapter!!.notifyDataSetChanged()
        }
    }

    override val toolbar: Toolbar?
        get() = binding.toolbar

    override fun showProgress() {
        binding.progress.root.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.root.visibility = View.GONE
    }

    override fun showRetry() {}
    override fun hideRetry() {}
}