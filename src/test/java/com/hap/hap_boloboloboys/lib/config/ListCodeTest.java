package com.hap.hap_boloboloboys.lib.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class ListCodeTest {

    @Test
    public void testItemSetInitialization() {
        Set<String> expectedItems = Set.of("ACCELERATE", "DELAY", "INSTANT_HARVEST", "DESTROY", "PROTECT", "TRAP", "LAYOUT");
        assertEquals(expectedItems, ListCode.ITEM);
    }

    @Test
    public void testProductSetInitialization() {
        Set<String> expectedProducts = Set.of("SIRIP_HIU", "SUSU", "DAGING_DOMBA", "DAGING_KUDA", "TELUR", "DAGING_BERUANG", "JAGUNG", "LABU", "STROBERI");
        assertEquals(expectedProducts, ListCode.PRODUCT);
    }

    @Test
    public void testPlantSetInitialization() {
        Set<String> expectedPlants = Set.of("BIJI_JAGUNG", "BIJI_LABU", "BIJI_STROBERI");
        assertEquals(expectedPlants, ListCode.PLANT);
    }

    @Test
    public void testCarnivoreSetInitialization() {
        Set<String> expectedCarnivores = Set.of("HIU_DARAT");
        assertEquals(expectedCarnivores, ListCode.CARNIVORE);
    }

    @Test
    public void testHerbivoreSetInitialization() {
        Set<String> expectedHerbivores = Set.of("SAPI", "DOMBA", "KUDA");
        assertEquals(expectedHerbivores, ListCode.HERBIVORE);
    }

    @Test
    public void testOmnivoreSetInitialization() {
        Set<String> expectedOmnivores = Set.of("AYAM", "BERUANG");
        assertEquals(expectedOmnivores, ListCode.OMNIVORE);
    }
}
