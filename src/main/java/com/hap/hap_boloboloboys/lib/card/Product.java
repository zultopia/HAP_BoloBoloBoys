package com.hap.hap_boloboloboys.lib.card;

public class Product extends Card {
    private int price;
    private int addedWeight;

    public Product(String cardName, String imgPath, int price, int addedWeight) {
        super(cardName, imgPath);
        this.price = price;
        this.addedWeight = addedWeight;
    }

    public int getPrice() {
        return price;
    }

    public int getAddedWeight() {
        return addedWeight;
    }
}
