package com.hap.hap_boloboloboys.lib.card;

public class Main {
    public static void main(String[] args) {
        // Test harvesting creatures into products
        Animal creature1 = new Carnivore("HIU_DARAT");
        Animal creature2 = new Herbivore("SAPI");
        Animal creature3 = new Herbivore("DOMBA");
        Animal creature4 = new Herbivore("KUDA");
        Animal creature5 = new Omnivore("AYAM");
        Animal creature6 = new Omnivore("BERUANG");
        Plant creature7 = new Plant("BIJI_JAGUNG");
        Plant creature8 = new Plant("BIJI_LABU");
        Plant creature9 = new Plant("BIJI_STROBERI");

        // set creature untill harvestable
        creature1.setWeight(20);
        creature2.setWeight(10);
        creature3.setWeight(12);
        creature4.setWeight(14);
        creature5.setWeight(5);
        creature6.setWeight(25);
        creature7.setAge(3);
        creature8.setAge(5);
        creature9.setAge(4);

        // Harvest creatures
        Product product1 = creature1.harvest();
        Product product2 = creature2.harvest();
        Product product3 = creature3.harvest();
        Product product4 = creature4.harvest();
        Product product5 = creature5.harvest();
        Product product6 = creature6.harvest();
        Product product7 = creature7.harvest();
        Product product8 = creature8.harvest();
        Product product9 = creature9.harvest();

        // Display harvested products
        System.out.println("Harvested Product 1: " + product1.getCardName());
        System.out.println("Price: " + product1.getPrice());
        System.out.println("Added Weight: " + product1.getAddedWeight());

        System.out.println("Harvested Product 2: " + product2.getCardName());
        System.out.println("Price: " + product2.getPrice());
        System.out.println("Added Weight: " + product2.getAddedWeight());

        System.out.println("Harvested Product 3: " + product3.getCardName());
        System.out.println("Price: " + product3.getPrice());
        System.out.println("Added Weight: " + product3.getAddedWeight());

        System.out.println("Harvested Product 4: " + product4.getCardName());
        System.out.println("Price: " + product4.getPrice());
        System.out.println("Added Weight: " + product4.getAddedWeight());

        System.out.println("Harvested Product 5: " + product5.getCardName());
        System.out.println("Price: " + product5.getPrice());
        System.out.println("Added Weight: " + product5.getAddedWeight());

        System.out.println("Harvested Product 6: " + product6.getCardName());
        System.out.println("Price: " + product6.getPrice());
        System.out.println("Added Weight: " + product6.getAddedWeight());

        System.out.println("Harvested Product 7: " + product7.getCardName());
        System.out.println("Price: " + product7.getPrice());
        System.out.println("Added Weight: " + product7.getAddedWeight());

        System.out.println("Harvested Product 8: " + product8.getCardName());
        System.out.println("Price: " + product8.getPrice());
        System.out.println("Added Weight: " + product8.getAddedWeight());

        System.out.println("Harvested Product 9: " + product9.getCardName());
        System.out.println("Price: " + product9.getPrice());
        System.out.println("Added Weight: " + product9.getAddedWeight());
    }
}
