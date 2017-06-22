package com.hgianastasio.biblioulbrav2.core.base.exceptions;

/**
 * Created by heitorgianastasio on 4/26/17.
 */

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
    }

    public ConnectionException(String s) {
        super(s);
    }

    public ConnectionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConnectionException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return "Não foi possível acessar o servidor";
    }
}
