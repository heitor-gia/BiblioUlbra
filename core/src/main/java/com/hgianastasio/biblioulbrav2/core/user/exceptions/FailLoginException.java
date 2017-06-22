package com.hgianastasio.biblioulbrav2.core.user.exceptions;

/**
 * Created by heitorgianastasio on 4/26/17.
 */

public class FailLoginException extends Exception {
    String cgu;

    public FailLoginException(String cgu) {
        this.cgu = cgu;
    }
    public FailLoginException(String cgu, Throwable throwable) {
        super(throwable);
        this.cgu = cgu;
    }

    @Override
    public String getMessage() {
        return String.format("O cgu %s é inválido",cgu);
    }
}
