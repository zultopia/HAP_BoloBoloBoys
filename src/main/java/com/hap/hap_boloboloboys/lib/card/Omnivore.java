package com.hap.hap_boloboloboys.lib.card;


public class Omnivore extends Animal {
    public Omnivore(String code) {
        super(code);
    }

    @Override
    public boolean canEat(Product productCard) {
        return true; 
    }
}
