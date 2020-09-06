package com.hgianastasio.biblioulbrav2.data.models.user;


import com.google.gson.annotations.SerializedName;


/**
 * Created by heitor_12 on 02/09/16.
 */
public class UserEntity {

    @SerializedName("usuario")
    private String name;

    @SerializedName("cgu")
    private String cgu;

    @SerializedName("emprestimos")
    private String loans;

    @SerializedName("historico")
    private String history;

    @SerializedName("reservas")
    private String bookings;

    @SerializedName("caixa")
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
