package com.hap.hap_boloboloboys.lib.card;

public class Protect extends Item {
    public Protect() {
        super("PROTECT");
    }

    @Override
    public void applyEffect(Creature creature) {
        creature.useEffect(4);
    }
}
