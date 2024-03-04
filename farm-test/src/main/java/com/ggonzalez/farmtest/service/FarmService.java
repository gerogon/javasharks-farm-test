package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.entity.Farm;

import java.util.Map;

public interface FarmService {
    Farm createFarm(int money, int eggLimit, int chickenLimit, int eggValue, int chickenValue);
    int moneyAvailable(int farmId);
    void substractMoney(int farmId, int anAmountOfMoney);
    void addMoney(int farmId, int anAmountOfMoney);
    int chickenPrice(int farmId);
    int eggPrice(int farmId);
    int chickenCapacity(int farmId);
    int eggCapacity(int farmId);

    long eggStock();
    long chickenStock();
    Chicken buyChicken(int farmId);
    Egg buyEgg(int farmId);
    void sellChicken(int farmId);
    void sellEgg(int farmId);
    Map<String, Integer> advanceOneDay(int farmId);
    void addChickens(int farmId, int anAmountOfChickens);

    void addEggs(int farmId, int anAmountOfEggs);

    int chickensToDieOnTheNextDay();
    int eggsToBeLaidOnTheNextDay();
    int eggsToBeBrokenOnTheNextDay();
}
