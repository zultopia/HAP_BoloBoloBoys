package com.hap.hap_boloboloboys.lib.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Save {

    // save game state
    public static void saveGameState(String folderPath, int currentTurn, Map<String, Integer> shopItems) throws IOException {
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
    public static void savePlayer(String folderPath) throws IOException {
        String dirPath = "config/" + folderPath;
        File dir = new File(dirPath);
        
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filename = dirPath + "/player.txt";
        FileWriter writer = new FileWriter(filename);

        // TODO: write player into file

        writer.close();
        System.out.println("Player saved successfully!");
    }

}
