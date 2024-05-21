package com.hap.hap_boloboloboys.lib.card;

public abstract class Creature extends Card {
    private int harvestTarget;
    private boolean[] appliedEffects = new boolean[6]; // 6 effects

    public Creature(String cardName, String imgPath, int harvestTarget) {
        super(cardName, imgPath);
        this.harvestTarget = harvestTarget;
    }

    public int getHarvestTarget() {
        return harvestTarget;
    }

    public void setHarvestTarget(int harvestTarget) {
        this.harvestTarget = harvestTarget;
    }

    public void useEffect(int effectIndex) {
        if (effectIndex >= 0 && effectIndex < appliedEffects.length) {
            appliedEffects[effectIndex] = true;
        }
    }

    public boolean isEffectApplied(int effectIndex) {
        if (effectIndex >= 0 && effectIndex < appliedEffects.length) {
            return appliedEffects[effectIndex];
        }
        return false;
    }

    public abstract boolean canHarvest();
}
