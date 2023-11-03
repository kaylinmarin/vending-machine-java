package com.techelevator;

import com.techelevator.view.VendingMenu;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Map;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, }; //MAIN_MENU_SECRET_OPTION
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

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
				case MAIN_MENU_SECRET_OPTION:
					// make invisible
					// print sales log
					// also create a sales log
					break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
