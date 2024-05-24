package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.Test;

public class CreatureTest {
    @Test
    public void testCreature() {
        Creature creature = new Herbivore("DOMBA");
        assertEquals("DOMBA", creature.getCode());
        assertEquals("Domba", creature.getCardName());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "HIU_DARAT", "SAPI", "DOMBA", "KUDA", 
        "AYAM", "BERUANG", "BIJI_JAGUNG", 
        "BIJI_LABU", "BIJI_STROBERI"
    })
    void testSetCreatureDetails(String code) {
        Creature creature = null;
        switch (code) {
            case "HIU_DARAT":
                creature = new Carnivore("HIU_DARAT");
                assertEquals("Hiu Darat", creature.getCardName());
                assertEquals(20, creature.getHarvestTarget());
                break;
                case "SAPI":
                creature = new Herbivore("SAPI");
                assertEquals("Sapi", creature.getCardName());
                assertEquals(10, creature.getHarvestTarget());
                break;
                case "DOMBA":
                creature = new Herbivore("DOMBA");
                assertEquals("Domba", creature.getCardName());
                assertEquals(12, creature.getHarvestTarget());
                break;
                case "KUDA":
                creature = new Herbivore("KUDA");
                assertEquals("Kuda", creature.getCardName());
                assertEquals(14, creature.getHarvestTarget());
                break;
                case "AYAM":
                creature = new Omnivore("AYAM");
                assertEquals("Ayam", creature.getCardName());
                assertEquals(5, creature.getHarvestTarget());
                break;
                case "BERUANG":
                creature = new Omnivore("BERUANG");
                assertEquals("Beruang", creature.getCardName());
                assertEquals(25, creature.getHarvestTarget());
                break;
                case "BIJI_JAGUNG":
                creature = new Plant("BIJI_JAGUNG");
                assertEquals("Biji Jagung", creature.getCardName());
                assertEquals(3, creature.getHarvestTarget());
                break;
                case "BIJI_LABU":
                creature = new Plant("BIJI_LABU");
                assertEquals("Biji Labu", creature.getCardName());
                assertEquals(5, creature.getHarvestTarget());
                break;
                case "BIJI_STROBERI":
                creature = new Plant("BIJI_STROBERI");
                assertEquals("Biji Stroberi", creature.getCardName());
                assertEquals(4, creature.getHarvestTarget());
                break;
        }
    }

    @Test
    public void testGetHarvestTarget() {
        Creature creature = new Herbivore("KUDA");
        assertEquals(14, creature.getHarvestTarget());
    }

    @Test
    public void testUseEffect() {
        Creature creature = new Plant("BIJI_LABU");
        creature.useEffect(0);
        assertEquals(1, creature.getEffectValue(0));
        creature.useEffect(-1);
    }

    @Test
    public void testGetEffectValue() {
        Creature creature = new Plant("BIJI_STROBERI");
        creature.useEffect(0);
        assertEquals(1, creature.getEffectValue(0));
        assertEquals(0, creature.getEffectValue(100));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testGetEffectName(int effectIndex) {
        Creature creature = new Herbivore("SAPI");
        String effectName = creature.getEffectName(effectIndex);
        switch (effectIndex) {
            case 0:
                assertEquals("Accelerate", effectName);
                break;
            case 1:
                assertEquals("Delay", effectName);
                break;
            case 2:
                assertEquals("Instant Harvest", effectName);
                break;
            case 3:
                assertEquals("Destroy", effectName);
                break;
            case 4:
                assertEquals("Protect", effectName);
                break;
            case 5:
                assertEquals("Trap", effectName);
                break;
            default:
                assertEquals("No such effect", effectName);
                break;
        }
    }

    @Test
    public void testCanHarvest() {
        Creature creature = new Carnivore("HIU_DARAT");
        String canHarvest = "";
        if (!creature.canHarvest()) {
            canHarvest = "FALSE";
        }

        assertEquals("FALSE", canHarvest);
    }

    @Test
    public void testHarvest() {
        Animal creature = new Carnivore("HIU_DARAT");
        creature.setWeight(200);
        Product produk = creature.harvest();
        assertEquals("SIRIP_HIU", produk.getCode());

        Animal gagal = new Carnivore("HIU_DARAT");
        gagal.setWeight(0);
        Product gagalProduct = gagal.harvest();
        assertEquals(null, gagalProduct);
    }

    @Test
    public void testSetHarvesttarget() {
        Animal creature = new Carnivore("HIU_DARAT");
        creature.setHarvestTarget(200);
        assertEquals(200, creature.getHarvestTarget());
    }
}
