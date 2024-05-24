package com.hap.hap_boloboloboys.lib.config;



// run di root directory gitHub\HAP_BoloBoloBoys
// javac -d target src/main/java/com/hap/hap_boloboloboys/lib/util/Pair.java src/main/java/com/hap/hap_boloboloboys/lib/card/*.java src/main/java/com/hap/hap_boloboloboys/lib/store/*.java src/main/java/com/hap/hap_boloboloboys/lib/config/*.java src/main/java/com/hap/hap_boloboloboys/lib/config/Main.java src/main/java/com/hap/hap_boloboloboys/lib/person/*.java src/main/java/com/hap/hap_boloboloboys/lib/field/*.java
// java -cp target com.hap.hap_boloboloboys.lib.config.Main

import com.hap.hap_boloboloboys.lib.person.*;
import com.hap.hap_boloboloboys.lib.store.Store;

public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = "game1"; 

            Load.loadGameState(folderPath);
            System.out.println("Current Turn: " + Load.getCurrentTurn());
            System.out.println("Shop Items: " + Load.getShopItems());

            Store store = Load.getStore();
            System.out.println();
            store.displayInventory();
            System.out.println();

            Load.loadPlayer(folderPath, "player1");
            System.out.println("Player Wealth: " + Load.getWealth());
            System.out.println("Current Size Inventory: " + Load.getCurrentSizeInventory());
            System.out.println("Current Size Deck: " + Load.getCurrentSizeDeck());
            System.out.println("Deck: " + Load.getDeck());
            System.out.println("Card Ladang Count: " + Load.getCardLadangCount());
            System.out.println("Content: " + Load.getContent());

            Person player = Load.getPerson("player1");
            System.out.println(player.getName());
            System.out.println(player.getWealth());

            Deck deck = player.getDeck();
            for (int i = 0; i < 6; i++) {
                if (deck.getCard(i) != null) {

                    System.out.println(deck.getCard(i).getCode());
                }
            }
            System.out.println(player.getDeck());
            System.out.println(player.getInventory());
            System.out.println(player.getLadang());

            Save.savePlayer("game1", player);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        if (ListCode.CARNIVORE.contains("HIU_DARAT")) {
            System.out.println("hehe");
        }
    }

}