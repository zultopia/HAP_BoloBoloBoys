package com.hap.hap_boloboloboys.lib.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hap.hap_boloboloboys.lib.store.Store;
import com.hap.hap_boloboloboys.lib.card.*;
import com.hap.hap_boloboloboys.lib.field.*;
import com.hap.hap_boloboloboys.lib.person.*;
// import com.hap.hap_boloboloboys.lib.

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
    private static Map<String, List<String>> content = new HashMap<>(); // <LOKASI, [KARTU, UMUR/BERAT,
                                                                        // JUMLAH_ITEM_AKTIF(J), ITEM_1, .., ITEM_J]>
    // attribute instance for singleton pattern
    private static Load instance = null;                                                    
    // getter
    public static Load getInstance() {
        if (instance == null) {
            instance = new Load();
        }
        return instance;
    }

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

    public static void setWealth(int wealth) {
        Load.wealth = wealth;
    }

    public static void setCurrentSizeInventory(int currentSizeInventory) {
        Load.currentSizeInventory = currentSizeInventory;
    }

    public static void setCurrentSizeDeck(int currentSizeDeck) {
        Load.currentSizeDeck = currentSizeDeck;
    }

    public static void setCardLadangCount(int cardLadangCount) {
        Load.cardLadangCount = cardLadangCount;
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
        for (Map.Entry<String, Integer> entry : Load.shopItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            Product product = new Product(itemName);
            store.addItem(itemName, product, quantity);
        }
        return store;
    }

    // load player
    public static void loadPlayer(String folderPath, String player) throws Exception {
        String filename = "config/" + folderPath + "/" + player + ".txt";
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

    // get person
    public static Person getPerson(String name) throws Exception {
        Person player = new Person(name);
        player.setWealth(Load.wealth);

        Inventory inventory = new Inventory();
        inventory.setCurrentSize(Load.getCurrentSizeInventory());
        inventory.initiateFromScratch(Load.getCurrentSizeInventory());
        player.setInventory(inventory);

        Deck deck = loadDeck();
        player.setDeck(deck);
        
        Ladang ladang = loadLadang();
        player.setLadang(ladang);

        return player;
    }

    public static int getIdxFromLocation(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String numberStr = matcher.group();
            return Integer.parseInt(numberStr);
        }
        return 0;
    }

    public static Deck loadDeck() throws InventoryException {
        Deck deck = new Deck(6);
        for (Map.Entry<String, String> entry : Load.deck.entrySet()) {
            String code = entry.getValue();
            Card card = null;
            if (ListCode.ITEM.contains(code)) {
                switch (code) {
                    case "ACCELERATE":
                        card = new Accelerate();
                        break;

                    case "DELAY":
                        card = new Delay();
                        break;

                    case "INSTANT_HARVEST":
                        card = new InstantHarvest();
                        break;

                    case "DESTROY":
                        card = new Destroy();
                        break;

                    case "PROTECT":
                        card = new Protect();
                        break;

                    case "Trap":
                        card = new Trap();
                        break;
                }
            } else if (ListCode.PRODUCT.contains(code)) {
                card = new Product(code);
            } else if (ListCode.PLANT.contains(code)) {
                card = new Plant(code);
            } else if (ListCode.CARNIVORE.contains(code)) {
                card = new Carnivore(code);
            } else if (ListCode.HERBIVORE.contains(code)) {
                card = new Herbivore(code);
            } else if (ListCode.OMNIVORE.contains(code)) {
                card = new Omnivore(code);
            } else {
                card = null;
            }

            if (card != null) {
                String loc = entry.getKey();
                try {
                    deck.putToDeck(card, loc.charAt(0) - 'A');
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return deck;
    }

    public static Ladang loadLadang() throws Exception {
        List<String> listEffect = Arrays.asList("ACCELERATE", "DELAY", "INSTANT_HARVEST", "DESTROY", "PROTECT", "TRAP");
        Ladang ladang = new Ladang();
        for (Map.Entry<String, List<String>> entry : Load.content.entrySet()) {
            String loc = entry.getKey();
            List<String> content = entry.getValue();
            String code = content.get(0);
            int props = Integer.parseInt(content.get(1)); // BERAT/UMUR

            Card creature = null; // Initialize creature as null
            if (ListCode.PLANT.contains(code)) {
                Plant plant = new Plant(code);
                plant.setAge(props);
                creature = plant;
            } else if (ListCode.CARNIVORE.contains(code)) {
                Carnivore carnivore = new Carnivore(code);
                carnivore.setWeight(props);
                creature = carnivore;
            } else if (ListCode.HERBIVORE.contains(code)) {
                Herbivore herbivore = new Herbivore(code);
                herbivore.setWeight(props);
                creature = herbivore;
            } else if (ListCode.OMNIVORE.contains(code)) {
                Omnivore omnivore = new Omnivore(code);
                omnivore.setWeight(props);
                creature = omnivore;
            } else if (ListCode.PRODUCT.contains(code)) {
                Product product = new Product(code);
                creature = product;
            } else {
                System.out.println("Unknown card type: " + code);
            }
            
            if (creature != null) {
                int countItem = Integer.parseInt(content.get(2));
                for (int i = 0; i < countItem; i++) {
                    int idx = listEffect.indexOf(content.get(i + 3));
                    if (idx != -1) {
                        ((Creature) creature).useEffect(idx);
                    }
                }
                
                int col = loc.charAt(0) - 'A';
                int row = getIdxFromLocation(loc) - 1;
                ladang.loadplantKartu(row, col, creature);
            }
        }
        return ladang;
    }
}