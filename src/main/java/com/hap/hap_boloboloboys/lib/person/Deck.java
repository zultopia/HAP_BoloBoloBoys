package com.hap.hap_boloboloboys.lib.person;
import com.hap.hap_boloboloboys.lib.card.*;

public class Deck extends Storage {
    private final static int DEFAULT_CAPACITY = 6;

    public Deck() {
        super(DEFAULT_CAPACITY);
    }

    public Deck(int capacity) {
        super(capacity <= 0 ? DEFAULT_CAPACITY : capacity);
    }

    /**
     * Put the cards from inventory to deck. The size of empty slots is already checked to be sufficient before called
     * @param shuffledCard array of shuffled cards
     */
    public void putToDeck(Card[] shuffledCard) {
        for (int i = 0; i < this.capacity - this.getSize(); i++) {
            try {
                this.add(shuffledCard[i]);
            } catch (InventoryException e) {
                break;
            }
        }
    }

    public void putToDeck(Card card) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.cards[i] == null) {
                this.cards[i] = card;
                this.getSize();
                break;
            }
        }
    }
}
