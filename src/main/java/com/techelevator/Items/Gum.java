package com.techelevator.Items;

public class Gum extends VendingItems {

    public Gum(String slot, String name, double price, int inventory) {
        super(slot, name, price, inventory);
    }

    @Override
    public String displayMessage() {
        return "Chew Chew, Pop!";
    }

}
