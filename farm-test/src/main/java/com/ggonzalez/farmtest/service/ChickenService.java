package com.ggonzalez.farmtest.service;
import com.ggonzalez.farmtest.entity.Chicken;

public interface ChickenService {

    Chicken save(Chicken aChicken);
    void incrementOneDayOfLife();
    void killOldChickens();
    int eggsToAdd();
    long countChickens();
    void removeChicken();
    int chickensToDieOnTheNextDay();
    int eggsToBeLaidOnTheNextDay();
}
