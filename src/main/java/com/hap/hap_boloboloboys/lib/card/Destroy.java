package com.hap.hap_boloboloboys.lib.card;

public class Destroy extends Item {
    public Destroy() {
        super("DESTROY");
    }

    @Override
    public void applyEffect(Creature creature) {
        creature.useEffect(3);
    }
}
