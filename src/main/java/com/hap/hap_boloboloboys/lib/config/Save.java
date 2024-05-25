package com.hap.hap_boloboloboys.lib.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hap.hap_boloboloboys.lib.card.Animal;
import com.hap.hap_boloboloboys.lib.card.Card;
import com.hap.hap_boloboloboys.lib.card.Creature;
import com.hap.hap_boloboloboys.lib.card.Plant;
import com.hap.hap_boloboloboys.lib.field.*;
import com.hap.hap_boloboloboys.lib.person.*;

public class Save {
    // attribute instance for singleton pattern
    private static Save instance = null;

    // getInstance method for singleton pattern
    public static Save getInstance() {
        if (instance == null) {
            instance = new Save();
        }
        return instance;
    }
    
    // save game state
    public static void saveGameState(String folderPath, int currentTurn, Map<String, Integer> shopItems)
            throws IOException {
        String dirPath = "config/" + folderPath;
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filename = dirPath + "/gamestate.txt";
        FileWriter writer = new FileWriter(filename);

        writer.write(currentTurn + "\n");
        writer.write(shopItems.size() + "\n");

        for (Map.Entry<String, Integer> entry : shopItems.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue() + "\n");
        }

        writer.close();
        System.out.println("Game state saved successfully!");
    }

    // save player
    public static void savePlayer(String folderPath, Person player) throws IOException, InventoryException {
        String dirPath = "config/" + folderPath;
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filename = dirPath + "/" + player.getName() + ".txt";
        FileWriter writer = new FileWriter(filename);

        writer.write(player.getWealth() + "\n");
        writer.write(player.getInventory().getSize() + "\n");

        Deck deck = player.getDeck();
        int countCard = deck.calculateSize();
        writer.write(countCard + "\n");
        for (int i = 0; i < deck.getCapacity(); i++) {
            if (deck.getCard(i) != null) {
                writer.write((char) ('A' + i) + "01 ");
                writer.write(deck.getCard(i).getCode() + "\n");
            }
        }


        List<String> listEffect = Arrays.asList("ACCELERATE", "DELAY", "INSTANT_HARVEST", "DESTROY", "PROTECT", "TRAP");
        Ladang ladang = player.getLadang();
        writer.write(ladang.countCard() + "");
        for (int i = 0; i < ladang.getLadangRow(); i++) {
            for (int j = 0; j < ladang.getLadangColumn(); j++) {
                Petak petak = ladang.getPetak(i, j);
                if ((petak != null && !petak.isEmptyCard())) {
                    writer.write("\n");
                    writer.write((char) (j + 'A'));
                    writer.write(String.format("%02d", (i + 1)) + " ");
                    Card card = player.getLadang().takeCard(i, j);
                    writer.write(card.getCode() + " ");
                    if (card instanceof Plant) {
                        writer.write(((Plant) card).getAge() + " ");
                    } else if (card instanceof Animal) {
                        writer.write(((Animal) card).getWeight() + " ");
                    } else {
                        writer.write(0 + " " + 0);
                        continue;
                    }

                    int countItem = 0;
                    for (int k = 0; k < 6; k++) {
                        countItem += ((Creature) card).getEffectValue(k);
                    }
                    writer.write(countItem + " ");

                    for (int k = 0; k < 6; k++) {
                        if (((Creature) card).getEffectValue(k) != 0) {
                            writer.write(listEffect.get(k) + " ");
                        }
                    }
                }
            }
        }

        writer.close();
        System.out.println("Player saved successfully!");
    }

}
