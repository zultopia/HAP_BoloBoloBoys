package com.hap.hap_boloboloboys.lib.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.hap.hap_boloboloboys.lib.person.Person;
import com.hap.hap_boloboloboys.lib.store.Store;

public class LoadTest {

    @Test
    void testLoadGameState_FileNotFound() {
        assertThrows(Exception.class, () -> Load.loadGameState("wrongfolder"));
    }

    @Test
    void testLoadGameState_CompleteFile() throws Exception {
        Load.loadGameState("game1");
        assertEquals(1, Load.getCurrentTurn());
        Map<String, Integer> expectedShopItems = new HashMap<>();
        expectedShopItems.put("SIRIP_HIU", 5);
        expectedShopItems.put("SUSU", 2);
        expectedShopItems.put("DAGING_DOMBA", 3);
        expectedShopItems.put("DAGING_BERUANG", 1);
        expectedShopItems.put("DAGING_KUDA", 10);
        assertEquals(expectedShopItems, Load.getShopItems());

        Store store = Load.getStore();
        assertEquals(5, store.getItemQuantity("SIRIP_HIU"));
        assertEquals(2, store.getItemQuantity("SUSU"));
        assertEquals(3, store.getItemQuantity("DAGING_DOMBA"));
        assertEquals(1, store.getItemQuantity("DAGING_BERUANG"));
        assertEquals(10, store.getItemQuantity("DAGING_KUDA"));

    }

    @Test
    void testLoadPlayer_FileNotFound() {
        assertThrows(Exception.class, () -> Load.loadPlayer("wrongfolder", "player1"));
    }

    @Test
    void testLoadPlayer_Success() throws Exception {
        Load.loadPlayer("game1", "player1");
        assertEquals(500, Load.getWealth());
        assertEquals(39, Load.getCurrentSizeInventory());
        assertEquals(6, Load.getCurrentSizeDeck());
        Map<String, String> expectedDeck = Map.of("A01", "BERUANG", "A02", "BIJI_JAGUNG", "A03", "DAGING_KUDA", "A04", "ACCELERATE", "A05", "DELAY", "A06", "PROTECT");
        assertEquals(expectedDeck, Load.getDeck());
        assertEquals(4, Load.getCardLadangCount());
        Map<String, List<String>> expectedContent = Map.of("A01", List.of("DOMBA", "5", "3", "ACCELERATE", "DELAY", "PROTECT"), "A02", List.of("BIJI_JAGUNG", "5", "0"), "A03", List.of("SIRIP_HIU", "5", "0"), "A04", List.of("AYAM", "5", "0"));
        for (Map.Entry<String, List<String>> entry : expectedContent.entrySet()) {
            String key = entry.getKey();
            List<String> expectedValue = entry.getValue();
            List<String> actualValue = Load.getContent().get(key);
            assertEquals(expectedValue, actualValue);
        }
        Load.loadPlayer("game1", "player1");
        Person player = Load.getPerson("player1");
        assertEquals("player1", player.getName());
    }

    @Test
    void testGetIdxFromLocation() {
        assertEquals(1, Load.getIdxFromLocation("A01"));
        assertEquals(0, Load.getIdxFromLocation(""));
    }

}
