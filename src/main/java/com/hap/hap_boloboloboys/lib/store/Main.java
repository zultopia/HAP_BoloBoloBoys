package com.hap.hap_boloboloboys.lib.store;

// run di root directory gitHub\HAP_BoloBoloBoys
// javac -d target src/main/java/com/hap/hap_boloboloboys/lib/util/Pair.java src/main/java/com/hap/hap_boloboloboys/lib/card/*.java src/main/java/com/hap/hap_boloboloboys/lib/store/*.java
// java -cp target com.hap.hap_boloboloboys.lib.store.Main


import com.hap.hap_boloboloboys.lib.card.*;;

public class Main {
    public static void main(String[] args) {
        // Membuat instance store
        Store store = new Store();

        // Membuat beberapa produk
        Product product1 = new Product("Sirip Hiu");
        Product product2 = new Product("Susu");
        Product product3 = new Product("Daging Domba");

        // Menambahkan produk ke store
        store.addItem("Sirip Hiu", product1, 10);
        store.addItem("Susu", product2, 20);
        store.addItem("Daging Domba", product3, 5);

        // Menampilkan inventaris toko
        store.displayInventory();

        // Menghapus beberapa produk dari store
        store.removeItem("Sirip Hiu", 5);
        store.removeItem("Susu", 20);
        store.removeItem("Daging Domba", 1);

        // Menampilkan inventaris toko setelah penghapusan
        System.out.println("\nAfter removal:");
        store.displayInventory();

        // Menampilkan informasi pembelian dan penjualan produk
        System.out.println("\nCan buy Sirip Hiu: " + store.canBuyProduct(product1));
        System.out.println("Can buy Susu: " + store.canBuyProduct(product2));
    }
}
