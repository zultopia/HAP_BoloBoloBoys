package com.hap.hap_boloboloboys.lib.person;
import com.hap.hap_boloboloboys.lib.card.Card;

/**
 * Inventory is a collection of cards
 * */
public class Inventory {
    private static final int DEFAULT_CAPACITY = 40;

    private int capacity;
    private int currentSize;
    private Card[] cards;

    /**
     * Default constructor
     * Default capacity is 40
     */
    public Inventory() {
        this.capacity = DEFAULT_CAPACITY;
        this.currentSize = 0;
        this.cards = new Card[DEFAULT_CAPACITY];
    }

    /**
     * Constructor with capacity
     * @param capacity capacity of the inventory
     */
    public Inventory(int capacity) {
        if (capacity <= 0) {
            this.capacity = DEFAULT_CAPACITY;
            this.cards = new Card[DEFAULT_CAPACITY];
        }
        else {
            this.capacity = capacity;
            this.cards = new Card[capacity];
        }
        this.currentSize = 0;
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
     * Remove card from inventory
     * @param index index of card to be removed
     * @throws InventoryException if index is invalid, or the index of inventory is null
     */
    public void delete(int index) throws InventoryException {
        if (index < 0 || index >= this.getSize() || this.cards[index] == null) throw new InventoryException("Indeks invalid");
        this.cards[index] = null;
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

    // TODO shuffle card to place into deck
}
