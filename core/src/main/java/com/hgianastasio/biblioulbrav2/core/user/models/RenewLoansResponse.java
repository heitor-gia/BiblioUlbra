package com.hgianastasio.biblioulbrav2.core.user.models;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class RenewLoansResponse {
    private boolean status;
    private String message;

    public RenewLoansResponse() {
    }

    public RenewLoansResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
