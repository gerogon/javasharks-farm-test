package com.ggonzalez.farmtest.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "farm")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "money")
    private int money;

    @Column(name = "egg_capacity")
    private int eggCapacity;

    @Column(name = "chicken_capacity")
    private int chickenCapacity;

    @Column(name = "egg_price")
    private int eggPrice;

    @Column(name = "chicken_price")
    private int chickenPrice;

    @OneToMany(mappedBy = "farm" /*, cascade*/)
    private List<Chicken> chickens;

    @OneToMany(mappedBy = "farm" /*, cascade */)
    private List<Egg> eggs;

    public Farm(){}

    public Farm(int money, int eggCapacity, int chickenCapacity, int eggPrice, int chickenPrice) {
        this.money = money;
        this.eggCapacity = eggCapacity;
        this.chickenCapacity = chickenCapacity;
        this.eggPrice = eggPrice;
        this.chickenPrice = chickenPrice;
    }

    public int getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public int getEggCapacity() {
        return eggCapacity;
    }

    public int getChickenCapacity() {
        return chickenCapacity;
    }

    public int getEggPrice() {
        return eggPrice;
    }

    public int getChickenPrice() {
        return chickenPrice;
    }

    public void addMoney(int anAmountOfMoney){
        this.money = money + anAmountOfMoney;
    }

    public void substractMoney(int anAmountOfMoney){
        this.money = money - anAmountOfMoney;
    }
}
