package com.hap.hap_boloboloboys.lib.person;
import com.hap.hap_boloboloboys.lib.card.*;
import com.hap.hap_boloboloboys.lib.config.ListCode;

import java.util.*;

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

    public void initiateFromScratch() {
        initiateFromScratch(DEFAULT_CAPACITY);
    }

    /* GAME MODIFIER */

    /**
     * Generate all 40 random cards when game initialized from scratch (not from state)
     */
    public void initiateFromScratch(int maximum) {
        for (int i = 0; i < maximum; i++) {
            if (this.cards[i] != null) continue;
            Random rand = new Random();
            /* Random nicely
             * if 0 : plant
             * if 1 : carnivore
             * if 2 : herbivore
             * if 3 : omnivore
             * if 4 : items
             * if 5 : product
             * */
            switch (i % 6) {
                case 0:
                    List<String> plant = new ArrayList<>(ListCode.PLANT);
                    this.cards[i] = new Plant(plant.get(rand.nextInt(plant.size())));
                    break;
                case 1:
                    List<String> carnivore = new ArrayList<>(ListCode.CARNIVORE);
                    this.cards[i] = new Carnivore(carnivore.get(rand.nextInt(carnivore.size())));
                    break;
                case 2:
                    List<String> herbivore = new ArrayList<>(ListCode.HERBIVORE);
                    this.cards[i] = new Herbivore(herbivore.get(rand.nextInt(herbivore.size())));
                    break;
                case 3:
                    List<String> omnivore = new ArrayList<>(ListCode.OMNIVORE);
                    this.cards[i] = new Omnivore(omnivore.get(rand.nextInt(omnivore.size())));
                    break;
                case 4:
                    int itemType = rand.nextInt(7);
                    switch (itemType) {
                        case 1:
                            this.cards[i] = new Accelerate();
                            break;
                        case 2:
                            this.cards[i] = new Delay();
                            break;
                        case 3:
                            this.cards[i] = new Destroy();
                            break;
                        case 4:
                            this.cards[i] = new InstantHarvest();
                            break;
                        case 6:
                            this.cards[i] = new Trap();
                            break;
                        default:
                            this.cards[i] = new Protect();
                            break;
                    }
                    break;
                case 5:
                    List<String> product = new ArrayList<>(ListCode.PRODUCT);
                    this.cards[i] = new Product(product.get(rand.nextInt(product.size())));
                    break;
            }
        }
        setCurrentSize(maximum);
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
            int randomIndex = rand.nextInt(this.getSize());
            shuffledCards[i] = this.getCard(randomIndex);
            this.cards[randomIndex] = null;
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
        return this.getCard(randomIndex);
    }
}
