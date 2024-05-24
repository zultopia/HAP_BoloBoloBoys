package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class PlantTest {
    @Test
    public void testPlant() {
        Plant plant = new Plant("BIJI_JAGUNG");
        assertEquals(0, plant.getAge());
    }
    
    @Test
    public void testGrow() {
        Plant plant = new Plant("BIJI_JAGUNG");
        plant.grow();
        assertEquals(1, plant.getAge());
    }

    @Test
    public void testCanHarvest() {
        Plant plant = new Plant("BIJI_JAGUNG");
        plant.setAge(100);
        String canHarvest = "";
        if (plant.canHarvest()) {
            canHarvest = "TRUE";
        }
        
        assertEquals("TRUE", canHarvest);
    }
    
    @Test
    public void testGetAge() {
        Plant plant = new Plant("BIJI_JAGUNG");
        plant.setAge(100);
        assertEquals(100, plant.getAge());
    }
    
    @Test
    public void testSetAge() {
        Plant plant = new Plant("BIJI_JAGUNG");
        plant.setAge(50);
        assertEquals(50, plant.getAge());
    }

    @Test
    void testCanHarvest_False() {
        Plant plant = new Plant("BIJI_JAGUNG");
        assertFalse(plant.canHarvest());
    }
    
}
