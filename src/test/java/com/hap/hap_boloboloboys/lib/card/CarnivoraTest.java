package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CarnivoraTest {
    @Test
    public void testCarnivore() {
        Carnivore carnivore = new Carnivore("HIU_DARAT");
        assertEquals("HIU_DARAT", carnivore.getCode());
        assertEquals(0, carnivore.getWeight());
        assertEquals("Hiu Darat", carnivore.getCardName());
    }
    
    @Test
    public void testCanEat() {
        Carnivore carnivore = new Carnivore("HIU_DARAT");
        Product product = new Product("DAGING_DOMBA");
        String canEat = "";
        if (carnivore.canEat(product)) {
            canEat = "TRUE";
        }

        assertEquals("TRUE", canEat);
    }

}
