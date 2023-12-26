package com.ggonzalez.farmtest;

import jakarta.persistence.*;

@Entity
@Table(name="eggs")
public class Egg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "days_of_life")
    private int days;

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
}