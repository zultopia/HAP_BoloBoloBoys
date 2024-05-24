package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DelayTest {

    @Test
    public void testDelay() {
        Delay delay = new Delay();
        assertEquals("DELAY", delay.getCode());
        assertEquals("Delay", delay.getCardName());
    }
    
    @Test
    public void testApplyEffect() {
        Delay accelerate = new Delay();
        Animal animal = new Omnivore("BERUANG");
        animal.setWeight(10);
        animal.useEffect(1);
        accelerate.applyEffect(animal);
        assertEquals(5, animal.getWeight());
        
        Plant plant = new Plant("BIJI_JAGUNG");
        plant.setAge(2);
        plant.useEffect(1);
        accelerate.applyEffect(plant);
        assertEquals(0, plant.getAge());
    } 
}
