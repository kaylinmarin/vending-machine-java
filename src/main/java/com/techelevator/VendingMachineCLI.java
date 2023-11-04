package com.techelevator;

import com.techelevator.Items.VendingItems;
import com.techelevator.view.VendingMenu;

import java.util.Map;


public class VendingMachineCLI{

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private VendingMenu menu;

    public VendingMachineCLI(VendingMenu menu) {
        this.menu = menu;
    }

    public void run() throws Exception {
        // for debugging
//		VendingMachine machine;
//		try {
//			machine = new VendingMachine();
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
        boolean running = true;
        VendingMachine vendingMachine = new VendingMachine();
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

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
                    displayPurchaseOptions(vendingMachine);
//                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//                    switch (purchaseChoice) {
//
//                    }
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    running = false;
                    System.out.println("Goodbye");

            }
                    break;
        }
    }



//    public void loadItemsFromFile() {
//        try (Scanner fileScanner = new Scanner(vendingItemOptions)) {
//            while (fileScanner.hasNext()) {
//                String line = fileScanner.nextLine();
//                String[] data = line.split("\\|");
//                String location = "";
//                String name = "";
//                Double price = 0.0;
//                if (data.length > 0) {
//                    location = data[0];
//                    name = data[1];
//                    price = Double.parseDouble(data[2]);
//                    Vendable product = new Vendable(location, name, price);
//                    items.add(product);
//                } else {
//                    throw new Exception("Could not parse item from line. Line:" + line);
//                }
//            }
//            System.out.println("items parsed: " + items.size());
//        } catch (Exception exception) {
//
//        }
//    }

    public void displayPurchaseOptions(VendingMachine vendingMachine) {

        System.out.println("Your current balance is: " + vendingMachine.balance);


        String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
        if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
            System.out.println("Enter amount to add (in whole dollar value)");
            String moneyToDeposit = menu.getUserInput();
            int amount = Integer.parseInt(moneyToDeposit);
            depositMoney(amount, vendingMachine);

            displayPurchaseOptions(vendingMachine);

        } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
            //print out available vending options

//            choice = (String) menu.getChoiceFromOptions(placeholderItems);
//            menu.displayMenuItems(items);
//            choice = (String) showOptionsAndWaitForUserInput(items.toArray());

//			System.out.println(getCostOfProduct());
        } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
            System.out.println("Thank you, make it a great day!");

        }
    }
    public void depositMoney(int amount, VendingMachine vendingMachine) {
        if (amount > 0) {
            vendingMachine.depositMoney(amount);
        } else {
            System.out.println("Please input an amount above $0 in whole dollar value");
        }
    }

//    public void handleItemPurchase(VendingItems product) {
//        if (isItemInStock(product) && canUserPurchase(product.priceOfProduct)) {
//            purchaseItem(product);
//        }
//    }
//

//    public void subtractMoney(double amount) {
//        currentBalance -= amount;
//    }
//
//    public boolean canUserPurchase(double costOfProduct) {
//        boolean canPurchase = false;
//        if (costOfProduct < currentBalance) {
//            canPurchase = true;
//        }
//        return canPurchase;
//    }
//
//    public void purchaseItem(VendingItems product) {
//        if (canUserPurchase(product.priceOfProduct)) {
//            subtractMoney(product.priceOfProduct);
//            updateInventory(product);
//            // TODO: display message "chew chew, Yum!"
//        } else {
//            System.out.println("Error: Insufficient funds");
//        }
//    }
//
//    public boolean isItemInStock(VendingItems product) {
//        boolean inStock = false;
//        if (inStock == false) {
//            System.out.println("Item OUT OF STOCK");
//        }
//        // TODO: check if item inventory is available.
//        return inStock;
//    }
//
//    public void updateInventory(VendingItems product) {
//        // TODO: subtract item from inventory amount.
//    }

    public Object showOptionsAndWaitForUserInput(Object[] options) {
        return menu.getChoiceFromOptions(options);
    }


//				case MAIN_MENU_OPTION_PURCHASE:
//					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//					switch (purchaseChoice) {
//						case PURCHASE_MENU_OPTION_FEED_MONEY:
//							//userInput to get money
//							//vendingMachine.depositMoney();
//						case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
//							//userInput key/slot selection
//							//type message output
//							//vendingMachine.makePurchase();
//						case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
//							//Quarters, dimes, and nickels???
//							//vendingMachine.returnChange();
//							break;
//					}
//					break;
//
//				case MAIN_MENU_OPTION_EXIT:
//					running = false;
//				case MAIN_MENU_SECRET_OPTION:
//					// make invisible (option 4)
//					// print sales log
//					// also create a sales log (write to log file?)
//					break;


    public static void main(String[] args) throws Exception {
        VendingMenu menu = new VendingMenu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
