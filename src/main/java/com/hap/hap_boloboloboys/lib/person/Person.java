package com.hap.hap_boloboloboys.lib.person;

import com.hap.hap_boloboloboys.lib.card.*;
import com.hap.hap_boloboloboys.lib.field.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class Person {
    private String name;
    private int wealth = 0;
    private Inventory inventory = new Inventory(40);
    private Deck deck = new Deck(6);
    public List<Card> shuffledCard = new ArrayList<>(0);
    public Ladang ladangku = new Ladang();

    public Person(String name) {
        this.name = name;
        this.wealth = 0;
        this.inventory = new Inventory(40);
        this.deck = new Deck(6);
        this.shuffledCard = new ArrayList<>(0);
        this.ladangku = new Ladang();
    }

    public Person(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public Person(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public Person(String name, Ladang ladang) {
        this.name = name;
        this.ladangku = ladang;
    }

    public Person(String name, Inventory inventory, Deck deck, Ladang ladang) {
        this.name = name;
        this.inventory = inventory;
        this.deck = deck;
        this.ladangku = ladang;
    }

    /*GETTER*/
    public String getName() {return this.name;}
    public int getWealth() {return this.wealth;}
    public Inventory getInventory() {return this.inventory;}
    public Deck getDeck() {return this.deck;}
    public List<Card> getShuffledCard() {return this.shuffledCard;}
    public Ladang getLadang() {return this.ladangku;}

    /*SETTER*/
    public void setName(String name) {this.name = name;}
    public void setWealth(int wealth) {this.wealth = wealth;}
    public void setInventory(Inventory inventory) {this.inventory = inventory;}
    public void setDeck(Deck deck) {this.deck = deck;}
    public void setShuffledCard(List<Card> shuffledCard) {this.shuffledCard = shuffledCard;}
    public void addWealth(int wealth) {this.wealth += wealth;}
    public void reduceWealth(int wealth) {this.wealth -= wealth;}
    public void setLadang(Ladang ladang) {this.ladangku = ladang;}

    /*SHUFFLE CARD*/
    /* Sequence :
    * 1. When change turn, call shuffleCard, automatically shuffle the card from inventory to deck, based on the deck
    * and inventory size
    * 2. When player choose (click) a card, call getCardFromShuffled to get the card chosen
    * 3. After the card chosen, call putToDeck to put the card to deck
    * 4. After the card put to deck, call deleteFromShuffled to delete the card from shuffled card
    * 5. After the card deleted from shuffled card, the player can choose remaining card in already exist shuffledCard,
    * or reshuffle again using shuffle card.
    * 6. If the user reshuffle the card, all the card that already exist in the shuffled card will be put back into inventory
    */

    /**
     * Shuffle the card from inventory to shuffledCard.
     * Shuffled card is a list of card that will be shown to player to choose.
     * The size of shuffled card is based on the empty slots of deck or if inventory size is less than empty slots of deck,
     * then the size of shuffled card is based on inventory size.
     * Call this function again to reshuffle
     */
    public void shuffleCard() {
        int neededCard = this.deck.getCapacity() - this.deck.calculateSize(); // Empty slots of deck
        int inventorySize = this.inventory.getSize(); // How many card in inventory
        // Check if inventory size is sufficient, use minimum between inventory size and needed card
        neededCard = min(min(neededCard, 4), inventorySize);// Maximum 4 cards or the empty slots of deck, or the inventory size
        System.out.println(neededCard);
        this.shuffledCard = List.of(this.inventory.shuffle(neededCard));
    }

    /**
     * Get the card from shuffled card based on index
     * @param index index of card to be get from shuffled card
     * @return card from shuffled card based on index
     */
    public Card getCardFromShuffled(int index) {
        return this.shuffledCard.get(index);
    }

    /**
     * Put the cards from shuffled card to deck
     * @param card card to be put to deck
     */
    public void putToDeck(Card card) {
        this.deck.putToDeck(card);
    }

    /**
     * Delete a single card from shuffled card. Call this function when shuffled card shown in GUI and a person choose a card
     * @param index index of card to be deleted
     */
    public void deleteFromShuffled(int index) {
        this.shuffledCard.remove(index);
    }
}
