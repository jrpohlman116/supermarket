package com.supermarket;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.*;
import org.json.*;

public class Inventory {
    // Object to keep track of the active inventory with the item name as the key
    // Made public for the users of the class
    public static Map<String, Item> inventoryList = new HashMap<>();
    private static String dateFormatted;
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public Inventory() {
        LocalDateTime date = LocalDateTime.now();
        this.dateFormatted = date.format(dateFormat);
    }

    public Inventory(LocalDateTime date) {
        this.dateFormatted = date.format(dateFormat);
    }

    /**
     * Create a inventory list by reading from a json file located in the root directory 
     * and looking for today's date for the most recent inventory
     */
    public static void checkInventory() {

        try {
            String inventoryFile = new JSONParser().parse(new FileReader("inventory.json")).toString(); 
            JSONObject salesJson = new JSONObject(inventoryFile); 

            JSONArray todaysInventory = (JSONArray) salesJson.get(dateFormatted);

            for (int i = 0; i < todaysInventory.length(); i++){
                JSONObject jsonItem = todaysInventory.getJSONObject(i);

                Item item = new Item(jsonItem.get("name").toString(), jsonItem.get("price").toString(), jsonItem.get("quantity").toString());
                if (jsonItem.has("specialPrice")){
                    item.setDiscount(jsonItem.get("specialPrice").toString());
                }
                
                inventoryList.put(item.getName(), item);
                System.out.println(item.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
