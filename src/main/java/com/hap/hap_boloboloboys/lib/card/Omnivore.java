package com.hap.hap_boloboloboys.lib.card;


public class Omnivore extends Animal {
    public Omnivore(String cardName, String imgPath, int harvestTarget) {
        super(cardName, imgPath, harvestTarget);
    }

    @Override
    public boolean canEat(Product productCard) {
        return true; 
    }
}
