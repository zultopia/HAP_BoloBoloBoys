package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.Card;

public interface IPetak {
    boolean isEmptyCard();
    Card getKartu();
    void setKartu(Card kartu);
    void cetakInfo() throws FieldException;
}
