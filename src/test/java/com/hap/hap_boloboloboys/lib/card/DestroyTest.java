package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DestroyTest {
    @Test
    public void testDestroy() {
        Destroy destroy = new Destroy();
        assertEquals("DESTROY", destroy.getCode());
        assertEquals("Destroy", destroy.getCardName());
    }
    
    @Test
    public void testApplyEffect() {
        Destroy destroy = new Destroy();
        Creature animal = new Omnivore("BERUANG");
        animal.useEffect(0);
        destroy.applyEffect(animal);
        assertEquals(1, animal.getEffectValue(3));
    } 
}

