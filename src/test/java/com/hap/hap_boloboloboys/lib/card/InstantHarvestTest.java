package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InstantHarvestTest {

    @Test
    public void testInstantHarvest() {
        InstantHarvest instantHarvest = new InstantHarvest();
        assertEquals("INSTANT_HARVEST", instantHarvest.getCode());
        assertEquals("Instant Harvest", instantHarvest.getCardName());
    }

    @Test
    public void testApplyEffect() {
        InstantHarvest instantHarvest = new InstantHarvest();
        Animal animal = new Omnivore("AYAM");
        animal.useEffect(5);
        instantHarvest.applyEffect(animal);
        assertEquals(1, animal.getEffectValue(2));

        Plant plant = new Plant("BIJI_JAGUNG");
        plant.useEffect(5);
        instantHarvest.applyEffect(plant);
        assertEquals(1, plant.getEffectValue(2));
    }
}
