package com.hap.hap_boloboloboys.lib.person;

import com.hap.hap_boloboloboys.lib.card.Card;

public class Storage {
    protected int capacity;
    protected int currentSize;
    protected Card[] cards;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        this.cards = new Card[capacity];
    }

    /*GETTER*/
    public int getCapacity() {return this.capacity;}
    public int getCurrentSize() {return this.currentSize;}
    public Card[] getCards() {return this.cards;}
    /**
     * Get the size of the inventory, refactor the items in inventory so there are no empty slots in the middle
     * @return size of the inventory
     */
    public int getSize() {
        // Refactor the items
        Card[] newCards = new Card[this.capacity];
        int index = 0;
        for (int i = 0; i < this.capacity; i++) {
            if (this.cards[i] != null) {
                newCards[index] = this.cards[i];
                index++;
            }
        }
        // Update the current size and cards
        this.currentSize = index;
        this.cards = newCards;
        return index;
    }
    /** Get card at specified index
     * @param index index of Card to be get
     * @throws InventoryException if index is invalid, or the index of inventory is null
     * @return Card of the index in the Inventory array */
    public Card getCard(int index) throws InventoryException {
        if (index < 0 || index >= this.getSize() || this.cards[index] == null) throw new InventoryException("Indeks invalid");
        else return this.cards[index];
    }


    /*SETTER*/
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setCurrentSize(int currentSize) {this.currentSize = currentSize;}
    public void setCards(Card[] cards) {
        this.cards = cards;
        this.currentSize = this.getSize();
    }

    /*CHECKER*/
    public boolean isFull() {return this.getSize() == this.capacity;}
    public boolean isEmpty() {return this.getSize() == 0;}
    public Card findCard(Card card) {
        for (int i = 0; i < this.getSize(); i++) {
            if (this.cards[i].equals(card)) return this.cards[i];
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /*ADD/REMOVE CARD*/
    /**
     * Add card to inventory
     * @param card card to be added
     * @throws InventoryException if inventory is full
     */
    public void add(Card card) throws InventoryException {
        if (this.isFull()) throw new InventoryException("Inventory penuh");
        for (int i = 0; i < this.capacity; i++) {
            if (this.cards[i] == null) {
                this.cards[i] = card;
                break;
            }
        }
        this.currentSize = this.getSize();
    }

    /**
     * Remove card from inventory, given the index
     * @param index index of card to be removed
     * @throws InventoryException if index is invalid, or the index of inventory is null
     */
    public void delete(int index) throws InventoryException {
        if (index < 0 || index >= this.getSize() || this.cards[index] == null) throw new InventoryException("Indeks invalid");
        this.cards[index] = null;
        this.currentSize = this.getSize();
    }

    /**
     * Remove card from inventory, given the card
     * @param card card to be removed
     */
    public void delete(Card card) {
        for (int i = 0; i < this.getSize(); i++) {
            if (this.cards[i] == card) {
                this.cards[i] = null;
                break;
            }
        }
        this.currentSize--;
        this.currentSize = this.getSize();
    }

    /**
     * Pop card from inventory
     * @param index index of card to be popped
     * @throws InventoryException if index is invalid, or the index of inventory is null
     * @return card of the index in the Inventory array
     */
    public Card pop(int index) throws InventoryException {
        if (index < 0 || index >= this.getSize() || this.cards[index] == null) throw new InventoryException("Indeks invalid");
        Card card = this.cards[index];
        this.cards[index] = null;
        this.currentSize = this.getSize();
        return card;
    }

}
