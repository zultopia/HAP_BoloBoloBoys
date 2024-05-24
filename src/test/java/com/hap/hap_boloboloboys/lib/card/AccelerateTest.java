package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccelerateTest {

    @Test
    public void testAccelerate() {
        Accelerate accelerate = new Accelerate();
        assertEquals("ACCELERATE", accelerate.getCode());
        assertEquals("Accelerate", accelerate.getCardName());
    }
    
    @Test
    public void testApplyEffect() {
        Accelerate accelerate = new Accelerate();
        Animal animal = new Omnivore("BERUANG");
        animal.useEffect(0);
        accelerate.applyEffect(animal);
        assertEquals(8, animal.getWeight());

        Plant plant = new Plant("BIJI_JAGUNG");
        plant.useEffect(0);
        accelerate.applyEffect(plant);
        assertEquals(2, plant.getAge());
    } 
}
