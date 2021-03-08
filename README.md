## Supermarket Checkout
### Overview
This code solution is created in response to a CodeKata prompt: [Back to the Supermarket](http://codekata.com/kata/kata09-back-to-the-checkout/).The prompt gives a specific pricing scheme and asks the user to think about how they would handle "Special Pricing" as the items are scanned in one by one. 
```
 Item    Unit      Special
         Price     Price
  --------------------------
    A     50       3 for 130
    B     30       2 for 45
    C     20
    D     15
```

### Calculations
When considering how to handle the special pricing, we first have to see what the industry does in this situation. It seems that each business handles their discounts differently. It is not always the case that if an item has a special price of "3 for 130" that the indivual item is discounted. In this program, we are going with the assumption that the indivual items are NOT discounted, because it means more money for the business. However, if something is priced $1.99/lb and the customer only gets 4oz

### Inventory
Inventory at supermarkets is taken at certain intervals. The json file that contains the inventory for this program is organized by date, and the checkout will only get the inventory for the current day. 

### How to Run
```
cd supermarket-checkout
mvn compile
mvn exec:java
```
