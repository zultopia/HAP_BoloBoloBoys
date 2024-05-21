package com.hap.hap_boloboloboys.lib.card;

public class Plant extends Creature {
    private int age;

    public Plant(String cardName, String imgPath, int harvestTarget) {
        super(cardName, imgPath, harvestTarget);
        this.age = 0;
    }

    public void grow() {
        this.age++;
    }

    @Override
    public boolean canHarvest() {
        return this.age >= getHarvestTarget();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}