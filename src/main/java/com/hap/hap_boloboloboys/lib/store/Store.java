package com.hap.hap_boloboloboys.lib.store;

import java.util.HashMap;
import java.util.Map;
import com.hap.hap_boloboloboys.lib.card.Product;
import com.hap.hap_boloboloboys.lib.util.Pair;

public class Store {
    private Map<String, Pair<Product, Integer>> items; // <Product name, Pair<Product, quantity>>

    public Store() {
        this.items = new HashMap<>();
    }

    // getter
    public Map<String, Pair<Product, Integer>> getItems() {
        return items;
    }

    public Integer getItemQuantity(String code) {
        Pair<Product, Integer> item = items.get(code);
        return item != null ? item.getValue() : 0;
    }

    public Integer getItemPrice(String code) {
        Pair<Product, Integer> item = items.get(code);
        return item != null ? item.getKey().getPrice() : 0;
    }

    // setter
    public void setItems(Map<String, Pair<Product, Integer>> items) {
        this.items = items;
    }

    // validator
    public boolean canBuyProduct(Product product) {
        return getItemQuantity(product.getCode()) > 0;
    }

    /* remove item from store
        1. Menambah jumlah produk di store sebanyak quantity
        2. Jika produk belum ada di store, tambahkan produk baru dengan jumlah sebanyak quantity
    */
    public void addItem(String cardName, Product product, int quantity) {
        Pair<Product, Integer> existingItem = items.get(cardName);
        if (existingItem == null) {
            items.put(cardName, new Pair<>(product, quantity));
        } else {
            items.put(cardName, new Pair<>(product, existingItem.getValue() + quantity));
        }
    }

    /* remove item from store
        1. Mengurangi jumlah produk di store sebanyak quantity
        2. Jika quantitynya menjadi 0 maka dihapus dari store
        3. Tidak akan terjadi apa apa jika produk tidak ada di store
    */
    public void removeItem(String cardName, int quantity) {
        Pair<Product, Integer> existingItem = items.get(cardName);
        if (existingItem != null) {
            int currentQuantity = existingItem.getValue();
            if (currentQuantity > quantity) {
                items.put(cardName, new Pair<>(existingItem.getKey(), currentQuantity - quantity));
            } else {
                items.remove(cardName);
            }
        }
    }

    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Map.Entry<String, Pair<Product, Integer>> entry : items.entrySet()) {
            String productCode = entry.getKey();
            String productName = entry.getValue().getKey().getCardName();
            int quantity = entry.getValue().getValue();
            int price = entry.getValue().getKey().getPrice();
            System.out.println(productCode + " " + productName + ": " + price + " (" + quantity + ")");
        }
    }
}
