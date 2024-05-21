package com.hap.hap_boloboloboys.lib.card;

public abstract class Card {
    private String cardName;
    private String imgPath;

    public Card(String cardName, String imgPath) {
        this.cardName = cardName;
        this.imgPath = imgPath;
    }

    public String getCardName() {
        return cardName;
    }

    public String getImgPath() {
        return imgPath;
    }

    // setImgPath
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
