package com.hap.hap_boloboloboys.lib.card;

public abstract class Creature extends Card {
    private int harvestTarget;
    private int[] appliedEffects = new int[6]; // 6 effects, default to 0

    public Creature(String cardName) {
        super(cardName, determineImgPath(cardName));
        this.harvestTarget = determineHarvestTarget(cardName);
    }

    private static String determineImgPath(String cardName) {
        return "path/to/" + cardName.replace(" ", "") + ".png";
    }

    private int determineHarvestTarget(String cardName) {
        switch (cardName) {
            case "Hiu Darat":
                return 20;
            case "Sapi":
                return 10;
            case "Domba":
                return 12;
            case "Kuda":
                return 14;
            case "Ayam":
                return 5;
            case "Beruang":
                return 25;
            case "Biji Jagung":
                return 3;
            case "Biji Labu":
                return 5;
            case "Biji Stroberi":
                return 4;
            default:
                throw new IllegalArgumentException("Unknown creature: " + cardName);
        }
    }

    public int getHarvestTarget() {
        return harvestTarget;
    }

    public void setHarvestTarget(int harvestTarget) {
        this.harvestTarget = harvestTarget;
    }

    public void useEffect(int effectIndex) {
        if (effectIndex >= 0 && effectIndex < appliedEffects.length) {
            appliedEffects[effectIndex]++;
        }
    }

    public int getEffectValue(int effectIndex) {
        if (effectIndex >= 0 && effectIndex < appliedEffects.length) {
            return appliedEffects[effectIndex];
        }
        return 0;
    }

    public String getEffectName(int effectIndex) {
        if (effectIndex >= 0 && effectIndex < appliedEffects.length) {
            switch (effectIndex) {
                case 0:
                    return "Accelerate";
                case 1:
                    return "Delay";
                case 2:
                    return "Instant Harvest";
                case 3:
                    return "Destroy";
                case 4:
                    return "Protect";
                case 5:
                    return "Trap";
            }
        }
        return "No such effect";
    }

    public abstract boolean canHarvest();

    public Product harvest() {
        if (!canHarvest()) {
            System.out.println(getCardName() + " cannot be harvested yet.");
            return null;
        }

        String productName = determineProductName(getCardName());
        return new Product(productName);
    }

    private String determineProductName(String cardName) {
        switch (cardName) {
            case "Hiu Darat":
                return "Sirip Hiu";
            case "Sapi":
                return "Susu";
            case "Domba":
                return "Daging Domba";
            case "Kuda":
                return "Daging Kuda";
            case "Ayam":
                return "Telur";
            case "Beruang":
                return "Daging Beruang";
            case "Biji Jagung":
                return "Jagung";
            case "Biji Labu":
                return "Labu";
            case "Biji Stroberi":
                return "Stroberi";
            default:
                throw new IllegalArgumentException("No harvestable product for " + cardName);
        }
    }
}
