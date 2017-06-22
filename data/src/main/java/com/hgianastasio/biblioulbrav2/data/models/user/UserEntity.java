package com.hgianastasio.biblioulbrav2.data.models.user;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by heitor_12 on 02/09/16.
 */
@JsonObject
public class UserEntity {

    @JsonField(name = "usuario")
    private String name;

    @JsonField
    private String cgu;

    @JsonField(name = "emprestimos")
    private String loans;

    @JsonField(name = "historico")
    private String history;

    @JsonField(name = "reservas")
    private String bookings;

    @JsonField(name = "caixa")
    private String debt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCgu() {
        return cgu;
    }

    public void setCgu(String cgu) {
        this.cgu = cgu;
    }

    public String getLoans() {
        return loans;
    }

    public void setLoans(String loans) {
        this.loans = loans;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getBookings() {
        return bookings;
    }

    public void setBookings(String bookings) {
        this.bookings = bookings;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }


}
