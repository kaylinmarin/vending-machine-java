package com.techelevator;

import com.techelevator.view.VendingMenu;



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

	public void run() {
		// for debugging
		VendingMachine machine;
		try {
			machine = new VendingMachine();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}


		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
//
//			// A switch statement could also be used here.  Your choice.
//			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
//				// display vending machine items
//			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
//				// do purchase
//			}
			switch (choice) {
				// how is this printing it??
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					// display vending machine items
					// how do I access them from the other class? I need to call it?
					System.out.println();
					break;
				case MAIN_MENU_OPTION_PURCHASE:
					// do purchase
					break;

				default: MAIN_MENU_OPTION_EXIT:
					break;
			}
		}
	}

	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
