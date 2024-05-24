package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProtectTest {
    @Test
    public void testProtect() {
        Protect protect = new Protect();
        assertEquals("PROTECT", protect.getCode());
        assertEquals("Protect", protect.getCardName());
    }
    
    @Test
    public void testApplyEffect() {
        Protect accelerate = new Protect();
        Animal animal = new Omnivore("AYAM");
        animal.useEffect(4);
        accelerate.applyEffect(animal);
        assertEquals(2, animal.getEffectValue(4));
    }
}
