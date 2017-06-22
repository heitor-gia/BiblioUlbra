package com.hgianastasio.biblioulbrav2.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel;
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter;
import com.hgianastasio.biblioulbrav2.views.adapters.LoanBookAdapter;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class LoanBooksListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnProgressListener {
    @BindView(R.id.progress)
    View progress;
    @BindView(R.id.noBooksFound)
    View noBooksView;
    @BindView(R.id.bookList)
    RecyclerView rvLoanBooks;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    LoanBookAdapter adapter;
    List<LoanBookModel> bookModelList = new ArrayList<>();

    @Inject
    LoanBooksListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }


    @Override
    protected void postBind() {
        presenter.setProgressListener(this);
        refreshLayout.setOnRefreshListener(this);
        rvLoanBooks.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new LoanBookAdapter(bookModelList,getContext());
        adapter.setOnItemClickListener(this::createLoanBookDialog);
        rvLoanBooks.setAdapter(adapter);
        presenter.getLoanBooks(
                this::renderLoanBookList,
                e -> Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_simple_books;
    }

    @Override
    public String getFragTag() {
        return "LoanBooks";
    }

    @Override
    public String getTitle() {
        return "Empréstimos";
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        presenter.reloadLoanBooks(
                this::renderLoanBookList,
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

    private void renderLoanBookList(List<LoanBookModel> list){
        if(list.isEmpty()){
            if ( noBooksView!=null ) noBooksView.setVisibility(View.VISIBLE);
        }else {
            if ( noBooksView!=null ) noBooksView.setVisibility(View.GONE);
            bookModelList.clear();
            bookModelList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    private void createLoanBookDialog(LoanBookModel model){
        new AlertDialog.Builder(getContext())
                .setTitle(model.getTitle())
                .setMessage(String.format(
                        "Biblioteca: %s\n" +
                                "Prazo: %s\n" +
                                "Multa: R$%s\n" +
                                "Código: %s\n" +
                                "Descrição: %s",
                        model.getLibrary(),
                        model.getDeadline(),
                        model.getPenalty(),
                        model.getCode(),
                        model.getDescription()
                ))
                .setNeutralButton("Ok",null)
                .show();
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
