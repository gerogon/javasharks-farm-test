package com.ggonzalez.farmtest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "eggs")
public class Egg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "days_of_life")
    private int days;

    @ManyToOne /* (CascadeType) */
    @JoinColumn(name="farm_id")
    private Farm farm;

    public Egg(){
        this.days = 0;
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

    public void incrementDays(){ // public?
        this.days ++;
    }
}