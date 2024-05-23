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
                break;
            case "SUSU":
                setCardName("Susu");
                this.price = 100;
                this.addedWeight = 4;
                break;
            case "DAGING_DOMBA":
                setCardName("Daging Domba");
                this.price = 120;
                this.addedWeight = 6;
                break;
            case "DAGING_KUDA":
                setCardName("Daging Kuda");
                this.price = 150;
                this.addedWeight = 8;
                break;
            case "TELUR":
                setCardName("Telur");
                this.price = 50;
                this.addedWeight = 2;
                break;
            case "DAGING_BERUANG":
                setCardName("Daging Beruang");
                this.price = 500;
                this.addedWeight = 12;
                break;
            case "JAGUNG":
                setCardName("Jagung");
                this.price = 150;
                this.addedWeight = 3;
                break;
            case "LABU":
                setCardName("Labu");
                this.price = 500;
                this.addedWeight = 10;
                break;
            case "STROBERI":
                setCardName("Stroberi");
                this.price = 350;
                this.addedWeight = 5;
                break;
            default:
                throw new IllegalArgumentException("Unknown product: " + getCardName());
        }
        this.setImgPath("path/to/" + getCardName().replace(" ", "") + ".png");
    }

    public int getPrice() {
        return price;
    }

    public int getAddedWeight() {
        return addedWeight;
    }
}
