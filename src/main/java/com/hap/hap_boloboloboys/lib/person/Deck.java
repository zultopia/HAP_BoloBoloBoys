package com.hap.hap_boloboloboys.lib.person;
import com.hap.hap_boloboloboys.lib.card.*;

import java.util.List;

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
    public void putToDeck(List<Card> shuffledCard) {
        int size = Math.min(this.capacity - this.calculateSize(), shuffledCard.size());
        for (int i = 0; i < size; i++) {
            try {
                this.add(shuffledCard.get(i));
            } catch (InventoryException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void putToDeck(Card card) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.cards[i] == null) {
                this.cards[i] = card;
                this.currentSize++;
                break;
            }
        }
    }

    public void putToDeck(Card card, int index) throws InventoryException {
        if (index >= 0 && index < this.capacity && this.cards[index] == null) {
            this.cards[index] = card;
            this.currentSize++;
        } else {
            throw new InventoryException("Indeks invalid");
        }
    }

    @Override
    public Card pop(int index) throws InventoryException {
        if (index < 0 || index >= this.capacity || this.cards[index] == null) throw new InventoryException("Indeks invalid");
        else {
            Card card = this.cards[index];
            this.cards[index] = null;
            this.currentSize--;
            return card;
        }
    }

    public void moveCard(int from, int to) {
        if (from >= 0 && from < this.capacity && to >= 0 && to < this.capacity && this.cards[from] != null && this.cards[to] == null) {
            this.cards[to] = this.cards[from];
            this.cards[from] = null;
        }
    }
}
