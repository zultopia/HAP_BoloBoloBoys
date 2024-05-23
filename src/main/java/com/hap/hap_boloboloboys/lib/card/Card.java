package com.hap.hap_boloboloboys.lib.card;

public abstract class Card {
    private String code;
    private String cardName;
    private String imgPath;

    public Card(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getCardName() {
        return cardName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
