package com.techelevator.cli;

import com.techelevator.domain.Transactions.TransactionRecord;
import com.techelevator.domain.items.VendingItems;
import com.techelevator.domain.vending.VendingMachine;
import com.techelevator.services.Logger;

import java.awt.geom.QuadCurve2D;
import java.beans.PropertyEditorManager;
import java.util.*;


public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

    private static final String SECRET_MENU_TRANSACTION_LOG = "Transaction Log";
    private static final String SECRET_MENU_STATISTICS = "Sales Stats";

    private static final String[] SECRET_MENU_OPTIONS = {SECRET_MENU_STATISTICS, SECRET_MENU_TRANSACTION_LOG};
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
    private static final String[] MAIN_MENU_OPTIONS_TO_DISPLAY = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private VendingMenu menu;
    private Logger logger;

    public VendingMachineCLI(VendingMenu menu, Logger logger) {
        this.logger = logger;
        this.menu = menu;
    }

    public void run() throws Exception {

        boolean running = true;
        VendingMachine vendingMachine = new VendingMachine(logger);
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, MAIN_MENU_OPTIONS_TO_DISPLAY);

            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    System.out.println("");
                    System.out.printf("%-5s %-20s %-6s %-10s \n", "Slot", "Name", "Price", "Inventory");
                    System.out.println("..............................................");
                    Map<String, VendingItems> inventory = vendingMachine.getVendingMachineMap();
                    for (VendingItems item : inventory.values()) {
                        System.out.printf("%-5s %-20s %-6s %-1s %-8s \n", item.getSlot(), item.getName(), item.getPrice(), "Available:", item.getInventory());
                    }
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    switch (purchaseChoice) {
                        case PURCHASE_MENU_OPTION_FEED_MONEY:

                            System.out.print("Please add money >>> $");
                            Scanner feedMoney = new Scanner(System.in);
                            double amount = Double.parseDouble(feedMoney.nextLine());
                            if (amount > 0) {
                                vendingMachine.depositMoney(amount);
                            }
                            System.out.println(String.format("You deposited $%.2f", vendingMachine.getBalance()));
                            //userInput to get money
                            //vendingMachine.depositMoney();
                            break;
                        case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
                            System.out.println("Enter a slot number >>>");
                            Scanner selectKey = new Scanner(System.in);
                            String slotKey = (selectKey.nextLine());
                            Collection<String> purchaseMessages = vendingMachine.makePurchase(slotKey);

                            // loop over messages, printing each one
                            for (String message : purchaseMessages) {
                                System.out.println(message);
                            }
                            break;
                        case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
                            //Quarters, dimes, and nickels

                            //vendingMachine.returnChange();
                            double changeTotal = vendingMachine.returnChange();

                            int quartersToReturn = vendingMachine.quartersToReturn(changeTotal);
                            int dimesToReturn = 0;
                            int nickelsToReturn = 0;

                            double remainder = changeTotal - (quartersToReturn * vendingMachine.QUARTER);


                            if (remainder > 0) {
                                dimesToReturn = vendingMachine.dimesToReturn(remainder);
                                remainder = remainder - (dimesToReturn * vendingMachine.DIME);

                            }
                            if (remainder > 0) {
                                nickelsToReturn = vendingMachine.nickelsToReturn(remainder);
                            }


                            System.out.println("Here is your change:Quarters:  " + quartersToReturn + ", dimes: " + dimesToReturn + ", nickels:" + nickelsToReturn);


                            System.out.printf("Thank you for purchasing. I'm returning %.2f to you", changeTotal);
                            break;
                    }
                    break;

                case MAIN_MENU_OPTION_EXIT:
                    running = false;
                    System.out.println("Goodbye");
                    break;
                case MAIN_MENU_SECRET_OPTION:
                    // make invisible (option 4)
                    // print sales log
                    // also create a sales log (write to log file?)
                    String secretChoice = (String) menu.getChoiceFromOptions(SECRET_MENU_OPTIONS);
                    switch (secretChoice) {
                        case SECRET_MENU_TRANSACTION_LOG:
                            Collection<String> messages = logger.readFromLog();
                            for (String message : messages) {
                                System.out.println(message);
                            }
                            break;
                        case SECRET_MENU_STATISTICS:
                            Map<String, Integer> itemTotals = new HashMap<>();
                            for(TransactionRecord record: vendingMachine.transactions){
                                if(itemTotals.containsKey(record.name)) {
                                    int value = itemTotals.get(record.name);
                                    itemTotals.put(record.name, value + 1);

                                }else{
                                    itemTotals.put(record.name, 1);
                                }

                            }
                            for(Map.Entry<String,Integer> entry: itemTotals.entrySet()){
                                String name = entry.getKey();
                                int value = entry.getValue();
                                System.out.println(name + " : " + value);

                            }
                            break;
                    }
                    break;
            }
        }
    }
}
