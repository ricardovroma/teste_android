package com.example.ricardo.myapplication.api;

import java.io.Serializable;

/**
 * Created by ricardo on 17/08/16.
 */
public class ProfileWithdrawal implements Serializable {
    public int profile;
    public String account;
    public String reference_date;
    public String balance;
    public String retrieval;

    public ProfileWithdrawal(int profile, String account, String reference_date, String balance, String retrieval) {
        this.profile = profile;
        this.account = account;
        this.reference_date = reference_date;
        this.balance = balance;
        this.retrieval = retrieval;
    }

    @Override
    public String toString() {
        return "ProfileWithdrawal{" +
                "profile=" + profile +
                ", account='" + account + '\'' +
                ", reference_date='" + reference_date + '\'' +
                ", balance='" + balance + '\'' +
                ", retrieval='" + retrieval + '\'' +
                '}';
    }
}
