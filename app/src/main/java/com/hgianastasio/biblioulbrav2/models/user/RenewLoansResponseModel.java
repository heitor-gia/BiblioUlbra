package com.hgianastasio.biblioulbrav2.models.user;

/**
 * Created by heitor_12 on 11/05/17.
 */

public class RenewLoansResponseModel {
    private boolean success;
    private String message;

    public RenewLoansResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
