package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.repository.FarmRepository;
import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.entity.Farm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements FarmService{

    private FarmRepository farmRepository;
    private ChickenService chickenService;
    private EggService eggService;

    @Autowired
    public FarmServiceImpl(ChickenService aChickenService, EggService anEggService, FarmRepository aFarmRepository){
        chickenService = aChickenService;
        eggService = anEggService;
        farmRepository = aFarmRepository;
    }

    @Override
    public int moneyAvailable() {
        Farm farm = farmRepository.findById(1).get(); // ?? Optional <>
        return farm.getMoney();
    }

    @Override
    public void addMoney(int anAmountOfMoney) {
        Farm farm = farmRepository.findById(1).get(); // ?? Optional <>
        farm.addMoney(anAmountOfMoney);
        farmRepository.save(farm);
    }

    @Override
    public long eggStock() {
        return eggService.countEggs();
    }

    @Override
    public long chickenStock() {
        return chickenService.countChickens();
    }

    @Override
    public void substractMoney(int anAmountOfMoney) {
        Farm farm = farmRepository.findById(1).get(); // ?? Optional <>
        farm.substractMoney(anAmountOfMoney);
        farmRepository.save(farm);
    }

    @Override
    public Chicken buyChicken(Chicken aChicken) {
        int chickenValue = 15;
        this.substractMoney(chickenValue);
        return chickenService.save(aChicken);
    }

    @Override
    public Egg buyEgg(Egg anEgg) {
        int eggValue = 5;
        this.substractMoney(eggValue);
        return eggService.save(anEgg);
    }

    @Override
    public void sellChicken() {
        this.addMoney(15); // chicken_value = 15
        chickenService.removeChicken();
    }

    @Override
    public void sellEgg() {
        this.addMoney(5); // egg_value = 5
        eggService.removeEgg();
    }
/*
    public Chicken saveChicken(Chicken aChicken){
        return chickenService.save(aChicken);
    }

    public Egg saveEgg(Egg anEgg){
        return eggService.save(anEgg);
    }*/

    @Override
    public void advanceOneDay() {
        chickenService.incrementOneDayOfLife();
        eggService.incrementOneDayOfLife();

        chickenService.killOldChickens();
        int newEggs = chickenService.eggsToAdd();
        int newChickens = eggService.turnOldEggsIntoChickens();

        chickenService.addChickens(newChickens);
        eggService.addEggs(newEggs);

        // informar cantidad de huevos y gallinas descartados
    }
}
