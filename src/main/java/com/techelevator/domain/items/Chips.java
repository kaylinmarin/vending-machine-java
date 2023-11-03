package com.techelevator.domain.items;

public class Chips extends VendingItems {

    public Chips(String slot, String name, double price, int inventory) {
        super(slot, name, price, inventory);
    }

    @Override
    public String displayMessage() {
        return "Crunch Crunch, It's Yummy!";
    }
}
