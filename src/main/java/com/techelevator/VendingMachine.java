package com.techelevator;

import java.io.File;
import java.util.*;

public class VendingMachine {

    public Map<String, VendingItems> vendingMachineMap = new HashMap<>();
    private double balance;
    private double sales;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public VendingMachine() throws Exception {
        File inputFile = new File("vendingmachine.csv");
        Scanner inputScanner = new Scanner(inputFile);
        while (inputScanner.hasNextLine()) {
            // read line into string
            String line = inputScanner.nextLine();
            // split string on '|'
            String[] itemArray = line.split("\\|");

            // create variables for each split value corresponding to file format
            String slot = itemArray[0];
            String name = itemArray[1];
            double price = Double.parseDouble(itemArray[2]);
            String type = itemArray[3];

            // create new VendingItems object of the appropriate type
            VendingItems item;
            switch(type){
                case "Gum":
                    item = new Gum(slot, name, price, 5);
                    break;
                case "Candy":
                    item = new Candy(slot, name, price, 5);
                    break;
                case "Chip":
                    item = new Chips(slot, name, price, 5);
                    break;
                case "Drink":
                    item = new Drinks(slot, name, price, 5);
                    break;
                default:
                    throw new Exception("The type " + type + " isn't supported.");
            }
            // populate new object with values saved in variables
            vendingMachineMap.put(item.getSlot(), item);

            // add new object and its key to the map
        }


        boolean debug = false;
    }
    public Collection<VendingItems> getInventory() {
        return vendingMachineMap.values();
    }
    public void depositMoney(double money) {
        balance += money;

    }
    public boolean makePurchase(String slot) {
        boolean itemExists = vendingMachineMap.containsKey(slot);

        VendingItems item = vendingMachineMap.get(slot);
        boolean balanceIsAdequate = balance >= item.getPrice();
        boolean itemIsInStock = item.getInventory() > 0;

        //item exists && enough money for purchase && in stock items?
        // if not, no purchase = false
        if (!itemExists || !balanceIsAdequate || !itemIsInStock) return false;
        //deduct cost of item from balance
        balance = balance - item.getPrice();
        //decrement number of items
         
        //add cost of item to sales
        sales = getSales() + sales;
        return true;
    }
    public double returnChange() {
        double change = balance;
        balance = 0;
        return change;
    }
}
