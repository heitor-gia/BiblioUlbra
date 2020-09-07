package com.hgianastasio.biblioulbrav2.views.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.hgianastasio.biblioulbrav2.databinding.ActivitySimpleBooksBinding
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel
import com.hgianastasio.biblioulbrav2.presenters.HistoryBooksListPresenter
import com.hgianastasio.biblioulbrav2.views.adapters.HistoryBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by heitor_12 on 09/05/17.
 */
class HistoryBooksListActivity : AppCompatActivity(), OnRefreshListener, OnProgressListener {
    val binding by viewBinding(ActivitySimpleBooksBinding::inflate)
    val presenter: HistoryBooksListPresenter by inject()
    var adapter: HistoryBookAdapter? = null
    var bookModelList: MutableList<HistoryBookModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.progressListener = (this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Histórico"
        binding.refreshLayout.setOnRefreshListener(this)
        binding.bookList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = HistoryBookAdapter(bookModelList, this)
        binding.bookList.adapter = adapter
        presenter.getHistoryBooks(
                { list -> renderHistoryBookList(list) },
                { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onRefresh() {
        binding.refreshLayout.isRefreshing = false
        presenter.reloadHistoryBooks(
                { list -> renderHistoryBookList(list) },
                { e -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
        )
    }

    override fun onDestroy() {
        presenter.unsetProgressListener()
        presenter.destroy()
        super.onDestroy()
    }

    private fun renderHistoryBookList(list: List<HistoryBookModel>) {
        if (list.isEmpty()) {
            binding.noBooksFound.visibility = View.VISIBLE
        } else {
            binding.noBooksFound.visibility = View.GONE
            bookModelList.clear()
            bookModelList.addAll(list)
            adapter!!.notifyDataSetChanged()
        }
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