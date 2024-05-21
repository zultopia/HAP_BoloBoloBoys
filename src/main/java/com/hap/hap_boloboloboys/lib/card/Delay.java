package com.hap.hap_boloboloboys.lib.card;

public class Delay extends Item {
    public Delay(String cardName) {
        super(cardName, Effect.DELAY);
    }

    @Override
    public void applyEffect(Creature creature) {
        if (creature instanceof Plant) {
            Plant plant = (Plant) creature;
            plant.setAge(plant.getAge() - 2);
            plant.useEffect(1); // Set Delay effect to true
        } else if (creature instanceof Animal) {
            Animal animal = (Animal) creature;
            animal.setWeight(animal.getWeight() - 5);
            animal.useEffect(1); // Set Delay effect to true
        }
    }
}
