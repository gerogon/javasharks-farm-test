package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.entity.Egg;

public interface EggService {
    Egg save(Egg anEgg);
    void incrementOneDayOfLife();
    int turnOldEggsIntoChickens();
    void addEggs(int anAmountOfEggs);
}
