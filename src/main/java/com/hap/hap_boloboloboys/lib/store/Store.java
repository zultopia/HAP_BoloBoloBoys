package com.hap.hap_boloboloboys.lib.store;

import java.util.HashMap;
import java.util.Map;

import com.hap.hap_boloboloboys.lib.card.Product;

public class Store {
    private Map<String, Integer> inventory; // cardName -> quantity
    private Map<String, Integer> prices;    // cardName -> price

    public Store() {
        inventory = new HashMap<>();
        prices = new HashMap<>();
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public Map<String, Integer> getPrices() {
        return prices;
    }

    public void sellProduct(Product product) {
        String cardName = product.getCardName();
        inventory.put(cardName, inventory.getOrDefault(cardName, 0) + 1);
        prices.put(cardName, product.getPrice());
    }

    public void buyProduct(Product product) {
        String cardName = product.getCardName();
        if (inventory.containsKey(cardName) && inventory.get(cardName) > 0) {
            inventory.put(cardName, inventory.get(cardName) - 1);
        }
    }

    public int getProductPrice(Product product) {
        return prices.getOrDefault(product.getCardName(), 0);
    }

    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String cardName = entry.getKey();
            int quantity = entry.getValue();
            int price = prices.getOrDefault(cardName, 0);
            System.out.println(cardName + ": " + price + " (" + quantity + ")");
        }
    }

    public boolean canBuyProduct(Product product) {
        return inventory.getOrDefault(product.getCardName(), 0) > 0;
    }

    public boolean canSellProduct(Product product) {
        return true;
    }

    // public static void main(String[] args) {
    //     Store store = new Store();
    //     store.sellProduct(new Product("Apple", "apple.png", 10, 5));
    //     store.sellProduct(new Product("Apple", "apple.png", 10, 5));
    //     store.sellProduct(new Product("Banana", "banana.png", 5, 3));
    //     store.displayInventory();
    //     store.buyProduct(new Product("Apple", "apple.png", 10, 5));
    //     store.displayInventory();
    // }
}