package com.techelevator.cli;

import com.techelevator.domain.items.VendingItems;
import com.techelevator.domain.vending.VendingMachine;
import com.techelevator.services.Logger;

import java.util.Map;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private VendingMenu menu;
	private Logger logger;

	public VendingMachineCLI(VendingMenu menu, Logger logger) {
		this.menu = menu;
		this.logger = logger;
	}

	public void run() throws Exception {

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
					for (VendingItems item : inventory.values()){
						System.out.printf("%-5s %-20s %-6s %-1s %-8s \n", item.getSlot(), item.getName(), item.getPrice(), "Available:", item.getInventory());
					}
					break;
				case MAIN_MENU_OPTION_PURCHASE:
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					switch (purchaseChoice) {
						case PURCHASE_MENU_OPTION_FEED_MONEY:
							//userInput to get money
							//vendingMachine.depositMoney();
						case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
							//userInput key/slot selection
							//type message output
							//vendingMachine.makePurchase();
						case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
							//Quarters, dimes, and nickels???
							//vendingMachine.returnChange();
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
					break;
			}
		}
	}
}
