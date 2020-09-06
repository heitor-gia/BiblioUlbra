package com.hgianastasio.biblioulbrav2.data.models.user.renew;


import com.google.gson.annotations.SerializedName;


/**
 * Created by heitorgianastasio on 4/25/17.
 */
public class RenewLoansResponseEntity {
    @SerializedName("renovado")
    private String status;

    @SerializedName("razao")
    private String message;

    public RenewLoansResponseEntity() {
    }

    public RenewLoansResponseEntity(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
