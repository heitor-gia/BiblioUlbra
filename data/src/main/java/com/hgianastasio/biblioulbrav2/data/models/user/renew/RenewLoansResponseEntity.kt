package com.hgianastasio.biblioulbrav2.data.models.user.renew

import com.google.gson.annotations.SerializedName

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class RenewLoansResponseEntity {
    @SerializedName("renovado")
    var status: String? = null

    @SerializedName("razao")
    var message: String? = null

    constructor() {}
    constructor(status: String?, message: String?) {
        this.status = status
        this.message = message
    }

}