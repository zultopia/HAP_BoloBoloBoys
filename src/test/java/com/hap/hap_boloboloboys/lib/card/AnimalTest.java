package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testAnimal() {
        Animal animal = new Carnivore("HIU_DARAT");
        assertEquals(0, animal.getWeight());
    }
    
    @Test
    public void testCanEat() {
        Animal animal = new Carnivore("HIU_DARAT");
        Product product = new Product("DAGING_DOMBA");
        String canEat = "";
        if (animal.canEat(product)) {
            canEat = "TRUE";
        }

        assertEquals("TRUE", canEat);
    }

    @Test
    public void testCanHarvest() {
        Animal animal = new Carnivore("HIU_DARAT");
        animal.setWeight(100);
        String canHarvest = "";
        if (animal.canHarvest()) {
            canHarvest = "TRUE";
        }
        
        assertEquals("TRUE", canHarvest);
    }
    
    @Test
    public void testGetWeight() {
        Animal animal = new Carnivore("HIU_DARAT");
        animal.setWeight(100);
        assertEquals(100, animal.getWeight());
    }
    
    @Test 
    public void testFeed() throws CardException {
        Animal animal = new Carnivore("HIU_DARAT");
        Product product = new Product("DAGING_KUDA");
        String gagal = "FALSE";
        animal.feed(product);
        
        assertEquals("FALSE", gagal);
    }

    @Test
    void testFeedThrowsCardException() {
        Animal animal = new Carnivore("HIU_DARAT");
        Product product = new Product("JAGUNG");
        assertThrows(CardException.class, () -> {
            animal.feed(product);
        });
    }
    
    @Test
    public void testSetWeight() {
        Animal animal = new Carnivore("HIU_DARAT");
        animal.setWeight(50);
        assertEquals(50, animal.getWeight());
    }
    
}
