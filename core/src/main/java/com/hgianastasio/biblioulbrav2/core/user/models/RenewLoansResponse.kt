package com.hgianastasio.biblioulbrav2.core.user.models

/**
 * Created by heitorgianastasio on 4/25/17.
 */
class RenewLoansResponse {
    var status = false
    var message: String? = null

    constructor() {}
    constructor(status: Boolean, message: String?) {
        this.status = status
        this.message = message
    }

}