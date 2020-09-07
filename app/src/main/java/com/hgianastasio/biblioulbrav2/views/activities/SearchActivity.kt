package com.hgianastasio.biblioulbrav2.views.activities

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia
import com.hgianastasio.biblioulbrav2.databinding.ActivitySearchBooksBinding
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModel
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModel
import com.hgianastasio.biblioulbrav2.presenters.SearchPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.adapters.SearchBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.EndlessRecyclerViewScrollListener
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 31/05/17.
 */
class SearchActivity : BaseActivity(), OnProgressListener {
    @JvmField
    @Inject
    var presenter: SearchPresenter? = null
    val binding by viewBinding(ActivitySearchBooksBinding::inflate)
    var currentResult: SearchResultModel? = null
    var adapter: SearchBookAdapter? = null
    var modelList: MutableList<SearchBookModel?> = ArrayList(10)

    override fun preBind() {
        super.preBind()
        activityComponent.inject(this)
        setContentView(binding.root)
    }

    override fun postBind() {
        super.postBind()
        supportActionBar?.title = "Pesquisa"
        presenter!!.progressListener = this
        initRecyclerView()
        setupSpinners()
        binding.btnToggleAdvancedSearch.setOnClickListener { toggleAdvancedSearchLayout() }
        binding.btnToggleSearch.setOnClickListener {  toggleSearchLayout() }
        binding.btnSearch.setOnClickListener {  doSearch() }
    }

    override val toolbar: Toolbar?
        get() = binding.toolbar

    private fun initRecyclerView() {
        adapter = SearchBookAdapter(modelList, this)
        adapter!!.setOnItemClickListener (object : SearchBookAdapter.OnItemClickListener{
            override fun onItemClick(searchBookModel: SearchBookModel?) =  createSearchBookDialog(searchBookModel!!)

        })
        binding.bookList.adapter = adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.bookList.layoutManager = layoutManager
        binding.bookList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (Math.abs(dy) > 30) {
                    hideKeyboard()
                    binding.searchLayout.visibility = View.GONE
                    binding.btnToggleSearch.show()
                }
            }
        })
        binding.bookList.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (currentResult!!.currentPage < currentResult!!.pages)
                    presenter?.nextPage(currentResult!!,
                            { result: SearchResultModel? -> processResults(result) },
                            { e: Exception -> processError(e) }
                    )
            }
        })
    }

    private fun doSearch() {
        if (!binding.etSearch.text.toString().trim { it <= ' ' }.isEmpty()) {
            hideKeyboard()
            presenter!!.search(buildSearchModel(),
                    { result: SearchResultModel? -> processResults(result) },
                    { e: Exception -> processError(e) }
            )
            binding.searchLayout.visibility = View.GONE
            binding.btnToggleSearch.show()
        } else {
            Toast.makeText(this, "Preencha o campo de busca", Toast.LENGTH_SHORT).show()
        }
    }

    private fun processResults(result: SearchResultModel?) {
        currentResult = result
        if (result?.currentPage ?: 0 <= 1) {
            modelList.clear()
            adapter!!.notifyDataSetChanged()
            (binding.bookList.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(0, 0)
        }
        addBooksToRV(result?.result)
    }

    private fun processError(e: Exception) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter!!.unsetProgressListener()
        super.onDestroy()
    }

    private fun buildSearchModel(): SearchModelModel {
        val result = SearchModelModel()
        result.search = binding.etSearch.text.toString().trim { it <= ' ' }
        result.isExact = binding.vwAdvancedSearchLayout.cbExactSearch.isChecked
        result.field = binding.vwAdvancedSearchLayout.spField.selectedItemPosition
        result.language = binding.vwAdvancedSearchLayout.spLanguage.selectedItemPosition
        result.media = binding.vwAdvancedSearchLayout.spMedia.selectedItemPosition
        result.base = binding.vwAdvancedSearchLayout.spBase.selectedItemPosition
        return result
    }

    private fun setupSpinners() {
        binding.vwAdvancedSearchLayout.spLanguage.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchLanguage.names.toTypedArray())
        binding.vwAdvancedSearchLayout.spMedia.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchMedia.names.toTypedArray())
        binding.vwAdvancedSearchLayout.spBase.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchBase.titles.toTypedArray())
        binding.vwAdvancedSearchLayout.spField.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchField.names.toTypedArray())
    }

    private fun toggleSearchLayout() {
        if (binding.searchLayout.visibility != View.VISIBLE) {
            binding.searchLayout.visibility = View.VISIBLE
            binding.btnToggleSearch.hide()
        } else {
            binding.searchLayout.visibility = View.GONE
        }
    }

    private fun createSearchBookDialog(model: SearchBookModel) {
        AlertDialog.Builder(this)
                .setTitle("Exemplares")
                .setMessage(model.catchedExps?.joinToString(separator = "\n"))
                .setNeutralButton("Ok", null)
                .show()
    }

    private fun toggleAdvancedSearchLayout() {
        if (binding.vwAdvancedSearchLayout.root.visibility != View.VISIBLE) {
            binding.vwAdvancedSearchLayout.root.visibility = View.VISIBLE
        } else {
            binding.vwAdvancedSearchLayout.root.visibility = View.GONE
        }
    }

    private fun addBooksToRV(list: List<SearchBookModel?>?) {
        val prevListSize = modelList.size
        modelList.addAll(list!!)
        adapter!!.notifyItemRangeInserted(prevListSize, list.size)
    }

    override fun showProgress() {
        binding.progress.root.visibility = View.VISIBLE
        binding.btnToggleSearch.visibility = View.GONE
    }

    override fun hideProgress() {
        binding.progress.root.visibility = View.GONE
        binding.btnToggleSearch.visibility = View.VISIBLE
    }

    override fun showRetry() {}
    override fun hideRetry() {}
    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(window.decorView.applicationWindowToken, 0)
        } catch (e: Exception) {
            Log.e("Error", Log.getStackTraceString(e))
        }
    }
}