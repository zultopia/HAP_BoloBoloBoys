package com.hap.hap_boloboloboys.lib.card;

public class Trap extends Item {
    public Trap(String cardName, String imgPath) {
        super(cardName, imgPath, Effect.TRAP);
    }

    @Override
    public void applyEffect(Creature creature) {
        creature.useEffect(5); // Set Trap effect to true
    }
}
