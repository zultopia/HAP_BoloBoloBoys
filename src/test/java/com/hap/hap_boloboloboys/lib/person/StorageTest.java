package com.hap.hap_boloboloboys.lib.person;

import com.hap.hap_boloboloboys.lib.card.Animal;
import com.hap.hap_boloboloboys.lib.card.Card;
import com.hap.hap_boloboloboys.lib.card.Carnivore;
import com.hap.hap_boloboloboys.lib.card.Herbivore;
import com.hap.hap_boloboloboys.lib.card.Plant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    void testStorageFunctionality() {
        int capacity = 10;
        Storage storage = new Storage(capacity);
        storage.setCapacity(2);
        assertEquals(2, storage.getCapacity());
        try {
            Animal animal1 = new Carnivore("HIU_DARAT");
            Plant plant1 = new Plant("BIJI_LABU");
            storage.add(animal1);
            storage.add(plant1);

            assertThrows(Exception.class, () -> storage.add(animal1));
            
            assertTrue(storage.isFull());
            assertFalse(storage.isEmpty());
            assertEquals(2, storage.getSize());
            assertEquals(2, storage.getCapacity());

            Card retrievedCard = storage.getCard(0);
            assertEquals(animal1, retrievedCard);
            
            assertThrows(Exception.class, () -> storage.delete(100));
            storage.delete(0);
            assertEquals(1, storage.getSize());
            
            storage.delete(plant1);
            assertEquals(0, storage.getSize());
            assertTrue(storage.isEmpty());
            
            storage.add(new Herbivore("SAPI"));
            Card cardsapi = new Herbivore("SAPI");
            assertEquals("SAPI", storage.findCard(cardsapi).getCode());
            
            Card poppedCard = storage.pop(0);
            assertThrows(Exception.class, () -> storage.pop(8));
            assertEquals("SAPI", poppedCard.getCode());
            assertEquals(0, storage.getSize());
            
            Card[] cards = storage.getCards();
            storage.setCards(cards);
            assertEquals(null, storage.findCard(new Herbivore("SAPI")));
            
        } catch (InventoryException e) {
            e.printStackTrace();
        }
    }
}
