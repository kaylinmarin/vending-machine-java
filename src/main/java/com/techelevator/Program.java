package com.techelevator;

import com.techelevator.cli.VendingMachineCLI;
import com.techelevator.cli.VendingMenu;
import com.techelevator.services.Logger;

public class Program {
    public static void main(String[] args) throws Exception {
        Logger logger = new Logger("Log.txt");
        VendingMenu menu = new VendingMenu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu, logger);
        cli.run();
    }
}
