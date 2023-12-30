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

    @OneToMany(mappedBy = "farm" /*, cascade*/)
    private List<Chicken> chickens;

    @OneToMany(mappedBy = "farm" /*, cascade */)
    private List<Egg> eggs;

}
