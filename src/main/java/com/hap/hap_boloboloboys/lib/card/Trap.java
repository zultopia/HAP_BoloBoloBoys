package com.hap.hap_boloboloboys.lib.card;

public class Trap extends Item {
    public Trap() {
        super("TRAP");
    }

    @Override
    public void applyEffect(Creature creature) {
        creature.useEffect(5); 
    }
}
