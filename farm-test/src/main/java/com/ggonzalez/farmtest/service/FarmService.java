package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;

public interface FarmService {
    int moneyAvailable();
    void substractMoney(int anAmountOfMoney);
    void addMoney(int anAmountOfMoney);
    long eggStock();
    long chickenStock();
    Chicken buyChicken(Chicken aChicken);
    Egg buyEgg(Egg anEgg);
    void sellChicken();
    void sellEgg();
    //Chicken saveChicken(Chicken aChicken);
    //Egg saveEgg(Egg anEgg);
    void advanceOneDay();
}
