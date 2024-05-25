package com.hap.hap_boloboloboys.lib.config;

import com.hap.hap_boloboloboys.lib.card.*;
import com.hap.hap_boloboloboys.lib.field.*;
import com.hap.hap_boloboloboys.lib.person.*;
import com.hap.hap_boloboloboys.lib.store.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.File;

public class LoadJsonPlugin {

    public static void saveGameStateToJSON(String folderPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Game state data
        GameState gameState = new GameState();
        gameState.setCurrentTurn(Load.getCurrentTurn());
        gameState.setShopItems(Load.getShopItems());

        // Serialize game state to JSON
        File file = new File("config/" + folderPath + "/gamestate.json");
        mapper.writeValue(file, gameState);
        System.out.println("Game state saved to JSON successfully: " + file.getAbsolutePath());
    }

    public static void savePlayerToJSON(String folderPath, String player) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Player data
        PlayerState playerState = new PlayerState();
        playerState.setWealth(Load.getWealth());
        playerState.setCurrentSizeInventory(Load.getCurrentSizeInventory());
        playerState.setCurrentSizeDeck(Load.getCurrentSizeDeck());
        playerState.setDeck(Load.getDeck());
        playerState.setCardLadangCount(Load.getCardLadangCount());
        playerState.setContent(Load.getContent());

        // Serialize player state to JSON
        File file = new File("config/" + folderPath + "/" + player + ".json");
        mapper.writeValue(file, playerState);
        System.out.println("Player state saved to JSON successfully: " + file.getAbsolutePath());
    }

    public static void saveStoreToJSON(String folderPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Store data
        Store store = Load.getStore();

        // Serialize store to JSON
        File file = new File("config/" + folderPath + "/store.json");
        mapper.writeValue(file, store);
        System.out.println("Store saved to JSON successfully: " + file.getAbsolutePath());
    }

    // Example class for game state serialization
    private static class GameState {
        private int currentTurn;
        private Map<String, Integer> shopItems;

        public int getCurrentTurn() {
            return currentTurn;
        }

        public void setCurrentTurn(int currentTurn) {
            this.currentTurn = currentTurn;
        }

        public Map<String, Integer> getShopItems() {
            return shopItems;
        }

        public void setShopItems(Map<String, Integer> shopItems) {
            this.shopItems = shopItems;
        }
    }

    // Example class for player state serialization
    private static class PlayerState {
        private int wealth;
        private int currentSizeInventory;
        private int currentSizeDeck;
        private Map<String, String> deck;
        private int cardLadangCount;
        private Map<String, List<String>> content;

        public int getWealth() {
            return wealth;
        }

        public void setWealth(int wealth) {
            this.wealth = wealth;
        }

        public int getCurrentSizeInventory() {
            return currentSizeInventory;
        }

        public void setCurrentSizeInventory(int currentSizeInventory) {
            this.currentSizeInventory = currentSizeInventory;
        }

        public int getCurrentSizeDeck() {
            return currentSizeDeck;
        }

        public void setCurrentSizeDeck(int currentSizeDeck) {
            this.currentSizeDeck = currentSizeDeck;
        }

        public Map<String, String> getDeck() {
            return deck;
        }

        public void setDeck(Map<String, String> deck) {
            this.deck = deck;
        }

        public int getCardLadangCount() {
            return cardLadangCount;
        }

        public void setCardLadangCount(int cardLadangCount) {
            this.cardLadangCount = cardLadangCount;
        }

        public Map<String, List<String>> getContent() {
            return content;
        }

        public void setContent(Map<String, List<String>> content) {
            this.content = content;
        }
    }
}
