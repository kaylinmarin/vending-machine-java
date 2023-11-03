package com.techelevator.Items;

public class Drinks extends VendingItems {


    public Drinks(String slot, String name, double price, int inventory) {
        super(slot, name, price, inventory);
    }

    @Override
    public String displayMessage() {
        return "Glug Glug, Chug Chug";
    }
}
