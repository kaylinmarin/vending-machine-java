package com.techelevator.cli;

import com.techelevator.domain.items.VendingItems;
import com.techelevator.domain.vending.VendingMachine;
import com.techelevator.services.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;


public class VendingMachineCLI{

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
	private static final String[] MAIN_MENU_OPTIONS_TO_DISPLAY = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

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
					for (VendingItems item : inventory.values()){
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
							for (String message: purchaseMessages) {
								System.out.println(message);
							}
							break;
						case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
							//Quarters, dimes, and nickels???
							//vendingMachine.returnChange();
							System.out.printf("Thank you for purchasing. I'm returning %.2f to you", vendingMachine.returnChange());
							break;
					}
					break;

				case MAIN_MENU_OPTION_EXIT:
					running = false;
					break;
				case MAIN_MENU_SECRET_OPTION:
					// make invisible (option 4)
					// print sales log
					// also create a sales log (write to log file?)
					Collection<String> messages = logger.readFromLog();
					for (String message: messages){
						System.out.println(message);
					}
					break;
			}
		}
	}
}
