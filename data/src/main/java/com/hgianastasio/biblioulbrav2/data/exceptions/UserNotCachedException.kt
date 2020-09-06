package com.hgianastasio.biblioulbrav2.data.exceptions

/**
 * Created by heitorgianastasio on 4/28/17.
 */
class UserNotCachedException : RuntimeException {
    constructor() {}
    constructor(cause: Throwable?) : super(cause) {}

    override val message: String
        get() = "Não foi possível recuperar os dados do usuário"
}