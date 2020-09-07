package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.user.interactors.RenewLoans
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModel
import com.hgianastasio.biblioulbrav2.models.user.RenewLoansResponseModelMapper
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
/**
 * Created by heitor_12 on 11/05/17.
 */
class RenewLoansPresenter constructor(executor: UseCaseExecutor, private val renewLoans: RenewLoans, private val mapper: RenewLoansResponseModelMapper) : BasePresenter(executor) {
    fun renewLoans(onResultListener: OnResultListener<RenewLoansResponseModel>, errorListener: OnErrorListener) {
        if (progressListener != null) progressListener!!.showProgress()
        useCaseExecutor.execute(renewLoans, null,
                { result: RenewLoansResponse ->
                    if (progressListener != null) progressListener!!.hideProgress()
                    onResultListener(mapper.transform(result))
                },
                { e: Exception ->
                    if (progressListener != null) progressListener!!.hideProgress()
                    errorListener(e)
                })
    }

}