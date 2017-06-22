package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.user.interactors.RenewLoans;
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModel;
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModelMapper;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 11/05/17.
 */

public class RenewLoansPresenter extends BasePresenter {

    private RenewLoans renewLoans;
    private RenewLoansResponseModelMapper mapper;

    @Inject
    public RenewLoansPresenter(UseCaseExecutor executor, RenewLoans renewLoans, RenewLoansResponseModelMapper mapper) {
        super(executor);
        this.renewLoans = renewLoans;
        this.mapper = mapper;
    }


    public void renewLoans(OnResultListener<RenewLoansResponseModel> onResultListener, OnErrorListener errorListener){
        if (progressListener!=null)progressListener.showProgress();
        useCaseExecutor.execute(renewLoans,null,
                result->{
                    if (progressListener!=null)progressListener.hideProgress();
                    onResultListener.onResult(mapper.transform(result));
                },
                e -> {
                    if (progressListener!=null)progressListener.hideProgress();
                    errorListener.onError(e);
                });
    }
}
