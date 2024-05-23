package com.hap.hap_boloboloboys.lib.card;

import java.util.Set;

public class Herbivore extends Animal {
    private static final Set<String> ALLOWED_FOODS = Set.of("Jagung", "Labu", "Stroberi");

    public Herbivore(String code) {
        super(code);
    }

    @Override
    public boolean canEat(Product productCard) {
        return ALLOWED_FOODS.contains(productCard.getCardName());
    }
}
