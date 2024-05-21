package com.hap.hap_boloboloboys.lib.card;

public class Product extends Card {
    private int price;
    private int addedWeight;

    public Product(String cardName) {
        super(cardName, "");
        setProductDetails(cardName);
    }

    private void setProductDetails(String cardName) {
        switch (cardName) {
            case "Sirip Hiu":
                this.price = 500;
                this.addedWeight = 12;
                break;
            case "Susu":
                this.price = 100;
                this.addedWeight = 4;
                break;
            case "Daging Domba":
                this.price = 120;
                this.addedWeight = 6;
                break;
            case "Daging Kuda":
                this.price = 150;
                this.addedWeight = 8;
                break;
            case "Telur":
                this.price = 50;
                this.addedWeight = 2;
                break;
            case "Daging Beruang":
                this.price = 500;
                this.addedWeight = 12;
                break;
            case "Jagung":
                this.price = 150;
                this.addedWeight = 3;
                break;
            case "Labu":
                this.price = 500;
                this.addedWeight = 10;
                break;
            case "Stroberi":
                this.price = 350;
                this.addedWeight = 5;
                break;
            default:
                throw new IllegalArgumentException("Unknown product: " + cardName);
        }
        this.setImgPath("path/to/" + cardName.replace(" ", "") + ".png");
    }

    public int getPrice() {
        return price;
    }

    public int getAddedWeight() {
        return addedWeight;
    }
}
