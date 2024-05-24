package com.hap.hap_boloboloboys.lib.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void testProduct() {
        Product plant = new Product("JAGUNG");
        assertEquals(150, plant.getPrice());
    }

    @Test
    void testSetProductDetails_SiripHiu() {
        Product product = new Product("SIRIP_HIU");
        assertEquals("Sirip Hiu", product.getCardName());
        assertEquals(500, product.getPrice());
        assertEquals(12, product.getAddedWeight());
        assertEquals("/card/produk/produk6.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_Susu() {
        Product product = new Product("SUSU");
        assertEquals("Susu", product.getCardName());
        assertEquals(100, product.getPrice());
        assertEquals(4, product.getAddedWeight());
        assertEquals("/card/produk/produk8.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_DagingDomba() {
        Product product = new Product("DAGING_DOMBA");
        assertEquals("Daging Domba", product.getCardName());
        assertEquals(120, product.getPrice());
        assertEquals(6, product.getAddedWeight());
        assertEquals("/card/produk/produk2.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_DagingKuda() {
        Product product = new Product("DAGING_KUDA");
        assertEquals("Daging Kuda", product.getCardName());
        assertEquals(150, product.getPrice());
        assertEquals(8, product.getAddedWeight());
        assertEquals("/card/produk/produk3.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_Telur() {
        Product product = new Product("TELUR");
        assertEquals("Telur", product.getCardName());
        assertEquals(50, product.getPrice());
        assertEquals(2, product.getAddedWeight());
        assertEquals("/card/produk/produk9.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_DagingBeruang() {
        Product product = new Product("DAGING_BERUANG");
        assertEquals("Daging Beruang", product.getCardName());
        assertEquals(500, product.getPrice());
        assertEquals(12, product.getAddedWeight());
        assertEquals("/card/produk/produk1.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_Jagung() {
        Product product = new Product("JAGUNG");
        assertEquals("Jagung", product.getCardName());
        assertEquals(150, product.getPrice());
        assertEquals(3, product.getAddedWeight());
        assertEquals("/card/produk/produk4.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_Labu() {
        Product product = new Product("LABU");
        assertEquals("Labu", product.getCardName());
        assertEquals(500, product.getPrice());
        assertEquals(10, product.getAddedWeight());
        assertEquals("/card/produk/produk5.png", product.getImgPath());
    }

    @Test
    void testSetProductDetails_Stroberi() {
        Product product = new Product("STROBERI");
        assertEquals("Stroberi", product.getCardName());
        assertEquals(350, product.getPrice());
        assertEquals(5, product.getAddedWeight());
        assertEquals("/card/produk/produk7.png", product.getImgPath());
    }
    
    @Test
    public void testGetPrice() {
        Product plant = new Product("LABU");
        assertEquals(500, plant.getPrice());
    }
    
    @Test
    public void testGetAddWeight() {
        Product plant = new Product("LABU");
        assertEquals(10, plant.getAddedWeight());
    }
    
}
