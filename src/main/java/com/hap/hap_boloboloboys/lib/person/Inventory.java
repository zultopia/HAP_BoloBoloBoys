package com.hap.hap_boloboloboys.lib.person;
import com.hap.hap_boloboloboys.lib.card.*;


import java.util.Random;

/**
 * Inventory is a collection of cards
 * */
public class Inventory extends Storage {
    private static final int DEFAULT_CAPACITY = 40;

    /**
     * Default constructor
     * Default capacity is 40
     */
    public Inventory() {
        super(DEFAULT_CAPACITY); // super(40)
    }

    /**
     * Constructor with capacity
     * @param capacity capacity of the inventory
     */
    public Inventory(int capacity) {
        super(capacity <= 0 ? DEFAULT_CAPACITY : capacity);

    }

    /* GAME MODIFIER */

    /**
     * Generate all 40 random cards when game initialized from scratch (not from state)
     */
    public void initiateFromScratch() {
        for (int i = 0; i < this.capacity; i++) {
            if (this.cards[i] == null) continue;
            Random rand = new Random();
            int cardType = rand.nextInt(3);
            // TODO instantiate card based on cardType random
        }
    }

    /**
     * Shuffle the cards in the inventory
     * @param neededCard number of cards to be shuffled, got from deck.emptyslots
     * @return array of shuffled cards without delete the card in the inventory
     */
    public Card[] shuffle(int neededCard) {
        Card[] shuffledCards = new Card[neededCard];
        Random rand = new Random();
        for (int i = 0; i < neededCard; i++) {
            int randomIndex = rand.nextInt(this.capacity);
            try {
                shuffledCards[i] = this.getCard(randomIndex);
            } catch (InventoryException e) {
                i--;
            }
        }
        return shuffledCards;
    }

    /**
     * Shuffle the inventory and return a random card
     * @return a single random card from the inventory
     */
    public Card shuffle() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(this.getSize());
        try {
            return this.getCard(randomIndex);
        } catch (InventoryException e) {
            return null;
        }
    }
}
