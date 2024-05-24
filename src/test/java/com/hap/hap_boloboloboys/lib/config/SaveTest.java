package com.hap.hap_boloboloboys.lib.config;

import org.junit.jupiter.api.Test;

import com.hap.hap_boloboloboys.lib.person.InventoryException;
import com.hap.hap_boloboloboys.lib.person.Person;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveTest {

    private final String TEST_FOLDER = "test_folder";

    @Test
    void testSaveGameState() throws Exception {
        int currentTurn = 1;
        Map<String, Integer> shopItems = new HashMap<>();
        shopItems.put("SIRIP_HIU", 5);
        shopItems.put("SUSU", 2);
        shopItems.put("DAGING_DOMBA", 3);
        shopItems.put("DAGING_BERUANG", 1);
        shopItems.put("DAGING_KUDA", 10);

        try {
            Save.saveGameState(TEST_FOLDER, currentTurn, shopItems);
            File gameStateFile = new File("config/" + TEST_FOLDER + "/gamestate.txt");
            assertTrue(gameStateFile.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testSavePlayer() throws Exception {
        Load.loadPlayer("game1", "player1");
        Person player = Load.getPerson("player1");

        try {
            Save.savePlayer(TEST_FOLDER, player);
            File playerFile = new File("config/" + TEST_FOLDER + "/Player1.txt");
            assertTrue(playerFile.exists());

          } catch (IOException | InventoryException e) {
            e.printStackTrace();
        }
    }
}
