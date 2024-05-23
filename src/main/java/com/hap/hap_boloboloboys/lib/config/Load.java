package com.hap.hap_boloboloboys.lib.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.hap.hap_boloboloboys.lib.store.Store;
import com.hap.hap_boloboloboys.lib.card.Product;

public class Load {
    // game state
    private static int currentTurn; // CURRENT_TURN
    private static Map<String, Integer> shopItems = new HashMap<>(); // <ITEM, JUMLAH>

    // player
    private static int wealth; // JUMLAH_GULDEN
    private static int currentSizeInventory; // JUMLAH_DECK
    private static int currentSizeDeck; // JUMLAH_DECK_AKTIF
    private static Map<String, String> deck = new HashMap<>(); // <LOKASI, KARTU>
    private static int cardLadangCount; // JUMLAH_KARTU_LADANG
    private static Map<String, List<String>> content = new HashMap<>(); // <LOKASI, [KARTU, UMUR/BERAT, JUMLAH_ITEM_AKTIF(J), ITEM_1, .., ITEM_J]>

    // getter
    public static int getCurrentTurn() {
        return currentTurn;
    }

    public static Map<String, Integer> getShopItems() {
        return shopItems;
    }

    public static int getWealth() {
        return wealth;
    }

    public static int getCurrentSizeInventory() {
        return currentSizeInventory;
    }

    public static int getCurrentSizeDeck() {
        return currentSizeDeck;
    }

    public static Map<String, String> getDeck() {
        return deck;
    }

    public static int getCardLadangCount() {
        return cardLadangCount;
    }

    public static Map<String, List<String>> getContent() {
        return content;
    }

    // setter
    public static void setCurrentTurn(int currentTurn) {
        Load.currentTurn = currentTurn;
    }

    public static void setShopItems(Map<String, Integer> shopItems) {
        Load.shopItems = shopItems;
    }

    public static void setWealth(int wealth) {
        Load.wealth = wealth;
    }

    public static void setCurrentSizeInventory(int currentSizeInventory) {
        Load.currentSizeInventory = currentSizeInventory;
    }

    public static void setCurrentSizeDeck(int currentSizeDeck) {
        Load.currentSizeDeck = currentSizeDeck;
    }

    public static void setDeck(Map<String, String> deck) {
        Load.deck = deck;
    }

    public static void setCardLadangCount(int cardLadangCount) {
        Load.cardLadangCount = cardLadangCount;
    }

    public static void setContent(Map<String, List<String>> content) {
        Load.content = content;
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
                int countItem = Integer.parseInt(scanner.nextLine().trim());
                shopItems.clear();
                for (int i = 0; i < countItem; i++) {
                    if (scanner.hasNext()) {
                        String itemName = scanner.next();
                        if (scanner.hasNextInt()) {
                            int itemQuantity = scanner.nextInt();
                            shopItems.put(itemName, itemQuantity);
                        } else {
                            scanner.close();
                            throw new Exception(
                                    "Error: Jumlah Item not found for item " + itemName + " in file gamestate.txt!");
                        }
                        if (scanner.hasNextLine()) {
                            scanner.nextLine();
                        }
                    } else {
                        scanner.close();
                        throw new Exception("Error: Nama Item ke " + (i + 1) + " not found in file gamestate.txt!");
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

    // get store
    public static Store getStore() {
        Store store = new Store();
        for (Map.Entry<String, Integer> entry : shopItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            Product product = new Product(itemName); 
            store.addItem(itemName, product, quantity); 
        }
        return store;
    }

    // load player
    public static void loadPlayer(String folderPath) throws Exception {
        String filename = "config/" + folderPath + "/player1.txt";
        File file = new File(filename);

        if (!file.exists()) {
            throw new Exception("File not found: " + filename + "!");
        }

        Scanner scanner = new Scanner(new FileInputStream(file));
        if (scanner.hasNextLine()) {
            setWealth(Integer.parseInt(scanner.nextLine().trim()));
        } else {
            scanner.close();
            throw new Exception("Error: Jumlah Gulden not found in file " + filename + "!");
        }

        if (scanner.hasNextLine()) {
            setCurrentSizeInventory(Integer.parseInt(scanner.nextLine().trim()));
        } else {
            scanner.close();
            throw new Exception("Error: Jumlah Deck not found in file " + filename + "!");
        }

        if (scanner.hasNextLine()) {
            setCurrentSizeDeck(Integer.parseInt(scanner.nextLine().trim()));
            deck.clear();
            for (int i = 0; i < getCurrentSizeDeck(); i++) {
                if (scanner.hasNext()) {
                    String lokasi = scanner.next();
                    if (scanner.hasNext()) {
                        String kartu = scanner.next();
                        deck.put(lokasi, kartu);
                    } else {
                        scanner.close();
                        throw new Exception("Error: Kartu Deck Aktif not found for lokasi " + lokasi + " in file "
                                + filename + "!");
                    }
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                } else {
                    scanner.close();
                    throw new Exception("Error: Lokasi Kartu dan Kartu Deck Aktif not found in file " + filename + "!");
                }
            }
        } else {
            scanner.close();
            throw new Exception("Error: Jumlah Deck Aktif not found in file " + filename + "!");
        }

        if (scanner.hasNextLine()) {
            Load.setCardLadangCount(Integer.parseInt(scanner.nextLine().trim()));
            content.clear();
            for (int i = 0; i < Load.getCardLadangCount(); i++) {
                if (scanner.hasNext()) {
                    List<String> items = new ArrayList<>();
                    String lokasiKartu = scanner.next();
                    if (scanner.hasNext()) {
                        String kartuLadang = scanner.next();
                        items.add(kartuLadang);
                    } else {
                        scanner.close();
                        throw new Exception(
                                "Error: Kartu Ladang ke " + (i + 1) + "  not found in file " + filename + "!");
                    }
                    if (scanner.hasNext()) {
                        String umurBerat = scanner.next();
                        items.add(umurBerat);
                    } else {
                        scanner.close();
                        throw new Exception(
                                "Error: Umur/Berat Kartu ke " + (i + 1) + "  not found in file " + filename + "!");
                    }
                    if (scanner.hasNext()) {
                        int jumlahItemAktif = scanner.nextInt();
                        items.add(String.valueOf(jumlahItemAktif));
                        for (int j = 0; j < jumlahItemAktif; j++) {
                            if (scanner.hasNext()) {
                                String itemName = scanner.next();
                                items.add(itemName);

                            } else {
                                scanner.close();
                                throw new Exception(
                                        "Error: Item Kartu ke " + (j + 1) + "  not found in file " + filename
                                                + "!");
                            }
                        }
                    } else {
                        scanner.close();
                        throw new Exception(
                                "Error: Jumlah Item Aktif Kartu ke " + (i + 1) + "  not found in file " + filename
                                        + "!");
                    }
                    content.put(lokasiKartu, items);
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                } else {
                    scanner.close();
                    throw new Exception("Error: Lokasi Kartu ke " + (i + 1) + " not found in file " + filename + "!");
                }
            }
        } else {
            scanner.close();
            throw new Exception("Error: Jumlah Kartu Ladang not found in file " + filename + "!");
        }

        scanner.close();
        System.out.println("Player state loaded successfully from " + filename + "!");
    }
}