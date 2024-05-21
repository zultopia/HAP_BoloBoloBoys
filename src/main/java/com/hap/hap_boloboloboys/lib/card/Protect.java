package com.hap.hap_boloboloboys.lib.card;

public class Protect extends Item {
    public Protect(String cardName, String imgPath) {
        super(cardName, imgPath, Effect.PROTECT);
    }

    @Override
    public void applyEffect(Creature creature) {
        creature.useEffect(4); // Set Protect effect to true
    }
}
