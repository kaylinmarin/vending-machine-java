package com.techelevator.domain.Transactions;

import java.util.Date;

public class TransactionRecord {
    //capture date, item, price, position
    public String name;
    public double price;
    public String position;
    public Date date;


    public TransactionRecord(String name, double price, String position, Date date) {
        this.name = name;
        this.price = price;
        this.position = position;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
