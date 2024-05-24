package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OmnivoreTest {
    @Test
    public void testOmnivore() {
        Omnivore omnivore = new Omnivore("BERUANG");
        assertEquals("BERUANG", omnivore.getCode());
        assertEquals(0, omnivore.getWeight());
        assertEquals("Beruang", omnivore.getCardName());
    }
    
    @Test
    public void testCanEat() {
        Omnivore omnivore = new Omnivore("BERUANG");
        Product product = new Product("DAGING_DOMBA");
        String canEat = "";
        if (omnivore.canEat(product)) {
            canEat = "TRUE";
        }

        assertEquals("TRUE", canEat);
    }
}
