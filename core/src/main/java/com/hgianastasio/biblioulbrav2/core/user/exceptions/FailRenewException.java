package com.hgianastasio.biblioulbrav2.core.user.exceptions;

/**
 * Created by heitorgianastasio on 4/26/17.
 */

public class FailRenewException extends Exception {
    String cause;

    public FailRenewException(String cause) {
        this.cause = cause;
    }
    public FailRenewException(String cause, Throwable throwable) {
        super(throwable);
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return String.format("Não foi possível renovar os empréstimos.\nRazão: %s",cause);
    }
}
