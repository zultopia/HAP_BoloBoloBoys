package com.hap.hap_boloboloboys.lib.config;

// run di root directory gitHub\HAP_BoloBoloBoys
// javac -d target src/main/java/com/hap/hap_boloboloboys/lib/config/*.java
// java -cp target com.hap.hap_boloboloboys.lib.config.Main

public class Main {
    public static void main(String[] args) {
        try {
            Load.loadGameState("game1");
            System.out.println("Current Turn: " + Load.getCurrentTurn());
            System.out.println("Jumlah item di shop: " + Load.getItemCount());
            System.out.println("Shop Items: " + Load.getShopItems());

            Load.loadPlayer("game1");
            System.out.println("Jumlah Gulden: " + Load.getWealth());
            System.out.println("Jumlah Deck: " + Load.getCurrentSizeInventory());
            System.out.println("Jumlah Deck Aktif: " + Load.getCurrentSizeDeck());
            System.out.println("Deck: " + Load.getDeck());
            System.out.println("Jumlah Kartu Ladang: " + Load.getCardLadangCount());
            System.out.println("Content: " + Load.getContent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
