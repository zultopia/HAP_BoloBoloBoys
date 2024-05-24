package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LadangTest {
    private Ladang ladang;

    @BeforeEach
    void setUp() {
        ladang = new Ladang();
    }

    @Test
    void testConstructor() {
        assertNotNull(ladang);
        assertEquals(4, ladang.getLadangRow());
        assertEquals(5, ladang.getLadangColumn());
    }

    @Test
    void testGetPetak() {
        Petak petak = ladang.getPetak(0, 0);
        assertNotNull(petak);

        Petak invalidPetak = ladang.getPetak(-1, -1);
        assertNull(invalidPetak);

        invalidPetak = ladang.getPetak(4, 5);
        assertNull(invalidPetak);
    }

    @Test
    void testTakeCard() {
        Plant plant = new Plant("BIJI_LABU");
        try {
            ladang.plantKartu(0, 0, plant);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
        Card card = ladang.takeCard(0, 0);
        assertEquals(plant, card);
        assertNull(ladang.getPetak(0, 0).getKartu());

        Card invalidCard = ladang.takeCard(4, 5);
        assertNull(invalidCard);
    }

    @Test
    void testPlantKartu() {
        Plant plant = new Plant("BIJI_LABU");
        try {
            ladang.plantKartu(0, 0, plant);
            assertEquals(plant, ladang.getPetak(0, 0).getKartu());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        try {
            ladang.plantKartu(0, 0, new Protect());
        } catch (Exception e) {
            assertEquals("Kartu tidak bisa ditanam", e.getMessage());
        }
    }

    @Test
    void testHarvestKartu() {
        Plant plant = new Plant("BIJI_LABU");
        plant.setAge(5); 
        try {
            ladang.plantKartu(0, 0, plant);
            ladang.harvestKartu(0, 0);
            assertTrue(ladang.getPetak(0, 0).getKartu() instanceof Product);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        Animal animal = new Herbivore("DOMBA");
        animal.setWeight(50); 
        try {
            ladang.plantKartu(1, 1, animal);
            ladang.harvestKartu(1, 1);
            assertTrue(ladang.getPetak(1, 1).getKartu() instanceof Product);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        assertThrows(FieldException.class, () -> {
            ladang.harvestKartu(4, 5);
        });
    }

    @Test
    void testApplyItem() {
        Plant plant = new Plant("BIJI_LABU");
        Item item = new Protect();
        try {
            ladang.plantKartu(0, 0, plant);
            ladang.applyItem(0, 0, item);
      
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        assertThrows(FieldException.class, () -> {
            ladang.applyItem(4, 5, item);
        });
    }

    @Test
    void testResizeLadang() {
        ladang.resizeLadang(6, 7);
        assertEquals(6, ladang.getLadangRow());
        assertEquals(7, ladang.getLadangColumn());
    }

    @Test
    void testCountCard() {
        Plant plant1 = new Plant("BIJI_LABU");
        Plant plant2 = new Plant("BIJI_JAGUNG");
        try {
            ladang.plantKartu(0, 0, plant1);
            ladang.plantKartu(1, 1, plant2);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
        assertEquals(2, ladang.countCard());
    }

    @Test
    void testCetakInfo() {
      Animal animal = new Herbivore("DOMBA");
      animal.setWeight(50); 
      try {
          ladang.plantKartu(1, 1, animal);
          ladang.plantKartu(1, 1, new Accelerate());
          assertTrue(ladang.getPetak(1, 1).getKartu() instanceof Animal);
          ladang.plantKartu(2, 1, new Product("SIRIP_HIU"));
          ladang.plantKartu(2, 1, new Protect());
      } catch (Exception e) {
          fail("Exception should not be thrown");
      }

      ladang.cetakInfo();
    }

    @Test
    void testLoadPlant() {
      Animal animal = new Herbivore("DOMBA");
      animal.setWeight(50); 
      try {
          ladang.loadplantKartu(1, 1, animal);
          ladang.loadplantKartu(1, 1, new Accelerate());
          assertTrue(ladang.getPetak(1, 1).getKartu() instanceof Animal);
          ladang.loadplantKartu(2, 1, new Product("SIRIP_HIU"));
          ladang.loadplantKartu(2, 1, new Protect());
      } catch (Exception e) {
          fail("Exception should not be thrown");
      }

      ladang.cetakInfo();
    }
}
