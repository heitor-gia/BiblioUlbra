package com.hgianastasio.biblioulbrav2.core.user.exceptions

/**
 * Created by heitorgianastasio on 4/26/17.
 */
class FailLoginException : Exception {
    var cgu: String?

    constructor(cgu: String?) {
        this.cgu = cgu
    }

    constructor(cgu: String?, throwable: Throwable?) : super(throwable) {
        this.cgu = cgu
    }

    override val message: String
        get() = String.format("O cgu %s é inválido", cgu)
}