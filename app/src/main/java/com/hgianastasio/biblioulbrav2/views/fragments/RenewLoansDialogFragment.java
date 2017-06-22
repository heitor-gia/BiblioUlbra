package com.hgianastasio.biblioulbrav2.views.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.di.components.ActivityComponent;
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel;
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModel;
import com.hgianastasio.biblioulbrav2.presenters.LoanBooksListPresenter;
import com.hgianastasio.biblioulbrav2.presenters.RenewLoansPresenter;
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity;
import com.hgianastasio.biblioulbrav2.views.adapters.RenewLoanBookAdapter;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 11/05/17.
 */

public class RenewLoansDialogFragment extends DialogFragment{
    @Inject
    RenewLoansPresenter renewPresenter;
    @Inject
    LoanBooksListPresenter booksListPresenter;

    @BindView(R.id.rvBookList)
    RecyclerView rvBookList;
    @BindView(R.id.errorLayout)
    View errorLayout;
    @BindView(R.id.tvError)
    TextView tvError;
    @BindView(R.id.btnRenewLoans)
    Button btnRenewLoans;
    @BindView(R.id.tvWarningMessage)
    TextView tvWarningMessage;
    @BindView(R.id.tvOverdueMessage)
    TextView tvOverdueMessage;
    @BindView(R.id.tvSingleCopyMessage)
    TextView tvSingleCopyMessage;
    @BindView(R.id.renewFormLayout)
    View renewFormLayout;
    @BindView(R.id.progress)
    View progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_renew_loans,container,false);
        ButterKnife.bind(this,view);
        renewPresenter.setProgressListener(new OnProgressListener() {
            @Override
            public void showProgress() {
                btnRenewLoans.setEnabled(false);
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void hideProgress() {
                btnRenewLoans.setEnabled(true);
                progress.setVisibility(View.GONE);
            }
            @Override
            public void showRetry() {}
            @Override
            public void hideRetry() {

            }
        });
        rvBookList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        btnRenewLoans.setOnClickListener(v ->{
            renewPresenter.renewLoans(this::processRenewResult,this::processError);
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupDialog(getDialog());
        booksListPresenter.getLoanBooks(this::processLoanBooksResult,this::processError);
    }

    private void processRenewResult(RenewLoansResponseModel model){
        new AlertDialog.Builder(getContext())
                .setTitle("Pronto!")
                .setMessage("Seus empréstimos foram renovados")
                .setNeutralButton("OK",(dialog, which) -> RenewLoansDialogFragment.this.dismiss())
                .show();
    }

    private void processLoanBooksResult(List<LoanBookModel> list){
        rvBookList.setAdapter(new RenewLoanBookAdapter(list,getContext()));
        validateBooks(list);
        btnRenewLoans.setClickable(true);

    }

    private void validateBooks(List<LoanBookModel> list){
        boolean thereAreNoValidBooks = true;
        boolean thereAreJustRenewedBooks = false;
        boolean thereAreOverdueBooks = false;
        boolean thereAreSingleCopies = false;

        for (LoanBookModel model:list) {
            thereAreNoValidBooks &= model.isOverdue()||model.wasJustRenewed();
            thereAreJustRenewedBooks |= model.wasJustRenewed();
            thereAreOverdueBooks |= model.isOverdue();
            thereAreSingleCopies |= model.isSingleCopy();
        }

        if (thereAreNoValidBooks)
            btnRenewLoans.setVisibility(View.GONE);

        if(thereAreJustRenewedBooks)
            tvWarningMessage.setVisibility(View.VISIBLE);

        if(thereAreOverdueBooks)
            tvOverdueMessage.setVisibility(View.VISIBLE);

        if(thereAreSingleCopies)
            tvSingleCopyMessage.setVisibility(View.VISIBLE);
    }

    private void processError(Exception e){
        renewFormLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        tvError.setText(e.getMessage());
    }

    private void setupDialog(Dialog dialog){
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Renovar empréstimos");
    }

    ActivityComponent getComponent(){
        return ((BaseActivity)getActivity()).getActivityComponent();
    }

}
