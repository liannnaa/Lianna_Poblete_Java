package com.company;

import com.company.AccountRecord;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        int balance = 0;
        for(AccountRecord record : charges){
            balance += record.getCharge();
        }
        return balance;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        String id = "Customer ID: " + this.getId();
        String name = "Customer Name: " + this.getName();
        String balance = "Customer Balance: " + this.getBalance();
        return id + " | " + name + " | " + balance;
    }
}
