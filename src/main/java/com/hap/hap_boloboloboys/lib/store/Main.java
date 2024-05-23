package com.hap.hap_boloboloboys.lib.store;

// run di root directory gitHub\HAP_BoloBoloBoys
// javac -d target src/main/java/com/hap/hap_boloboloboys/lib/util/Pair.java src/main/java/com/hap/hap_boloboloboys/lib/card/*.java src/main/java/com/hap/hap_boloboloboys/lib/store/*.java
// java -cp target com.hap.hap_boloboloboys.lib.store.Main


import com.hap.hap_boloboloboys.lib.card.*;;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        Product product1 = new Product("SIRIP_HIU");
        Product product2 = new Product("SUSU");
        Product product3 = new Product("DAGING_DOMBA");
        Product product4 = new Product("DAGING_KUDA");
        Product product5 = new Product("TELUR");
        Product product6 = new Product("DAGING_BERUANG");

        store.addItem("SIRIP_HIU", product1, 10);
        store.addItem("SUSU", product2, 20);
        store.addItem("DAGING_DOMBA", product3, 5);
        store.addItem("DAGING_KUDA", product4, 10);
        store.addItem("TELUR", product5, 20);
        store.addItem("DAGING_BERUANG", product6, 5);

        store.displayInventory();

        store.removeItem("SIRIP_HIU", 5);
        store.removeItem("SUSU", 20);
        store.removeItem("DAGING_DOMBA", 1);

        System.out.println("\nAfter removal:");
        store.displayInventory();

        System.out.println("\nCan buy SIRIP_HIU: " + store.canBuyProduct(product1));
        System.out.println("Can buy SUSU: " + store.canBuyProduct(product2));
    }
}
