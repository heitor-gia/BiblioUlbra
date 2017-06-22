package com.hgianastasio.biblioulbrav2.core.user.models;



/**
 * Created by heitor_12 on 02/09/16.
 */
public class User{

    private String name;
    private String cgu;
    private int loans;
    private int history;
    private int bookings;
    private double debt;


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

    public int getLoans() {
        return loans;
    }

    public void setLoans(int loans) {
        this.loans = loans;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getBookings() {
        return bookings;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }


}
