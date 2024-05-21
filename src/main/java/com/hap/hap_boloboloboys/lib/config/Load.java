package com.hap.hap_boloboloboys.lib.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Load {
    // game state
    private static int currentTurn;
    private static int itemCount;
    private static Map<String, Integer> shopItems = new HashMap<>();

    // getter
    public static int getCurrentTurn() {
        return currentTurn;
    }

    public static int getItemCount() {
        return currentTurn;
    }
    
    public static Map<String, Integer> getShopItems() {
        return shopItems;
    }
    
    // setter
    public static void setCurrentTurn(int currentTurn) {
        Load.currentTurn = currentTurn;
    }
    
    public static void setItemCount(int itemCount) {
        Load.itemCount = itemCount;
    }

    public static void setShopItems(Map<String, Integer> shopItems) {
        Load.shopItems = shopItems;
    }

    // load game state
    public static void loadGameState(String folderPath) throws Exception {
        String filename = "config/" + folderPath + "/gamestate.txt";
        File file = new File(filename);

        if (file.exists()) {
            Scanner scanner = new Scanner(new FileInputStream(file));
            if (scanner.hasNextLine()) {
                Load.setCurrentTurn(Integer.parseInt(scanner.nextLine().trim()));
            } else {
                scanner.close();
                throw new Exception("Error: Current Turn not found in file gamestate.txt!");
            }

            if (scanner.hasNextLine()) {
                setItemCount(Integer.parseInt(scanner.nextLine().trim()));
                shopItems.clear();
                for (int i = 0; i < Load.itemCount; i++) {
                    if (scanner.hasNext()) {
                        String itemName = scanner.next();
                        if (scanner.hasNextInt()) {
                            int itemQuantity = scanner.nextInt();
                            shopItems.put(itemName, itemQuantity);
                        } else {
                            scanner.close();
                            throw new Exception("Error: Jumlah Item not found for item " + itemName + " in file gamestate.txt!");
                        }
                        if (scanner.hasNextLine()) {
                            scanner.nextLine();
                        }
                    } else {
                        scanner.close();
                        throw new Exception("Error: Nama Item ke "  + (i + 1) + " not found in file gamestate.txt!");
                    }
                }
                System.out.println("Game state loaded successfully!");
            } else {
                scanner.close();
                throw new Exception("Error: Jumlah Item di Shop not found in file gamestate.txt!");
            }

            scanner.close();

        } else {
            throw new Exception("File not found: " + filename + "!");
        }
    }
}
