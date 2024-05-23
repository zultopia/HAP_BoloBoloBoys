package com.hap.hap_boloboloboys.lib.card;

public abstract class Creature extends Card {
    private int harvestTarget;
    private int[] appliedEffects = new int[6]; // 6 effects, default to 0
    private String productCode;

    public Creature(String code) {
        super(code); // Call to the superclass constructor with the code
        setCreatureDetails(code);
    }

    private void setCreatureDetails(String code) {
        switch (code) {
            case "HIU_DARAT":
                setCardName("Hiu Darat");
                harvestTarget = 20;
                productCode = "SIRIP_HIU";
                break;
            case "SAPI":
                setCardName("Sapi");
                harvestTarget = 10;
                productCode = "SUSU";
                break;
            case "DOMBA":
                setCardName("Domba");
                harvestTarget = 12;
                productCode = "DAGING_DOMBA";
                break;
            case "KUDA":
                setCardName("Kuda");
                harvestTarget = 14;
                productCode = "DAGING_KUDA";
                break;
            case "AYAM":
                setCardName("Ayam");
                harvestTarget = 5;
                productCode = "TELUR";
                break;
            case "BERUANG":
                setCardName("Beruang");
                harvestTarget = 25;
                productCode = "DAGING_BERUANG";
                break;
            case "BIJI_JAGUNG":
                setCardName("Biji Jagung");
                harvestTarget = 3;
                productCode = "JAGUNG";
                break;
            case "BIJI_LABU":
                setCardName("Biji Labu");
                harvestTarget = 5;
                productCode = "LABU";
                break;
            case "BIJI_STROBERI":
                setCardName("Biji Stroberi");
                harvestTarget = 4;
                productCode = "STROBERI";
                break;
        }
        setImgPath("path/to/" + getCardName().replace(" ", "") + ".png");
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
        return new Product(productCode);
    }
}
