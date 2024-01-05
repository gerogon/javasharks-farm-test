package com.ggonzalez.farmtest.service;
import com.ggonzalez.farmtest.entity.Chicken;

public interface ChickenService {

    Chicken save(Chicken aChicken);
    void incrementOneDayOfLife();
    void killOldChickens();
    int eggsToAdd();
    void addChickens(int anAmountOfChickens);
    long countChickens();
    void removeChicken();

}
