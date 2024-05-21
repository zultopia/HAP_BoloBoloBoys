package com.hap.hap_boloboloboys.lib.person;

public class Person {
    private String name;
    private int wealth = 0;
    private Inventory inventory = new Inventory(40);
    // TODO ladang
    // TODO active deck

    public Person(String name) {
        this.name = name;
    }

    /*GETTER*/
    public String getName() {return this.name;}
    public int getWealth() {return this.wealth;}
    public Inventory getInventory() {return this.inventory;}

    /*SETTER*/
    public void setName(String name) {this.name = name;}
    public void setWealth(int wealth) {this.wealth = wealth;}
    public void setInventory(Inventory inventory) {this.inventory = inventory;}
}
