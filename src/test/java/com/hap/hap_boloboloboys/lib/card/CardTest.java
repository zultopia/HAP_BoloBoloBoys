package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void testGetCode() {
        Card card = new Product("SIRIP_HIU");
        assertEquals("SIRIP_HIU", card.getCode());
    }

    @Test
    public void testGetCardName() {
        Card card = new Product("SIRIP_HIU");
        card.setCardName("Test Card");
        assertEquals("Test Card", card.getCardName());
    }

    @Test
    public void testGetImgPath() {
        Card card = new Product("SIRIP_HIU");
        card.setImgPath("/path/to/image.png");
        assertEquals("/path/to/image.png", card.getImgPath());
    }

    @Test
    public void testSetImgPath() {
        Card card = new Product("SIRIP_HIU");
        card.setImgPath("/path/to/image.png");
        assertEquals("/path/to/image.png", card.getImgPath());
    }

    @Test
    public void testSetCardName() {
        Card card = new Product("SIRIP_HIU");
        card.setCardName("Test Card");
        assertEquals("Test Card", card.getCardName());
    }
}
