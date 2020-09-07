package com.hgianastasio.biblioulbrav2.views.activities

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModel
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModel
import com.hgianastasio.biblioulbrav2.presenters.SearchPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.adapters.SearchBookAdapter
import com.hgianastasio.biblioulbrav2.views.listeners.EndlessRecyclerViewScrollListener
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 31/05/17.
 */
class SearchActivity : BaseActivity(), OnProgressListener {
    @kotlin.jvm.JvmField
    @Inject
    var presenter: SearchPresenter? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.progress)
    var progress: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.cbExactSearch)
    var cbExactSearch: CheckBox? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.spLanguage)
    var spLanguage: Spinner? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.spMedia)
    var spMedia: Spinner? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.spField)
    var spField: Spinner? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.spBase)
    var spBase: Spinner? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.etSearch)
    var etSearch: EditText? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.btnSearch)
    var btnSearch: Button? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.vwAdvancedSearchLayout)
    var advancedSearchLayout: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.searchLayout)
    var searchLayout: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.btnToggleAdvancedSearch)
    var btnToggleAdvancedSearch: View? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.bookList)
    var bookList: RecyclerView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.btnToggleSearch)
    var btnToggleSearch: FloatingActionButton? = null
    var currentResult: SearchResultModel? = null
    var adapter: SearchBookAdapter? = null
    var modelList: MutableList<SearchBookModel?> = ArrayList(10)

    override fun preBind() {
        super.preBind()
        activityComponent.inject(this)
        setContentView(R.layout.activity_search_books)
    }

    override fun postBind() {
        super.postBind()
        supportActionBar?.title = "Pesquisa"
        presenter!!.progressListener = this
        initRecyclerView()
        setupSpinners()
        btnToggleAdvancedSearch!!.setOnClickListener { v: View? -> toggleAdvancedSearchLayout() }
        btnToggleSearch!!.setOnClickListener { v: View? -> toggleSearchLayout() }
        btnSearch!!.setOnClickListener { v: View? -> doSearch() }
    }
    
    private fun initRecyclerView() {
        adapter = SearchBookAdapter(modelList, this)
        adapter!!.setOnItemClickListener (object : SearchBookAdapter.OnItemClickListener{
            override fun onItemClick(searchBookModel: SearchBookModel?) =  createSearchBookDialog(searchBookModel!!)

        })
        bookList!!.adapter = adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bookList!!.layoutManager = layoutManager
        bookList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (Math.abs(dy) > 30) {
                    hideKeyboard()
                    searchLayout!!.visibility = View.GONE
                    btnToggleSearch!!.show()
                }
            }
        })
        bookList!!.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (currentResult!!.currentPage < currentResult!!.pages)
                    presenter!!.nextPage(currentResult!!,
                            { result: SearchResultModel? -> processResults(result) },
                            { e: Exception -> processError(e) }
                    )
            }
        })
    }

    private fun doSearch() {
        if (!etSearch!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            hideKeyboard()
            presenter!!.search(buildSearchModel(),
                    { result: SearchResultModel? -> processResults(result) },
                    { e: Exception -> processError(e) }
            )
            searchLayout!!.visibility = View.GONE
            btnToggleSearch!!.show()
        } else {
            Toast.makeText(this, "Preencha o campo de busca", Toast.LENGTH_SHORT).show()
        }
    }

    private fun processResults(result: SearchResultModel?) {
        currentResult = result
        if (result?.currentPage ?: 0 <= 1) {
            modelList.clear()
            adapter!!.notifyDataSetChanged()
            (bookList!!.layoutManager as LinearLayoutManager?)!!.scrollToPositionWithOffset(0, 0)
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
        result.search = etSearch!!.text.toString().trim { it <= ' ' }
        result.isExact = cbExactSearch!!.isChecked
        result.field = spField!!.selectedItemPosition
        result.language = spLanguage!!.selectedItemPosition
        result.media = spMedia!!.selectedItemPosition
        result.base = spBase!!.selectedItemPosition
        return result
    }

    private fun setupSpinners() {
        spLanguage!!.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchLanguage.names.toTypedArray())
        spMedia!!.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchMedia.names.toTypedArray())
        spBase!!.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchBase.titles.toTypedArray())
        spField!!.adapter = ArrayAdapter<Any>(this, R.layout.spinner_item, SearchField.names.toTypedArray())
    }

    private fun toggleSearchLayout() {
        if (searchLayout!!.visibility != View.VISIBLE) {
            searchLayout!!.visibility = View.VISIBLE
            btnToggleSearch!!.hide()
        } else {
            searchLayout!!.visibility = View.GONE
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
        if (advancedSearchLayout!!.visibility != View.VISIBLE) {
            advancedSearchLayout!!.visibility = View.VISIBLE
        } else {
            advancedSearchLayout!!.visibility = View.GONE
        }
    }

    private fun addBooksToRV(list: List<SearchBookModel?>?) {
        val prevListSize = modelList.size
        modelList.addAll(list!!)
        adapter!!.notifyItemRangeInserted(prevListSize, list.size)
    }

    override fun showProgress() {
        progress!!.visibility = View.VISIBLE
        btnToggleSearch!!.visibility = View.GONE
    }

    override fun hideProgress() {
        progress!!.visibility = View.GONE
        btnToggleSearch!!.visibility = View.VISIBLE
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