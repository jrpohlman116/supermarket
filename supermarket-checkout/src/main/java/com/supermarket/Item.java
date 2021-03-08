package com.supermarket;

import java.util.Arrays;

public class Item {
    private String name;
    private int quantity = 0;
    private double price;
    private int[] discount;
    private String specialPrice;

    public Item(String name){
        this.name = name.toUpperCase();
    }

    public Item(String name, int quantity){
        this.name = name.toUpperCase();
        this.quantity = quantity;
    }

    public Item(String name, String price, String quantity){
        this.name = name.toUpperCase();
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(quantity);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void addToQuantity() {
        this.quantity++;
    }

    public void subtractFromQuantity() {
        this.quantity--;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int[] getDiscount() {
        return this.discount;
    }

    public void setDiscount(String specialPrice) {
        this.specialPrice = specialPrice;
        this.discount = Arrays.stream(specialPrice.split(" for ")).mapToInt(Integer::parseInt).toArray();
    }

    public String toString() {
        return "Item: " + this.name + "\t\tPrice: $" + this.price + "\t\tQuantity: " + this.quantity 
        + (this.specialPrice == null ? "" : "\n\t\tDiscount: " + this.specialPrice);
    }
}
