package com.hgianastasio.biblioulbrav2.views.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia;
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel;
import com.hgianastasio.biblioulbrav2.models.search.searchmodel.SearchModelModel;
import com.hgianastasio.biblioulbrav2.models.search.searchresult.SearchResultModel;
import com.hgianastasio.biblioulbrav2.presenters.SearchPresenter;
import com.hgianastasio.biblioulbrav2.views.adapters.SearchBookAdapter;
import com.hgianastasio.biblioulbrav2.views.listeners.EndlessRecyclerViewScrollListener;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by heitor_12 on 31/05/17.
 */

public class SearchFragment extends BaseFragment implements OnProgressListener {
    @Inject
    SearchPresenter presenter;

    @BindView(R.id.progress)
    View progress;
    @BindView(R.id.cbExactSearch)
    CheckBox cbExactSearch;
    @BindView(R.id.spLanguage)
    Spinner spLanguage;
    @BindView(R.id.spMedia)
    Spinner spMedia;
    @BindView(R.id.spField)
    Spinner spField;
    @BindView(R.id.spBase)
    Spinner spBase;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.vwAdvancedSearchLayout)
    View advancedSearchLayout;
    @BindView(R.id.searchLayout)
    View searchLayout;
    @BindView(R.id.btnToggleAdvancedSearch)
    View btnToggleAdvancedSearch;
    @BindView(R.id.bookList)
    RecyclerView bookList;
    @BindView(R.id.btnToggleSearch)
    FloatingActionButton btnToggleSearch;

    SearchResultModel currentResult;
    SearchBookAdapter adapter;
    List<SearchBookModel> modelList = new ArrayList<>(10);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    protected void postBind() {
        presenter.setProgressListener(this);
        initRecyclerView();
        setupSpinners();
        btnToggleAdvancedSearch.setOnClickListener(v -> toggleAdvancedSearchLayout());
        btnToggleSearch.setOnClickListener(v -> toggleSearchLayout());
        btnSearch.setOnClickListener(v -> doSearch());
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_search_books;
    }

    @Override
    public String getFragTag() {
        return "SearchBooks";
    }

    @Override
    public String getTitle() {
        return "Pesquisa";
    }

    private void initRecyclerView(){
        adapter = new SearchBookAdapter(modelList,getContext());
        adapter.setOnItemClickListener(this::createSearchBookDialog);
        bookList.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bookList.setLayoutManager(layoutManager);
        bookList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(Math.abs(dy)>30){
                    SearchFragment.this.hideKeyboard();
                    searchLayout.setVisibility(View.GONE);
                    btnToggleSearch.show();
                }
            }
        });
        bookList.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if(currentResult.getCurrentPage()<currentResult.getPages())
                    presenter.nextPage(currentResult,SearchFragment.this::processResults,SearchFragment.this::processError);
            }
        });
    }

    private void doSearch(){
        if (!etSearch.getText().toString().trim().isEmpty()) {
            this.hideKeyboard();
            presenter.search(buildSearchModel(),this::processResults,this::processError);
            searchLayout.setVisibility(View.GONE);
            btnToggleSearch.show();
        }else {
            Toast.makeText(getContext(), "Preencha o campo de busca", Toast.LENGTH_SHORT).show();
        }
    }

    private void processResults(SearchResultModel result){
        this.currentResult = result;
        if (result.getCurrentPage()<=1){
            this.modelList.clear();
            this.adapter.notifyDataSetChanged();
            ((LinearLayoutManager)this.bookList.getLayoutManager()).scrollToPositionWithOffset(0,0);
        }
        addBooksToRV(result.getResult());

    }


    private void processError(Exception e){
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsetProgressListener();
    }

    private SearchModelModel buildSearchModel(){
        SearchModelModel result = new SearchModelModel();
        result.setSearch(etSearch.getText().toString().trim());
        result.setExact(cbExactSearch.isChecked());
        result.setField(spField.getSelectedItemPosition());
        result.setLanguage(spLanguage.getSelectedItemPosition());
        result.setMedia(spMedia.getSelectedItemPosition());
        result.setBase(spBase.getSelectedItemPosition());
        return result;
    }

    private void setupSpinners(){
        spLanguage.setAdapter(new ArrayAdapter<>(getContext(),R.layout.spinner_item,SearchLanguage.getNames().toArray()));
        spMedia.setAdapter(new ArrayAdapter<>(getContext(),R.layout.spinner_item, SearchMedia.getNames().toArray()));
        spBase.setAdapter(new ArrayAdapter<>(getContext(),R.layout.spinner_item, SearchBase.getNames().toArray()));
        spField.setAdapter(new ArrayAdapter<>(getContext(),R.layout.spinner_item, SearchField.getNames().toArray()));
    }

    private void toggleSearchLayout(){
        if(searchLayout.getVisibility()!=View.VISIBLE){
            searchLayout.setVisibility(View.VISIBLE);
            btnToggleSearch.hide();
        }else{
            searchLayout.setVisibility(View.GONE);
        }
    }

    private void createSearchBookDialog(SearchBookModel model){
        new AlertDialog.Builder(getContext())
                .setTitle("Exemplares")
                .setMessage(TextUtils.join("\n",model.getCatchedExps()))
                .setNeutralButton("Ok",null)
                .show();
    }

    private void toggleAdvancedSearchLayout(){
        if(advancedSearchLayout.getVisibility()!=View.VISIBLE){
            advancedSearchLayout.setVisibility(View.VISIBLE);
        }else{
            advancedSearchLayout.setVisibility(View.GONE);
        }
    }

    private void addBooksToRV(List<SearchBookModel> list){
        int prevListSize = modelList.size();
        this.modelList.addAll(list);
        this.adapter.notifyItemRangeInserted(prevListSize,list.size());
    }

    @Override
    public void showProgress() {
        this.progress.setVisibility(View.VISIBLE);
        this.btnToggleSearch.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        this.progress.setVisibility(View.GONE);
        this.btnToggleSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
        }catch (Exception e){
            Log.e("Error",Log.getStackTraceString(e));
        }
    }


}
