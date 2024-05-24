package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.*;

public class Ladang {
    private Petak[][] grid;

    public Ladang() {
        this.grid = new Petak[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Petak();
            }
        }
    }

    public Petak getPetak(int row, int col) {
        if (row >= 0 && row < getLadangRow() && col >= 0 && col < getLadangColumn()) {
            return grid[row][col];
        }
        return null;
    }

    public Card takeCard(int row, int col) {
        Petak petak = getPetak(row, col);
        if (petak != null) {
            Card kartu = petak.getKartu();
            petak.setKartu(null);
            return kartu;
        }
        return null;
    }

    public int getLadangRow() {
        return grid.length;
    }

    public int getLadangColumn() {
        return grid[0].length;
    }

    public void plantKartu(int row, int col, Card kartu) throws Exception {
        Petak petak = getPetak(row, col);
        if (petak != null) {
            if (petak.isEmptyCard()) {
                if (kartu instanceof Plant) {
                    petak.setKartu(kartu);
                    // ((Plant) petak.getKartu()).setAge(0);
                } else if (kartu instanceof Animal) {
                    petak.setKartu(kartu);
                    // ((Animal) petak.getKartu()).setWeight(0);
                } else {
                    // throw new Exception("Kartu yang ditanam bukan tanaman atau hewan");
                    System.out.println("Kartu yang ditanam bukan tanaman atau hewan");
                }
            } else {
                if (petak.getKartu() instanceof Plant) {
                    if (kartu instanceof Item) {
                        ((Item) kartu).applyEffect((Creature) petak.getKartu());
                    } else {
                        // throw new Exception("Kartu tidak bisa ditanam");
                        System.out.println("Kartu tidak bisa ditanam");
                    }
                } else if (petak.getKartu() instanceof Animal) {
                    if (kartu instanceof Item) {
                        ((Item) kartu).applyEffect((Creature) petak.getKartu());
                    }  else if (kartu instanceof Product) {
                        ((Animal) petak.getKartu()).feed((Product) kartu);
                    } else {
                        // throw new Exception("Kartu tidak bisa ditanam");
                        System.out.println("Kartu tidak bisa ditanam");
                    }
                } else {
                    // throw new Exception("Petak sudah terisi");
                    System.out.println("Petak sudah terisi");
                }
            }
        } else {
            // throw new Exception("Petak tidak valid");
            System.out.println("Petak tidak valid");
        }
    }

    public void loadplantKartu(int row, int col, Card kartu) throws Exception {
        Petak petak = getPetak(row, col);
        if (petak != null) {
            if (petak.isEmptyCard()) {
                if (kartu instanceof Plant) {
                    petak.setKartu(kartu);
                    // ((Plant) petak.getKartu()).setAge(0);
                } else if (kartu instanceof Animal) {
                    petak.setKartu(kartu);
                    // ((Animal) petak.getKartu()).setWeight(0);
                } else if (kartu instanceof Product) {
                    petak.setKartu(kartu);
                } else {
                    // throw new Exception("Kartu yang ditanam bukan tanaman atau hewan");
                    System.out.println("Kartu yang ditanam bukan tanaman atau hewan atau produk dari load!");
                }
            } else {
                if (petak.getKartu() instanceof Plant) {
                    if (kartu instanceof Item) {
                        ((Item) kartu).applyEffect((Creature) petak.getKartu());
                    } else {
                        // throw new Exception("Kartu tidak bisa ditanam");
                        System.out.println("Kartu tidak bisa ditanam");
                    }
                } else if (petak.getKartu() instanceof Animal) {
                    if (kartu instanceof Item) {
                        ((Item) kartu).applyEffect((Creature) petak.getKartu());
                    }  else if (kartu instanceof Product) {
                        ((Animal) petak.getKartu()).feed((Product) kartu);
                    } else {
                        // throw new Exception("Kartu tidak bisa ditanam");
                        System.out.println("Kartu tidak bisa ditanam");
                    }
                } else {
                    // throw new Exception("Petak sudah terisi");
                    System.out.println("Petak sudah terisi");
                }
            }
        } else {
            // throw new Exception("Petak tidak valid");
            System.out.println("Petak tidak valid");
        }
    }


    public void harvestKartu(int row, int col) throws FieldException {
        Petak petak = getPetak(row, col);
        if (petak != null && !petak.isEmptyCard()) {
            Card kartu = petak.getKartu();
            String code = kartu.getCode();
            petak.cetakInfo();
            if (kartu instanceof Plant && ((Plant) kartu).canHarvest()) {
                Product product = null;
                // Harvest plant
                switch (code) {
                    case "BIJI_JAGUNG":
                        product = new Product("JAGUNG");
                        break;
                    case "BIJI_LABU":
                        product = new Product("LABU");
                        break;
                    case "BIJI_STROBERI":
                        product = new Product("STROBERI");
                        break;
                    default:
                        break;
                }
                System.out.println("Berhasil memanen " + product.getCardName());
                petak.setKartu(product);
            } else if (kartu instanceof Animal && ((Animal) kartu).canHarvest()) {
                Product product = null;
                // Harvest animal
                switch (code) {
                    case "DOMBA":
                        product = new Product("DAGING_DOMBA");
                        break;
                    case "KUDA":
                        product = new Product("DAGING_KUDA");
                        break;
                    case "BERUANG":
                        product = new Product("DAGING_BERUANG");
                        break;
                    case "HIU_DARAT":
                        product = new Product("SIRIP_HIU");
                        break;
                    case "SAPI":
                        product = new Product("SUSU");
                        break;
                    case "AYAM":
                        product = new Product("TELUR");
                        break;
                    default:
                        break;
                }
                petak.setKartu(product);
            } else {
                // throw new FieldException("Kartu belum bisa dipanen");
                System.out.println("Kartu belum bisa dipanen");
            }
        } else {
            throw new FieldException("Tidak dapat memanen");
        }
    }

    // Method ini mirip sama plant Item ke Creature
    public void applyItem(int row, int col, Item item) throws FieldException {
        Petak petak = getPetak(row, col);
        if (petak != null && !petak.isEmptyCard()) {
            if (petak.getKartu() instanceof Creature) {
                item.applyEffect((Creature) petak.getKartu());
            } else {
                throw new FieldException("Tidak dapat menggunakan item pada selain hewan dan tanaman");
            }
        } else {
            throw new FieldException("Tidak dapat menggunakan item pada petak kosong");
        }
    }

    public void resizeLadang(int row, int col) {
        Petak[][] newGrid = new Petak[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i < 4 && j < 5) {
                    newGrid[i][j] = grid[i][j];
                } else {
                    newGrid[i][j] = new Petak();
                }
            }
        }
        grid = newGrid;
    }

    public int countCard() {
        int count = 0;
        for (int i = 0; i < getLadangRow(); i++) {
            for (int j = 0; j < getLadangColumn(); j++) {
                if (!grid[i][j].isEmptyCard()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void cetakInfo() {
        System.out.println("   ==========[ Ladang ]===========");

        System.out.print("   +");
        for (int i = 0; i < getLadangColumn(); i++) {
            System.out.print("-----+");
        }
        System.out.println();

        for (int i = 0; i < getLadangRow(); i++) {
            System.out.printf("   |");
            for (int j = 0; j < getLadangColumn(); j++) {
                Petak petak = grid[i][j];
                if (petak != null && !petak.isEmptyCard()) {
                    System.out.print("  " + petak.getKartu().getCardName().charAt(0) + "  |");
                } else {
                    System.out.print("     |");
                }
            }
            System.out.println();
            System.out.print("   +");
            for (int k = 0; k < getLadangColumn(); k++) {
                System.out.print("-----+");
            }
            System.out.println();
        }
        System.out.println();
    }
}
