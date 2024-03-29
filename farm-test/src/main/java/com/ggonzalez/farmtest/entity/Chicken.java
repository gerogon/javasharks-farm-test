package com.ggonzalez.farmtest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chickens")
public class Chicken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "days_of_life")
    private int days;

    @ManyToOne
    @JoinColumn(name="farm_id")
    private Farm farm;

    public Chicken(){
        this.days = 0;
    }

    public Chicken(Farm aFarm){
        this.days = 0;
        this.farm = aFarm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void incrementDays() { // public?
        this.days ++;
    }
}
