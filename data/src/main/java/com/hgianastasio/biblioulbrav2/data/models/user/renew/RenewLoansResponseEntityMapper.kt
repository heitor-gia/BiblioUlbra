package com.hgianastasio.biblioulbrav2.data.models.user.renew

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse
/**
 * Created by heitorgianastasio on 4/28/17.
 */
class RenewLoansResponseEntityMapper constructor() : Mapper<RenewLoansResponse?, RenewLoansResponseEntity?>() {
    override fun transform(input: RenewLoansResponse?): RenewLoansResponseEntity {
        return RenewLoansResponseEntity(input?.message, if (input?.status == true) "sim" else "nao")
    }

    override fun transformBack(input: RenewLoansResponseEntity?): RenewLoansResponse {
        return RenewLoansResponse(input?.status.equals("sim", ignoreCase = true), input?.message)
    }
}