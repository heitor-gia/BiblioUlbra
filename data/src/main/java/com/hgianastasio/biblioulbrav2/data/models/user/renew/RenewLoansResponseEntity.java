package com.hgianastasio.biblioulbrav2.data.models.user.renew;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonIgnore;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */
@JsonObject
public class RenewLoansResponseEntity {
    @JsonField(name = "renovado")
    private String status;

    @JsonField(name = "razao")
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
