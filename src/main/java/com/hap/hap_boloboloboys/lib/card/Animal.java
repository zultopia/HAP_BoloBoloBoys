package com.hap.hap_boloboloboys.lib.card;

public abstract class Animal extends Creature {
    private int weight;

    public Animal(String code) {
        super(code);
        this.weight = 0;
    }

    public abstract boolean canEat(Product productCard);

    public void feed(Product productCard) throws CardException {
        if (canEat(productCard)) {
            this.weight += productCard.getAddedWeight();
            System.out.println(getCardName() + " has eaten " + productCard.getCardName() + " and gained " + productCard.getAddedWeight() + " weight.");
        } else {
            throw new CardException("This animal cannot eat this product.");
        }
    }

    @Override
    public boolean canHarvest() {
        return this.weight >= getHarvestTarget();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = Math.max(weight, 0);
    }
}
