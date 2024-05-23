package com.hap.hap_boloboloboys.lib.card;

public abstract class Item extends Card {
    public enum Effect {
        ACCELERATE, DELAY, INSTANT_HARVEST, DESTROY, PROTECT, TRAP
    }

    private Effect effect;

    public Item(String code) {
        super(code);
        setItemDetails(code);
    }

    private void setItemDetails(String code) {
        switch (code) {
            case "ACCELERATE":
                setCardName("Accelerate");
                effect = Effect.ACCELERATE;
                break;
            case "DELAY":
                setCardName("Delay");
                effect = Effect.DELAY;
                break;
            case "INSTANT_HARVEST":
                setCardName("Instant Harvest");
                effect = Effect.INSTANT_HARVEST;
                break;
            case "DESTROY":
                setCardName("Destroy");
                effect = Effect.DESTROY;
                break;
            case "PROTECT":
                setCardName("Protect");
                effect = Effect.PROTECT;
                break;
            case "TRAP":
                setCardName("Trap");
                effect = Effect.TRAP;
                break;
        }
        setImgPath("path/to/" + getCardName().replace(" ", "") + ".png");
    }

    public Effect getEffect() {
        return effect;
    }

    public abstract void applyEffect(Creature creature);
}
