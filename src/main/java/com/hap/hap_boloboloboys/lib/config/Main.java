// package com.hap.hap_boloboloboys.lib.config;

// // run di root directory gitHub\HAP_BoloBoloBoys
// // javac -d target src/main/java/com/hap/hap_boloboloboys/lib/util/Pair.java src/main/java/com/hap/hap_boloboloboys/lib/card/*.java src/main/java/com/hap/hap_boloboloboys/lib/store/*.java src/main/java/com/hap/hap_boloboloboys/lib/config/*.java src/main/java/com/hap/hap_boloboloboys/lib/config/Main.java
// // java -cp target com.hap.hap_boloboloboys.lib.config.Main

// import com.hap.hap_boloboloboys.lib.store.Store;

// public class Main {
//     public static void main(String[] args) {
//         try {
//             String folderPath = "game1"; 

//             Load.loadGameState(folderPath);
//             System.out.println("Current Turn: " + Load.getCurrentTurn());
//             System.out.println("Shop Items: " + Load.getShopItems());

//             Store store = Load.getStore();
//             System.out.println();
//             store.displayInventory();
//             System.out.println();

//             Load.loadPlayer(folderPath);
//             System.out.println("Player Wealth: " + Load.getWealth());
//             System.out.println("Current Size Inventory: " + Load.getCurrentSizeInventory());
//             System.out.println("Current Size Deck: " + Load.getCurrentSizeDeck());
//             System.out.println("Deck: " + Load.getDeck());
//             System.out.println("Card Ladang Count: " + Load.getCardLadangCount());
//             System.out.println("Content: " + Load.getContent());

//         } catch (Exception e) {
//             System.err.println("An error occurred: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
// }
