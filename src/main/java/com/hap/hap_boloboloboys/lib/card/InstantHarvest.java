package com.hap.hap_boloboloboys.lib.card;

public class InstantHarvest extends Item {
    public InstantHarvest(String cardName) {
        super(cardName, Effect.INSTANT_HARVEST);
    }

    @Override
    public void applyEffect(Creature creature) {
        if (creature instanceof Plant) {
            Plant plant = (Plant) creature;
            plant.setAge(plant.getHarvestTarget());
            plant.useEffect(2); // Set Instant Harvest effect to true
        } else if (creature instanceof Animal) {
            Animal animal = (Animal) creature;
            animal.setWeight(animal.getHarvestTarget());
            animal.useEffect(2); // Set Instant Harvest effect to true
        }
    }
}
