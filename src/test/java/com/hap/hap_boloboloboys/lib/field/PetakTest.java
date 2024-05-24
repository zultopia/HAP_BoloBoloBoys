package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetakTest {

    private Petak petak;
    private Card plantCard;
    private Card animalCard;
    private Card productCard;

    @BeforeEach
    public void setUp() {
        petak = new Petak();
        plantCard = new Plant("BIJI_JAGUNG");
        animalCard = new Herbivore("SAPI");
        productCard = new Product("SUSU");
    }

    @Test
    public void testIsEmptyCardWhenEmpty() {
        assertTrue(petak.isEmptyCard());
    }

    @Test
    public void testIsEmptyCardWhenNotEmpty() {
        petak.setKartu(plantCard);
        assertFalse(petak.isEmptyCard());
    }

    @Test
    public void testGetKartuWhenEmpty() {
        assertNull(petak.getKartu());
    }

    @Test
    public void testGetKartuWhenNotEmpty() {
        petak.setKartu(plantCard);
        assertEquals(plantCard, petak.getKartu());
    }

    @Test
    public void testSetKartu() {
        petak.setKartu(plantCard);
        assertEquals(plantCard, petak.getKartu());
    }

    @Test
    public void testCetakInfoWithPlant() {
        petak.setKartu(plantCard);
        ((Plant)plantCard).setAge(2);
        assertDoesNotThrow(() -> petak.cetakInfo());
    }

    @Test
    public void testCetakInfoWithAnimal() {
        petak.setKartu(animalCard);
        ((Animal)animalCard).setWeight(3);  
        assertDoesNotThrow(() -> petak.cetakInfo());
    }

    @Test
    public void testCetakInfoWithProduct() {
        petak.setKartu(productCard);
        assertDoesNotThrow(() -> petak.cetakInfo());
    }

    @Test
    public void testCetakInfoWithEmptyPetak() {
        assertThrows(FieldException.class, () -> petak.cetakInfo());
    }

    // @Test
    // public void testCetakInfoWithInvalidCard() {
    //     Card invalidCard = new Card("INVALID", "Invalid") {};  // Creating an anonymous subclass of Card for testing
    //     petak.setKartu(invalidCard);
    //     assertThrows(FieldException.class, () -> petak.cetakInfo());
    // }
}
