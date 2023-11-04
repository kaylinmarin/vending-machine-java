package com.techelevator.domain.vending;


import com.techelevator.domain.Transactions.TransactionRecord;
import com.techelevator.domain.items.*;
import com.techelevator.services.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.util.*;

public class VendingMachine {



    private Logger logger;
    public double QUARTER = 0.25;
   public double DIME = 0.10;
   public double NICKEL = 0.05;
   public List<TransactionRecord> transactions = new ArrayList<>();


    private Map<String, VendingItems> vendingMachineMap = new HashMap<>();
    private double balance;
    private double sales;

    public double getBalance() {
        return balance;
    }


    public VendingMachine(Logger logger) throws Exception {
        this.logger = logger;
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
    public void depositMoney(double money) {
        balance += money;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        logger.writeToLog(String.format("%s FEED MONEY: $%.2f $%.2f", formatter.format(date), money, balance));
        //get date and other info

    }
    public Collection<String> makePurchase(String slot) {
        List<String> messages = new ArrayList<>();
        slot = slot.toUpperCase(Locale.ROOT);
        boolean itemExists = vendingMachineMap.containsKey(slot);
        if (!itemExists) {
            messages.add("Sorry, " + slot + " item does not exist.");
            return messages;
        }

        VendingItems item = vendingMachineMap.get(slot);
        boolean balanceIsAdequate = balance >= item.getPrice();
        boolean itemIsInStock = item.getInventory() > 0;

        //item exists && enough money for purchase && in stock items?
        // if not, no purchase = false
        if (!itemIsInStock) {
            messages.add("Sorry, this item is out of stock.");
        }
        else if (!balanceIsAdequate) {
            messages.add(String.format("Sorry, you have $%.2f and %s costs $%.2f. Please add more money.", balance, item.getName(), item.getPrice()));

        }
        else {
            //deduct cost of item from balance
            balance -= item.getPrice();
            //decrement number of items
            item.setInventory(item.getInventory()-1);
            //add cost of item to sales
            sales += item.getPrice();

            messages.add(String.format("Purchased %s Please enjoy.", item.getName()));
            messages.add(item.displayMessage());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            logger.writeToLog(String.format("%s %s %s $%.2f $%.2f", formatter.format(date), item.getName(), slot, item.getPrice(), balance));
            TransactionRecord record = new TransactionRecord(item.getName(), item.getPrice(), item.getSlot(), date);
            transactions.add(record);
        }
        return messages;
    }
    public double returnChange() {
        double change = balance;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return change;
    }

    public int quartersToReturn(double changeTotal){
        return (int)(changeTotal / QUARTER);
    }
    public int dimesToReturn(double remainder){
        return (int)(remainder / DIME);
    }
    public int nickelsToReturn(double remainder){
        return (int)(remainder / NICKEL);
    }




    public Map<String, VendingItems> getVendingMachineMap() {
        return vendingMachineMap;
    }



}
