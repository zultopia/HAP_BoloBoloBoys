package com.hap.hap_boloboloboys.lib.card;

// index:
// 0 - accelerate
// 1 - delay
// 2 - instant harvest
// 3 - destroy
// 4 - protect
// 5 - trap

public abstract class Item extends Card {
    public enum Effect {
        ACCELERATE, DELAY, INSTANT_HARVEST, DESTROY, PROTECT, TRAP
    }

    private Effect effect;

    public Item(String cardName, String imgPath, Effect effect) {
        super(cardName, imgPath);
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public abstract void applyEffect(Creature creature);
}
