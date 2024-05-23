package com.hap.hap_boloboloboys.lib.config;

import java.util.HashSet;
import java.util.Set;

public class ListCode {
    public static Set<String> ITEM = new HashSet<>();
    public static Set<String> PRODUCT = new HashSet<>();
    public static Set<String> PLANT = new HashSet<>();
    public static Set<String> CARNIVORE = new HashSet<>();
    public static Set<String> HERBIVORE = new HashSet<>();
    public static Set<String> OMNIVORE = new HashSet<>();

    static {
        // Initialize EFFECT set
        ITEM.addAll(Set.of("ACCELERATE", "DELAY", "INSTANT_HARVEST", "DESTROY", "PROTECT", "TRAP"));

        // Initialize PRODUCT set
        PRODUCT.addAll(Set.of("SIRIP_HIU", "SUSU", "DAGING_DOMBA", "DAGING_KUDA", "TELUR", "DAGING_BERUANG", "JAGUNG", "LABU", "STROBERI"));
        
        // Initialize PLANT set
        PLANT.addAll(Set.of("BIJI_JAGUNG", "BIJI_LABU", "BIJI_STROBERI"));
        
        // Initialize CARNIVORE set
        CARNIVORE.addAll(Set.of("HIU_DARAT"));

        // Initialize HERBIVORE set
        HERBIVORE.addAll(Set.of("SAPI", "DOMBA", "KUDA"));
        
        // Initialize KARNIVORE set
        OMNIVORE.addAll(Set.of("AYAM", "BERUANG"));
    }
}