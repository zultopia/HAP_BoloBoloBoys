package com.hap.hap_boloboloboys.lib.person;

public class Inventory {
    private int capacity;
    private int currentSize;
    // TODO array of card

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
    }

    /*GETTER*/
    public int getCapacity() {return this.capacity;}
    public int getCurrentSize() {return this.currentSize;}

    // TODO get card at specified index
    // TODO get array of

    /*SETTER*/
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setCurrentSize(int currentSize) {this.currentSize = currentSize;}
    // TODO add card to inventory

    /*CHECKER*/
    public boolean isFull() {return this.currentSize == this.capacity;}
    public boolean isEmpty() {return this.currentSize == 0;}

    /*ADD/REMOVE CARD*/
    // TODO add card to inventory
    // TODO remove card from inventory

    // TODO shuffle card to place into deck
}
