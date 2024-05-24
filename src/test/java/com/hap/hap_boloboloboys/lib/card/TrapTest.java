package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrapTest {
    @Test
    public void testProtect() {
        Trap trap = new Trap();
        assertEquals("TRAP", trap.getCode());
        assertEquals("Trap", trap.getCardName());
    }
    
    @Test
    public void testApplyEffect() {
        Trap trap = new Trap();
        Animal animal = new Omnivore("AYAM");
        animal.useEffect(5);
        trap.applyEffect(animal);
        assertEquals(2, animal.getEffectValue(5));
    }
}
