package com.hap.hap_boloboloboys.lib.card;

public class Accelerate extends Item {
    public Accelerate() {
        super("ACCELERATE");
    }

    @Override
    public void applyEffect(Creature creature) {
        if (creature instanceof Plant) {
            Plant plant = (Plant) creature;
            plant.grow();
            plant.grow();
            plant.useEffect(0); 
        } else if (creature instanceof Animal) {
            Animal animal = (Animal) creature;
            animal.setWeight(animal.getWeight() + 8);
            animal.useEffect(0); 
        }
    }
}
