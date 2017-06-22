package com.hgianastasio.biblioulbrav2.models.loanbooks;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public class LoanBookModel {
    private long id;
    private String title;
    private String deadline;
    private String penalty;
    private String library;
    private String code;
    private String description;
    private boolean overdue;
    private boolean justRenewed;
    private boolean singleCopy;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public boolean wasJustRenewed() {
        return justRenewed;
    }

    public void setJustRenewed(boolean justRenewed) {
        this.justRenewed = justRenewed;
    }

    public boolean isSingleCopy() {
        return singleCopy;
    }

    public void setSingleCopy(boolean singleCopy) {
        this.singleCopy = singleCopy;
    }
}
