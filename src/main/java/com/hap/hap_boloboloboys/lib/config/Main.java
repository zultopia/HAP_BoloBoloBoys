package com.hap.hap_boloboloboys.lib.config;

// run di root directory gitHub\HAP_BoloBoloBoys
// javac -d target src/main/java/com/hap/hap_boloboloboys/lib/config/*.java
// java -cp target com.hap.hap_boloboloboys.lib.config.Main

public class Main {
    public static void main(String[] args) {
        try {
            Load.loadGameState("game1");
            System.out.println("Current Turn: " + Load.getCurrentTurn());
            System.out.println("Shop Items: " + Load.getShopItems());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
