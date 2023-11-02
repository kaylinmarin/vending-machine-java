package com.techelevator;

public class Candy extends VendingItems {

    public Candy(String slot, String name, double price, int inventory) {
        super(slot, name, price, inventory);
    }

    @Override
    public String displayMessage() {
        return "Munch Munch, Mmm Mmm Good!";
    }
}
