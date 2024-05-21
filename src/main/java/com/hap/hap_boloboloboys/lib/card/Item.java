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

    public Item(String cardName, Effect effect) {
        super(cardName, determineImgPath(cardName));
        this.effect = effect;
    }

    private static String determineImgPath(String cardName) {
        return "path/to/" + cardName.replace(" ", "") + ".png";
    }

    public Effect getEffect() {
        return effect;
    }

    public abstract void applyEffect(Creature creature);
}
