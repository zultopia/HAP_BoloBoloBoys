package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HerbivoreTest {
    @Test
    public void testHerbivore() {
        Herbivore herbivore = new Herbivore("SAPI");
        assertEquals("SAPI", herbivore.getCode());
        assertEquals(0, herbivore.getWeight());
        assertEquals("Sapi", herbivore.getCardName());
    }
    
    @Test
    public void testCanEat() {
        Herbivore herbivore = new Herbivore("SAPI");
        Product product = new Product("JAGUNG");
        String canEat = "";
        if (herbivore.canEat(product)) {
            canEat = "TRUE";
        }

        assertEquals("TRUE", canEat);
    }
}
