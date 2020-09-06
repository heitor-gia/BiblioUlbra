package com.hgianastasio.biblioulbrav2.views.fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.user.UserModel;
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heitor_12 on 11/05/17.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnProgressListener {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPenalty)
    TextView tvPenalty;
    @BindView(R.id.tvBookings)
    TextView tvBookings;
    @BindView(R.id.tvHistory)
    TextView tvHistory;
    @BindView(R.id.tvLoans)
    TextView tvLoans;
    @BindView(R.id.btnRenewLoans)
    FloatingActionButton btnRenewLoans;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    OnCardClickListener onCardClickListener;

    @Inject
    UserModelPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        presenter.getUser(this::renderUserModel,Throwable::printStackTrace);
    }

    @Override
    protected void postBind() {
        super.postBind();
        refreshLayout.setOnRefreshListener(this);
        btnRenewLoans.setOnClickListener(v -> {
            RenewLoansDialogFragment fragment = new RenewLoansDialogFragment();
            fragment.show(getChildFragmentManager(),"oi");
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    private void renderUserModel(UserModel userModel){
        tvName.setText(userModel.getNameLastName());
        tvPenalty.setText(String.format("Dívida:\nR$%s",userModel.getDebt()));
        tvPenalty.setTextColor(ContextCompat.getColor(getContext(),userModel.isOverdue()?R.color.redPenalty:R.color.greenPenalty));
        tvBookings.setText("Reservas:\n"+getResources().getQuantityString(R.plurals.books,userModel.getBookings(),userModel.getBookings()));
        tvHistory.setText("Histórico:\n"+getResources().getQuantityString(R.plurals.books,userModel.getHistory(),userModel.getHistory()));
        tvLoans.setText("Empréstimos:\n"+getResources().getQuantityString(R.plurals.books,userModel.getLoans(),userModel.getLoans()));
        if(userModel.getLoans()==0){
            btnRenewLoans.setClickable(false);
            btnRenewLoans.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.greyDisabled)));
        }
    }

    @Override
    public String getFragTag() {
    return "HomeFragment";
    }

    @Override
    public String getTitle() {
        return "Home";
    }
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
    }
    @Override
    public void onDestroyView() {
        presenter.unsetProgressListener();
        onCardClickListener = null;
        super.onDestroyView();
    }

    @OnClick({R.id.loansCard,R.id.historyCard})
    public void cardClick(View view){
        if(onCardClickListener!=null)
        switch (view.getId()){
            case R.id.loansCard:
                onCardClickListener.onCardClick(R.id.navLoans);
                break;
            case R.id.historyCard:
                onCardClickListener.onCardClick(R.id.navHistory);
                break;
        }
    }

    public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }


    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    public static HomeFragment createInstance(OnCardClickListener listener){
        HomeFragment fragment = new HomeFragment();
        fragment.setOnCardClickListener(listener);
        return fragment;
    }

    public interface OnCardClickListener{
        void onCardClick(int id);
    }

}
