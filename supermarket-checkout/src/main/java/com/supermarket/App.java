package com.supermarket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        System.out.println("Hello, valued customer!");
        
        System.out.println("\nItems for Sale: ");
        inventory.checkInventory();
        Checkout checkout = new Checkout(inventory.getInventoryList());

        System.out.println("\nPlease scan your items one by one. At any time, you can "
            + "type CANCEL to exit the transaction, or DONE to finish paying.\n");
        
        String currentItem = scanner.nextLine();

        while (!(currentItem).equalsIgnoreCase("CANCEL") && 
        !(currentItem).equalsIgnoreCase("DONE")){
            checkout.scan(currentItem.toUpperCase());
            currentItem = scanner.nextLine();
        }

        if (currentItem.equalsIgnoreCase("CANCEL")) {
            System.out.println("We are sorry to see you go. Hope to see you again next time!");
        } else {
            checkout.calculateTotal();
            System.out.println("Thank you for shopping with us. Would you like your reciept? (Y/N)");
            currentItem = scanner.nextLine();
            switch(currentItem.toUpperCase()) {
                case "Y":
                    checkout.printReceipt();
                    break;
                default:
                    break;
            }
        }
    }
}
