package com.hap.hap_boloboloboys.lib.person;

import com.hap.hap_boloboloboys.lib.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(40, inventory.getCapacity());
        assertEquals(0, inventory.getCurrentSize());
    }

    @Test
    void testConstructorWithCapacity() {
        Inventory inv = new Inventory(20);
        assertEquals(20, inv.getCapacity());
        assertEquals(0, inv.getCurrentSize());
        
        Inventory invNegative = new Inventory(-1);
        assertEquals(40, invNegative.getCapacity());
        assertEquals(0, invNegative.getCurrentSize());
    }

    @Test
    void testInitiateFromScratch() {
        inventory.initiateFromScratch();
        assertEquals(40, inventory.getCurrentSize());

        inventory = new Inventory();
        inventory.initiateFromScratch(20);
        assertEquals(20, inventory.getCurrentSize());
    }

    @Test
    void testShuffleWithNeededCard() {
        inventory.initiateFromScratch();
        int neededCard = 5;
        Card[] shuffledCards = inventory.shuffle(neededCard);
        assertEquals(neededCard, shuffledCards.length);

        for (Card card : shuffledCards) {
            assertNotNull(card);
        }
    }

    @Test
    void testShuffleSingleCard() {
        inventory.initiateFromScratch();
        Card shuffledCard = inventory.shuffle();
        assertNotNull(shuffledCard);
    }

    @Test
    void testGetCapacity() {
        assertEquals(40, inventory.getCapacity());
    }

    @Test
    void testGetCurrentSize() {
        inventory.initiateFromScratch();
        assertEquals(40, inventory.getCurrentSize());
    }

    @Test
    void testGetCards() {
        inventory.initiateFromScratch();
        assertNotNull(inventory.getCards());
        assertEquals(40, inventory.getCards().length);
    }

    @Test
    void testGetSize() {
        inventory.initiateFromScratch();
        assertEquals(40, inventory.getSize());
    }

    @Test
    void testGetCard() {
        inventory.initiateFromScratch();
        try {
            Card card = inventory.getCard(0);
            assertNotNull(card);
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }

        assertThrows(InventoryException.class, () -> {
            inventory.getCard(-1);
        });

        assertThrows(InventoryException.class, () -> {
            inventory.getCard(100);
        });
    }

    @Test
    void testCalculateSize() {
        inventory.initiateFromScratch();
        assertEquals(40, inventory.calculateSize());
    }

    @Test
    void testIsFull() {
        inventory.initiateFromScratch();
        assertTrue(inventory.isFull());

        inventory = new Inventory(50);
        inventory.initiateFromScratch(40);
        assertFalse(inventory.isFull());
    }

    @Test
    void testIsEmpty() {
        assertTrue(inventory.isEmpty());

        inventory.initiateFromScratch(1);
        assertFalse(inventory.isEmpty());
    }

    @Test
    void testFindCard() {
        Card card = new Plant("BIJI_LABU");
        try {
            inventory.add(card);
            assertEquals(card, inventory.findCard(card));
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testAddCard() {
        Card card = new Plant("BIJI_LABU");
        try {
            inventory.add(card);
            assertEquals(1, inventory.getCurrentSize());
            assertEquals(card, inventory.getCard(0));
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }

        Inventory fullInventory = new Inventory(1);
        try {
            fullInventory.add(card);
            fullInventory.add(card);
            fail("Should have thrown InventoryException");
        } catch (InventoryException e) {
            assertEquals("Inventory penuh", e.getMessage());
        }
    }

    @Test
    void testDeleteCardByIndex() {
        inventory.initiateFromScratch();
        try {
            inventory.delete(0);
            assertEquals(39, inventory.getCurrentSize());
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }

        assertThrows(InventoryException.class, () -> {
            inventory.delete(-1);
        });

        assertThrows(InventoryException.class, () -> {
            inventory.delete(100);
        });
    }

    @Test
    void testDeleteCardByCard() {
        Card card = new Plant("BIJI_LABU");
        try {
            inventory.add(card);
            inventory.delete(card);
            assertNull(inventory.findCard(card));
            assertEquals(0, inventory.getCurrentSize());
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testPopCard() {
        Card card = new Plant("BIJI_LABU");
        try {
            inventory.add(card);
            Card poppedCard = inventory.pop(0);
            assertEquals(card, poppedCard);
            assertNull(inventory.getCard(0));
            assertEquals(0, inventory.getCurrentSize());
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }

        assertThrows(InventoryException.class, () -> {
            inventory.pop(-1);
        });

        assertThrows(InventoryException.class, () -> {
            inventory.pop(100);
        });
    }
}
