package com.hap.hap_boloboloboys.lib.card;

import java.util.Set;

public class Carnivore extends Animal {
    private static final Set<String> ALLOWED_FOODS = Set.of("Sirip Hiu", "Susu", "Telur", "Daging Kuda", "Daging Domba", "Daging Beruang");

    public Carnivore(String cardName) {
        super(cardName);
    }

    @Override
    public boolean canEat(Product product) {
        return ALLOWED_FOODS.contains(product.getCardName());
    }
}
