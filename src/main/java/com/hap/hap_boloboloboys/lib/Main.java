package com.hap.hap_boloboloboys.lib;

import com.hap.hap_boloboloboys.lib.card.*;
import com.hap.hap_boloboloboys.lib.field.*;

public class Main {
  public static void main(String[] args) throws Exception {
    Plant labu = new Plant("BIJI_LABU");
    Plant jagung = new Plant("BIJI_JAGUNG");
    Plant stroberi = new Plant("BIJI_STROBERI");

    Animal hiuDarat = new Carnivore("HIU_DARAT");
    Animal sapi = new Herbivore("SAPI");
    Animal domba = new Herbivore("DOMBA");
    Animal kuda = new Herbivore("KUDA");
    Animal ayam = new Omnivore("AYAM");
    Animal beruang = new Omnivore("BERUANG");

    Item accelerate = new Accelerate();
    Item Delay = new Delay();
    Item instantHarvest = new InstantHarvest();

    Ladang ladang = new Ladang();
    ladang.plantKartu(0, 0, labu);
    ladang.plantKartu(0, 1, jagung);
    ladang.plantKartu(0, 2, stroberi);

    ladang.plantKartu(1, 0, hiuDarat);
    ladang.plantKartu(1, 1, sapi);
    ladang.plantKartu(1, 2, domba);

    ladang.plantKartu(2, 0, kuda);
    ladang.plantKartu(2, 1, ayam);
    ladang.plantKartu(2, 2, beruang);

    labu.setAge(5);
    System.out.println("Labu : " + labu.getAge());
    ladang.plantKartu(0, 0, Delay);
    Delay.applyEffect(labu);
    Petak petak = ladang.getPetak(0, 0);
    System.out.println("Labu : " + labu.getAge());
    System.out.println(petak.getKartu().getCardName() + " sudah di-apply Delay\n");
    

    System.out.println("Jagung : " + jagung.getAge());
    ladang.plantKartu(0, 1, accelerate);
    accelerate.applyEffect(jagung);
    petak = ladang.getPetak(0, 1);
    System.out.println("Jagung : " + jagung.getAge());
    System.out.println(petak.getKartu().getCardName() + " sudah di-apply Accelerate\n");

    System.out.println("Ayam : " + ayam.getWeight());
    ladang.plantKartu(2, 1, instantHarvest);
    petak = ladang.getPetak(2, 1);
    System.out.println("Ayam : " + ayam.getWeight());
    System.out.println(petak.getKartu().getCardName() + " sudah di-apply Instant Harvest\n");
    
    petak = ladang.getPetak(0, 0);
    petak.cetakInfo();
    ladang.cetakInfo();

    if (((Creature) ladang.getPetak(0, 0).getKartu()).canHarvest()) {
      System.out.println(ladang.getPetak(0, 0).getKartu().getCardName() + " Bisa di-harvest");
    } else {
      System.out.println(ladang.getPetak(0, 0).getKartu().getCardName() + " Tidak bisa di-harvest");
    }
    if (ladang.getPetak(0, 0).getKartu() instanceof Plant) {
      System.out.println("Ini tanaman");
    } else {
      System.out.println("Ini bukan tanaman");
    }
    ladang.getPetak(0, 0).cetakInfo();

    if (ladang.getPetak(0, 1).getKartu() instanceof Plant && ((Plant) ladang.getPetak(0, 1).getKartu()).canHarvest()) {
      System.out.println(ladang.getPetak(0, 1).getKartu().getCardName() + " Bisa di-harvest");
    } else {
      System.out.println(ladang.getPetak(0, 1).getKartu().getCardName() + " Tidak bisa di-harvest");
    }
    ladang.getPetak(0, 1).cetakInfo();

    if (ladang.getPetak(2, 1).getKartu() instanceof Animal && ((Animal) ladang.getPetak(2, 1).getKartu()).canHarvest()) {
      System.out.println(ladang.getPetak(2, 1).getKartu().getCardName() + " Bisa di-harvest");
    } else {
      System.out.println(ladang.getPetak(2, 1).getKartu().getCardName() + " Tidak bisa di-harvest");
    }
    ladang.getPetak(2, 1).cetakInfo();

    ladang.harvestKartu(0, 1);
    ladang.cetakInfo();
    ladang.getPetak(0, 1).cetakInfo();
    
    ladang.harvestKartu(0, 0);
    ladang.cetakInfo();
    ladang.getPetak(0, 0).cetakInfo();
    
    ladang.harvestKartu(2, 1);
    ladang.cetakInfo();
    ladang.getPetak(2, 1).cetakInfo();
    
    Card produk = ladang.takeCard(0, 1);

    ladang.plantKartu(0, 1, Delay);
    ladang.cetakInfo();
    ladang.plantKartu(0, 2, Delay);
    ladang.plantKartu(0, 2, accelerate);
    ladang.plantKartu(2, 2, produk);
    ladang.plantKartu(0, 2, instantHarvest);
    ladang.getPetak(0, 2).cetakInfo();
    ladang.getPetak(2, 2).cetakInfo();
    
    while (!((Creature) ladang.getPetak(2, 2).getKartu()).canHarvest()) {
      ladang.plantKartu(2, 2, produk);
    }
    ladang.getPetak(2, 2).cetakInfo();

    ladang.harvestKartu(2, 2);
    produk = ladang.takeCard(2, 2);

    ladang.cetakInfo();
  }
}
