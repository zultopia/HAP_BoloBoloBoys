package com.hap.hap_boloboloboys.lib.card;


public class Omnivore extends Animal {
    public Omnivore(String cardName) {
        super(cardName);
    }

    @Override
    public boolean canEat(Product productCard) {
        return true; 
    }
}
