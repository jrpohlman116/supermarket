package com.supermarket;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Checkout {
    private static DecimalFormat decFormat = new DecimalFormat("0.00");
    private Map<String, Item> inventory = Inventory.inventoryList;
    private Map<String, Item> cart = new HashMap<>();
    private double totalPrice = 0;

    /**
     * Check the inventory, then add the item to the cart
     */
    public void scan(String scannedItemName) {
        Item inventoryItem = checkInventory(scannedItemName);
        if (inventoryItem == null){
            return;
        }

        Item cartItem = cart.get(scannedItemName);
        // Add the item to the cart if it hasn't been added previously
        if (cartItem == null){
            cart.put(scannedItemName, new Item(inventoryItem.getName(), String.valueOf(inventoryItem.getPrice()), "0"));
            cartItem = cart.get(scannedItemName);
            if (inventoryItem.getSpecialPrice() != null){
                cartItem.setDiscount(inventoryItem.getSpecialPrice());
            }
        }

        // Increase the quantity of the item in the cart
        cartItem.addToQuantity();
    }

    /**
     * Check to see if the scanned item by the user is in the current inventory then subtract from the inventory
     * @param scannedItemName
     * @return scannedItem
     */
    public Item checkInventory(String scannedItemName) {
        Item scannedItem = inventory.get(scannedItemName);
        if (scannedItem == null || scannedItem.getQuantity() == 0) {
            System.out.println("The item you tried to scan is not in our inventory.");
            return null;
        } else {
            scannedItem.subtractFromQuantity();
        }

        return scannedItem;
    }

    /**
     * Call the pricing service to add up the quantities of each item in the cart
     * @return totalPrice
     */
    public double calculateTotal() {
        for (Item item : cart.values()) {
            this.totalPrice += PricingService.calculatePrice(item);
        }

        return this.totalPrice;
    }

    /**
     * Print the individual prices and discounts in a readable form as well as the total
     */
    public void printReceipt() {
        for (Item item : cart.values()) {
            System.out.println(item.toString());
        }
        System.out.println("Total: \t\t$" + decFormat.format(this.totalPrice));
    }

    public Map<String, Item> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
    
}
