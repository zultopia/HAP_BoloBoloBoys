package com.hap.hap_boloboloboys.lib.field;

import com.hap.hap_boloboloboys.lib.card.*;

public class Ladang {
    private Petak[][] grid;
    private int turn;

    public Ladang() {
        this.grid = new Petak[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Petak();
            }
        }
        this.turn = 0;
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        turn++;
        // Update plants and animals
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Petak petak = grid[i][j];
                if (!petak.isEmpty()) {
                    Card kartu = petak.getKartu();
                    if (kartu instanceof Plant) {
                        petak.setUmur(((Plant) petak.getKartu()).getAge() + 1);
                    } else if (kartu instanceof Animal) {
                        // Handle weight increase or other periodic effects
                    }
                }
            }
        }
    }

    public Petak getPetak(int row, int col) {
        if (row >= 0 && row < 4 && col >= 0 && col < 5) {
            return grid[row][col];
        }
        return null;
    }

    public void plantKartu(int row, int col, Creature kartu) {
        Petak petak = getPetak(row, col);
        if (petak != null && petak.isEmpty()) {
            petak.setKartu(kartu);
            if (kartu instanceof Plant) {
                petak.setUmur(((Plant) kartu).getAge());
            } else if (kartu instanceof Animal) {
                petak.setBerat(((Animal) kartu).getWeight());
            }
        }
    }

    public void harvestKartu(int row, int col) {
        Petak petak = getPetak(row, col);
        if (petak != null && !petak.isEmpty()) {
            Creature kartu = petak.getKartu();
            if (kartu instanceof Plant && ((Plant) kartu).canHarvest()) {
                // Harvest plant
                petak.setKartu(null);
            } else if (kartu instanceof Animal && ((Animal) kartu).canHarvest()) {
                // Harvest animal
                petak.setKartu(null);
            }
        }
    }

    public void applyItem(int row, int col, Item item) {
        Petak petak = getPetak(row, col);
        if (petak != null && !petak.isEmpty()) {
            item.applyEffect(petak.getKartu());
        }
    }

    public void cetakInfo() {
        // System.out.println("Turn: " + turn);
        System.out.println("   ==========[ Ladang ]===========");

        System.out.print("   +");
        for (int i = 0; i < 5; i++) {
            System.out.print("-----+");
        }
        System.out.println();

        for (int i = 0; i < 4; i++) {
            System.out.printf("   |");
            for (int j = 0; j < 5; j++) {
                Petak petak = grid[i][j];
                if (!petak.isEmpty()) {
                    System.out.print("  " + petak.getKartu().getCardName().charAt(0) + "  |");
                } else {
                    System.out.print("     |");
                }
            }
            System.out.println();
            System.out.print("   +");
            for (int k = 0; k < 5; k++) {
                System.out.print("-----+");
            }
            System.out.println();
        }
        System.out.println();
    }
}
