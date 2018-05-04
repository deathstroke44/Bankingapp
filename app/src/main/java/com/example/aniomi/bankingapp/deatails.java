package com.example.aniomi.bankingapp;

/**
 * Created by aniomi on 5/4/18.
 */

public class deatails {
    String id,status,rdate;
    int balance;

    public deatails(String id, String status, String rdate, int balance) {
        this.id = id;
        this.status = status;
        this.rdate = rdate;
        this.balance = balance;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public deatails(String id, String status, int balance) {
        this.id = id;
        this.status = status;
        this.balance = balance;
    }

    public deatails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
