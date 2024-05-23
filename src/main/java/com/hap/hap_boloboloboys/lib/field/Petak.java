package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.*;

public class Petak implements IPetak {
    private Card kartu;

    public Petak() {
        this.kartu = null;
    }

    @Override
    public boolean isEmptyCard() {
        return kartu == null;
    }

    @Override
    public Card getKartu() {
        return kartu;
    }

    @Override
    public void setKartu(Card kartu) {
        this.kartu = kartu;
    }

    @Override
    public void cetakInfo() throws FieldException {
        if (kartu != null) {
            System.out.println("Nama: " + kartu.getCardName());
            if (kartu instanceof Animal) {
                System.out.println("Berat: " + ((Animal) kartu).getWeight() + "/" + ((Animal) kartu).getHarvestTarget());
            } else if (kartu instanceof Plant) {
                System.out.println("Umur: " + ((Plant) kartu).getAge() + "/" + ((Plant) kartu).getHarvestTarget());
            } else if (kartu instanceof Product) {
                System.out.println("Menambah " + ((Product) kartu).getAddedWeight() + " berat");
                return;
            } else {
                throw new FieldException("Kartu tidak valid");
            }
            System.out.print("Active Items: ");
            for (int i = 0; i < 6; i++) {
                if (((Creature) kartu).getEffectValue(i) == 0) continue;
                System.out.print(((Creature) kartu).getEffectName(i) + "(" + ((Creature) kartu).getEffectValue(i) + ")");
                if (i < 5 && ((Creature) kartu).getEffectValue(i + 1) > 0) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            throw new FieldException("Petak kosong");
        }
        System.out.println();
    }
}
