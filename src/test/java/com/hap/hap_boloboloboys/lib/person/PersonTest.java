package com.hap.hap_boloboloboys.lib.person;

import com.hap.hap_boloboloboys.lib.card.Card;
import com.hap.hap_boloboloboys.lib.card.Carnivore;
import com.hap.hap_boloboloboys.lib.card.Herbivore;
import com.hap.hap_boloboloboys.lib.field.Ladang;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void testPersonMethods() throws Exception {
        Inventory inventory = new Inventory(40);
        Deck deck = new Deck(6);
        Ladang ladang = new Ladang();

        Person person1 = new Person("haiya");
        Person person2 = new Person("haiyu", inventory);
        Person person3 = new Person("heiheih", deck);
        Person person4 = new Person("awikwok", ladang);
        Person person5 = new Person("benmamim", inventory, deck, ladang);

        assertEquals("haiya", person1.getName());
        assertEquals(0, person1.getWealth());
        assertNotNull(person1.getInventory());
        assertNotNull(person1.getDeck());
        assertNotNull(person1.getShuffledCard());
        assertNotNull(person1.getLadang());

        assertEquals("haiyu", person2.getName());
        assertEquals(0, person2.getWealth());
        assertEquals(inventory, person2.getInventory());
        assertNotNull(person2.getDeck());
        assertNotNull(person2.getShuffledCard());
        assertNotNull(person2.getLadang());

        assertEquals("heiheih", person3.getName());
        assertEquals(0, person3.getWealth());
        assertNotNull(person3.getInventory());
        assertEquals(deck, person3.getDeck());
        assertNotNull(person3.getShuffledCard());
        assertNotNull(person3.getLadang());

        assertEquals("awikwok", person4.getName());
        assertEquals(0, person4.getWealth());
        assertNotNull(person4.getInventory());
        assertNotNull(person4.getDeck());
        assertNotNull(person4.getShuffledCard());
        assertEquals(ladang, person4.getLadang());

        assertEquals("benmamim", person5.getName());
        assertEquals(0, person5.getWealth());
        assertEquals(inventory, person5.getInventory());
        assertEquals(deck, person5.getDeck());
        assertNotNull(person5.getShuffledCard());
        assertEquals(ladang, person5.getLadang());

        person1.setName("pigi");
        person1.setWealth(100);
        person1.setInventory(new Inventory(50));
        person1.setDeck(new Deck(8));
        List<Card> newShuffledCard = new ArrayList<>();
        newShuffledCard.add(new Herbivore("SAPI"));
        newShuffledCard.add(new Carnivore("HIU_DARAT"));
        person1.setShuffledCard(newShuffledCard);
        person1.setLadang(new Ladang());

        assertEquals("pigi", person1.getName());
        assertEquals(100, person1.getWealth());
        assertNotNull(person1.getInventory());
        assertNotNull(person1.getDeck());
        assertEquals(2, person1.getShuffledCard().size());
        assertNotNull(person1.getLadang());

        person1.addWealth(50);
        assertEquals(150, person1.getWealth());
        person1.reduceWealth(75);
        assertEquals(75, person1.getWealth());

        // Menambah dan mengurangi kekayaan
        person1.addWealth(50);
        assertEquals(125, person1.getWealth());
        person1.reduceWealth(75);
        assertEquals(50, person1.getWealth());

        // Pengujian metode shuffleCard
        Card card1 = new Herbivore("SAPI");
        Card card2 = new Herbivore("SAPI");
        Card card3 = new Herbivore("SAPI");

        inventory.add(card1);
        inventory.add(card2);
        inventory.add(card3);
        
        person1.setInventory(inventory);
        person1.shuffleCard();
        assertEquals(3, person1.getShuffledCard().size());
        assertTrue(person1.getCardFromShuffled(1) != null);
        assertTrue(person1.getCardFromShuffled(2) != null);
        assertTrue(person1.getCardFromShuffled(0) != null);

        Card chosenCard = person1.getCardFromShuffled(1);
        person1.putToDeck(chosenCard);
        assertEquals(1, person1.getDeck().calculateSize());

        // person1.deleteFromShuffled(0);
        // assertEquals(2, person1.getShuffledCard().size());
    }
}
