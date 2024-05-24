package com.hap.hap_boloboloboys.lib.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hap.hap_boloboloboys.lib.card.Product;
import com.hap.hap_boloboloboys.lib.util.Pair;

public class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
    }

    @Test
    void testAddItem_NewProduct() {
        Product product = new Product("SIRIP_HIU");
        int quantity = 5;
        store.addItem("SIRIP_HIU", product, quantity);
        assertEquals(quantity, store.getItemQuantity("SIRIP_HIU"));
        assertEquals(0, store.getItemQuantity("BEN"));
        assertEquals(500, store.getItemPrice("SIRIP_HIU"));
        assertEquals(0, store.getItemPrice("JUL"));
    }

    @Test
    void testAddItem_ExistingProduct() {
        Product product = new Product("SUSU");
        int initialQuantity = 3;
        int additionalQuantity = 2;
        store.addItem("SUSU", product, initialQuantity);

        store.addItem("SUSU", product, additionalQuantity);
        assertEquals(initialQuantity + additionalQuantity, store.getItemQuantity("SUSU"));
    }

    @Test
    void testRemoveItem_ExistingProduct() {
        Product product = new Product("SUSU");
        int initialQuantity = 5;
        store.addItem("SUSU", product, initialQuantity);
        int quantityToRemove = 3;

        store.removeItem("SUSU", quantityToRemove);
        assertEquals(initialQuantity - quantityToRemove, store.getItemQuantity("SUSU"));
        store.removeItem("BEN", quantityToRemove);
        assertEquals(0, store.getItemQuantity("BEN"));
    }

    @Test
    void testRemoveItem_AllProduct() {
        Product product = new Product("SIRIP_HIU");
        int initialQuantity = 1;
        store.addItem("SIRIP_HIU", product, initialQuantity);
        store.removeItem("SIRIP_HIU", initialQuantity);
        assertFalse(store.getItems().containsKey("SIRIP_HIU"));
    }

    @Test
    void testDisplayInventory() {
        Map<String, Pair<Product, Integer>> items = new HashMap<>();
        items.put("SIRIP_HIU", new Pair<>(new Product("SIRIP_HIU"), 2));
        items.put("SUSU", new Pair<>(new Product("SUSU"), 3));
        store.setItems(items);
        store.displayInventory();
    }

    @Test
    void testCanBuy() {
        Product product = new Product("SIRIP_HIU");
        int initialQuantity = 1;
        store.addItem("SIRIP_HIU", product, initialQuantity);
        store.addItem("SUSU", product, 0);
        assertTrue(store.canBuyProduct(product));
        Product hayya = new Product("SUSU");
        assertFalse(store.canBuyProduct(hayya));
    }
}
