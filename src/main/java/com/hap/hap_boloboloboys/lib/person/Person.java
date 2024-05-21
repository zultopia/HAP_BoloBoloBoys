package com.hap.hap_boloboloboys.lib.person;

public class Person {
    private int id;
    private String name;
    private int wealth = 0;
    private Inventory inventory = new Inventory(40);
    // TODO inventory
    // TODO active deck
    // TODO passive deck

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*GETTER*/
    public int getId() {return this.id;}
    public String getName() {return this.name;}
    public int getWealth() {return this.wealth;}

    /*SETTER*/
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setWealth(int wealth) {this.wealth = wealth;}
}
