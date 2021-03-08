package com.supermarket;

public class PricingService {
    /**
     * Calculate the price of a given item based on its quantity and any discounts
     * @param item
     * @return total price for a given item
     */
    public static int calculatePrice(Item item) {
        int[] discount = item.getDiscount();
        int quantity = item.getQuantity();
        
        if (discount == null || discount.length == 0 || quantity < discount[0]){
            return item.getPrice() * quantity; 
        }
        else {
            int total = 0;
            while (quantity >= discount[0]){
                total += discount[1];
                quantity -= discount[0];
            }

            if (quantity > 0) total += item.getPrice() * quantity;
            return total;
        }
        
    }
}
