package com.hgianastasio.biblioulbrav2.core.user.exceptions

/**
 * Created by heitorgianastasio on 4/26/17.
 */
class FailRenewException : Exception {
    var reason: String?

    constructor(cause: String?) {
        this.reason = cause
    }

    constructor(cause: String?, throwable: Throwable?) : super(throwable) {
        this.reason = cause
    }

    override val message: String
        get() = String.format("Não foi possível renovar os empréstimos.\nRazão: %s", reason)
}