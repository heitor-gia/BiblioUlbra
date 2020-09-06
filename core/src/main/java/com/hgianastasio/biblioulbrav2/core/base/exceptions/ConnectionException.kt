package com.hgianastasio.biblioulbrav2.core.base.exceptions

/**
 * Created by heitorgianastasio on 4/26/17.
 */
class ConnectionException : RuntimeException {
    constructor() {}
    constructor(s: String?) : super(s) {}
    constructor(s: String?, throwable: Throwable?) : super(s, throwable) {}
    constructor(throwable: Throwable?) : super(throwable) {}

    override val message: String
        get() = "Não foi possível acessar o servidor"
}