package com.hgianastasio.biblioulbrav2.data.exceptions;

/**
 * Created by heitorgianastasio on 4/28/17.
 */

public class UserNotCachedException extends RuntimeException {

    public UserNotCachedException() {
    }

    public UserNotCachedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Não foi possível recuperar os dados do usuário";
    }
}
