package com.hgianastasio.biblioulbrav2.data.models.user.renew;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/28/17.
 */

public class RenewLoansResponseEntityMapper extends Mapper<RenewLoansResponse,RenewLoansResponseEntity> {
    @Inject public RenewLoansResponseEntityMapper() {}

    @Override
    public RenewLoansResponseEntity transform(RenewLoansResponse input) {
        return new RenewLoansResponseEntity(input.getMessage(),input.getStatus()?"sim":"nao");
    }

    @Override
    public RenewLoansResponse transformBack(RenewLoansResponseEntity input) {
        return new RenewLoansResponse(input.getStatus().equalsIgnoreCase("sim"),input.getMessage());
    }
}
