package com.hgianastasio.biblioulbrav2.models.user;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 11/05/17.
 */

public class RenewLoansResponseModelMapper extends Mapper<RenewLoansResponse,RenewLoansResponseModel> {
    @Inject public RenewLoansResponseModelMapper() {}

    @Override
    public RenewLoansResponseModel transform(RenewLoansResponse input) {
        return new RenewLoansResponseModel(input.getStatus(),input.getMessage());
    }

    @Override
    public RenewLoansResponse transformBack(RenewLoansResponseModel input) {
        return new RenewLoansResponse(input.isSuccess(),input.getMessage());
    }
}
