package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.*;
import java.util.ArrayList;
import java.util.List;

public class Petak {
    private Creature kartu;
    private List<Item> activeItems;

    public Petak() {
        this.kartu = null;
        this.activeItems = new ArrayList<>();
    }

    public boolean isEmpty() {
        return kartu == null;
    }

    public Creature getKartu() {
        return kartu;
    }

    public void setKartu(Creature kartu) {
        this.kartu = kartu;
    }

    public void addItem(Item item) {
        activeItems.add(item);
    }

    public void removeItem(Item item) {
        activeItems.remove(item);
    }

    public List<Item> getActiveItems() {
        return activeItems;
    }

    public void setUmur(int umur) {
        if (kartu instanceof Plant) {
            ((Plant) kartu).setAge(umur);
        }
    }

    public void setBerat(int berat) {
        if (kartu instanceof Animal) {
            ((Animal) kartu).setWeight(berat);
        }
    }

    public void cetakInfo() {
        if (kartu != null) {
            System.out.println("Nama: " + kartu.getCardName());
            if (kartu instanceof Animal) {
                System.out.println("Berat: " + ((Animal) kartu).getWeight() + "/" + ((Animal) kartu).getHarvestTarget());
            } else if (kartu instanceof Plant) {
                System.out.println("Umur: " + ((Plant) kartu).getAge() + "/" + ((Plant) kartu).getHarvestTarget());
            }
            System.out.println("Active Items: ");
            for (int i = 0; i < 6; i++) {
                System.out.println(i + ". " + kartu.getEffectName(i) + " : " + kartu.getEffectValue(i));
            }
        } else {
            System.out.println("Petak kosong");
        }
    }
}
