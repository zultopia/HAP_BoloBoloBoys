package com.hap.hap_boloboloboys.lib.person;

import com.hap.hap_boloboloboys.lib.card.Card;
import com.hap.hap_boloboloboys.lib.card.Herbivore;
import com.hap.hap_boloboloboys.lib.card.Plant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    private Deck deck;
    private Card card1;
    private Card card2;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
        card1 = new Herbivore("SAPI");
        card2 = new Plant("BIJI_LABU");
    }

    @Test
    public void testDefaultCapacity() {
        assertEquals(6, deck.getCapacity());
    }

    @Test
    public void testCustomCapacity() {
        Deck customDeck = new Deck(10);
        assertEquals(10, customDeck.getCapacity());
    }

    @Test
    public void testPutToDeckWithList() throws InventoryException  {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        deck.putToDeck(cards);
        assertEquals(2, deck.calculateSize());
        assertEquals(card1, deck.pop(0));
        assertEquals(card2, deck.pop(1));
    }

    @Test
    public void testPutToDeckWithCard() throws InventoryException {
        deck.putToDeck(card1);
        assertEquals(1, deck.calculateSize());
        assertEquals(card1, deck.pop(0));
    }

    @Test
    public void testPutToDeckWithCardAndIndex() {
        try {
            deck.putToDeck(card1, 2);
            assertEquals(1, deck.calculateSize());
            assertEquals(card1, deck.pop(2));
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testPutToDeckWithInvalidIndex() {
        assertThrows(InventoryException.class, () -> {
            deck.putToDeck(card1, 10);
        });
    }

    @Test
    public void testPop() {
        deck.putToDeck(card1);
        try {
            Card poppedCard = deck.pop(0);
            assertEquals(card1, poppedCard);
            assertEquals(0, deck.calculateSize());
        } catch (InventoryException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testPopWithInvalidIndex() {
        assertThrows(InventoryException.class, () -> {
            deck.pop(10);
        });
    }

    @Test
    public void testMoveCard() throws InventoryException {
        deck.putToDeck(card1);
        deck.moveCard(0, 2);
        assertEquals(card1, deck.pop(2));
    }

    @Test
    public void testMoveCardWithInvalidIndex() throws InventoryException {
        deck.putToDeck(card1);
        deck.moveCard(0, 10);
        assertEquals(card1, deck.pop(0));
    }
}
