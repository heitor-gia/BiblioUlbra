package com.hgianastasio.biblioulbrav2.models.user;



/**
 * Created by heitor_12 on 02/09/16.
 */
public class UserModel {

    private String name;
    private String cgu;
    private int loans;
    private int history;
    private int bookings;
    private String debt;
    private boolean overdue;

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

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

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getNameLastName(){
        String[] names = name.split(" ");
        return names[0]+" "+names[names.length-1];
    }


}
