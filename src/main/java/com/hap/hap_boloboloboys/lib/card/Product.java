package com.hap.hap_boloboloboys.lib.card;

public class Product extends Card {
    private int price;
    private int addedWeight;

    public Product(String code) {
        super(code);
        setProductDetails(code);
    }

    private void setProductDetails(String code) {
        switch (code) {
            case "SIRIP_HIU":
                setCardName("Sirip Hiu");
                this.price = 500;
                this.addedWeight = 12;
                this.setImgPath("/card/produk/produk6.png");
                break;
            case "SUSU":
                setCardName("Susu");
                this.price = 100;
                this.addedWeight = 4;
                this.setImgPath("/card/produk/produk8.png");
                break;
            case "DAGING_DOMBA":
                setCardName("Daging Domba");
                this.price = 120;
                this.addedWeight = 6;
                this.setImgPath("/card/produk/produk2.png");
                break;
            case "DAGING_KUDA":
                setCardName("Daging Kuda");
                this.price = 150;
                this.addedWeight = 8;
                this.setImgPath("/card/produk/produk3.png");
                break;
            case "TELUR":
                setCardName("Telur");
                this.price = 50;
                this.addedWeight = 2;
                this.setImgPath("/card/produk/produk9.png");
                break;
            case "DAGING_BERUANG":
                setCardName("Daging Beruang");
                this.price = 500;
                this.addedWeight = 12;
                this.setImgPath("/card/produk/produk1.png");
                break;
            case "JAGUNG":
                setCardName("Jagung");
                this.price = 150;
                this.addedWeight = 3;
                this.setImgPath("/card/produk/produk4.png");
                break;
            case "LABU":
                setCardName("Labu");
                this.price = 500;
                this.addedWeight = 10;
                this.setImgPath("/card/produk/produk5.png");
                break;
            case "STROBERI":
                setCardName("Stroberi");
                this.price = 350;
                this.addedWeight = 5;
                this.setImgPath("/card/produk/produk7.png");
                break;
        }
    }

    public int getPrice() {
        return price;
    }

    public int getAddedWeight() {
        return addedWeight;
    }
}
