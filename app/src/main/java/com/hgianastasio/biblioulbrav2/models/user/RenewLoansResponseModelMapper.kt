package com.hgianastasio.biblioulbrav2.models.user

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse
import javax.inject.Inject

/**
 * Created by heitor_12 on 11/05/17.
 */
class RenewLoansResponseModelMapper @Inject constructor() : Mapper<RenewLoansResponse, RenewLoansResponseModel>() {
    override fun transform(input: RenewLoansResponse): RenewLoansResponseModel {
        return RenewLoansResponseModel(input.status, input.message.orEmpty())
    }

    override fun transformBack(input: RenewLoansResponseModel): RenewLoansResponse {
        return RenewLoansResponse(input.isSuccess, input.message)
    }
}