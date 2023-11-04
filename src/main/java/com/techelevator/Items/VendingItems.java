package com.techelevator.Items;

public abstract class VendingItems {

    public double priceOfProduct=0;
    private String slot;
    private String name;
    private double price;

    private int inventory = 5;
    public String product= "";

    public VendingItems(String slot, String name, double price, int inventory) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public abstract String displayMessage();


    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getSlot() {
        return slot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getInventory() {
        return inventory;
    }
}
