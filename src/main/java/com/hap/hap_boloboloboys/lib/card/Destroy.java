package com.hap.hap_boloboloboys.lib.card;

public class Destroy extends Item {
    public Destroy(String cardName) {
        super(cardName, Effect.DESTROY);
    }

    @Override
    public void applyEffect(Creature creature) {
        creature.useEffect(3); // Set Destroy effect to true
        // belom diimplementasi
    }
}
