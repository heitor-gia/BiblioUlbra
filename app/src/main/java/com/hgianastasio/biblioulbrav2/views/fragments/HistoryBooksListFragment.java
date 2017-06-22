package com.hgianastasio.biblioulbrav2.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel;
import com.hgianastasio.biblioulbrav2.presenters.HistoryBooksListPresenter;
import com.hgianastasio.biblioulbrav2.views.adapters.HistoryBookAdapter;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class HistoryBooksListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnProgressListener {
    @BindView(R.id.progress)
    View progress;

    @BindView(R.id.noBooksFound)
    View noBooksView;
    @BindView(R.id.bookList)
    RecyclerView rvHistoryBooks;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    HistoryBookAdapter adapter;
    List<HistoryBookModel> bookModelList = new ArrayList<>();

    @Inject
    HistoryBooksListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }


    @Override
    protected void postBind() {
        presenter.setProgressListener(this);
        refreshLayout.setOnRefreshListener(this);
        rvHistoryBooks.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new HistoryBookAdapter(bookModelList,getContext());
        rvHistoryBooks.setAdapter(adapter);
        presenter.getHistoryBooks(
                this::renderHistoryBookList,
                e -> Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_simple_books;
    }

    @Override
    public String getFragTag() {
        return "HistoryBooks";
    }

    @Override
    public String getTitle() {
        return "Histórico";
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        presenter.reloadHistoryBooks(
                this::renderHistoryBookList,
                e -> {
                    Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
    }

    @Override
    public void onDestroyView() {
        presenter.unsetProgressListener();
        presenter.destroy();
        super.onDestroyView();
    }

    private void renderHistoryBookList(List<HistoryBookModel> list){
        if(list.isEmpty()){
            if ( noBooksView!=null ) noBooksView.setVisibility(View.VISIBLE);
        }else {
            if ( noBooksView!=null ) noBooksView.setVisibility(View.GONE);
            bookModelList.clear();
            bookModelList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}
}
