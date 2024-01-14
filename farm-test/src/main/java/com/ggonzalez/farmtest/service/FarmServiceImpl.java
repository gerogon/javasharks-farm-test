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
    public int createFarm(int money, int eggLimit, int chickenLimit, int eggValue, int chickenValue) {
        Farm farm = new Farm(money, eggLimit, chickenLimit, eggValue, chickenValue);
        farmRepository.save(farm);
        return farm.getId();
    }

    @Override
    public int moneyAvailable(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        return farm.getMoney();
    }

    @Override
    public void addMoney(int farmId, int anAmountOfMoney) {
        Farm farm = farmRepository.findById(farmId).get();
        farm.addMoney(anAmountOfMoney);
        farmRepository.save(farm);
    }

    @Override
    public void substractMoney(int farmId, int anAmountOfMoney) {
        Farm farm = farmRepository.findById(farmId).get();
        farm.substractMoney(anAmountOfMoney);
        farmRepository.save(farm);
    }

    @Override
    public int chickenPrice(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        return farm.getChickenPrice();
    }

    @Override
    public int eggPrice(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        return farm.getEggPrice();
    }

    @Override
    public int chickenCapacity(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        return farm.getChickenCapacity();
    }

    @Override
    public int eggCapacity(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        return farm.getEggCapacity();
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
    public Chicken buyChicken(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        Chicken newChicken = new Chicken(farm);

        this.substractMoney(farmId, farm.getChickenPrice());
        return chickenService.save(newChicken);
    }

    @Override
    public Egg buyEgg(int farmId) {
        Farm farm = farmRepository.findById(farmId).get();
        Egg newEgg = new Egg(farm);

        this.substractMoney(farmId, farm.getEggPrice());
        return eggService.save(newEgg);
    }

    @Override
    public void sellChicken(int farmId) {
        int chickenPrice = farmRepository.findById(farmId).get().getChickenPrice();

        this.addMoney(farmId, chickenPrice);
        chickenService.removeChicken();
    }

    @Override
    public void sellEgg(int farmId) {
        int eggPrice = farmRepository.findById(farmId).get().getEggPrice();

        this.addMoney(farmId, eggPrice);
        eggService.removeEgg();
    }

    @Override
    public void advanceOneDay(int farmId) {
        chickenService.incrementOneDayOfLife();
        eggService.incrementOneDayOfLife();

        chickenService.killOldChickens();
        int newEggs = chickenService.eggsToAdd();
        int newChickens = eggService.turnOldEggsIntoChickens();

        this.addChickens(farmId, newChickens);
        this.addEggs(farmId, newEggs);

        // informar cantidad de huevos y gallinas descartados
    }

    @Override
    public void addChickens(int farmId, int anAmountOfChickens) {
        Farm farm = farmRepository.findById(farmId).get();
        for (int i = 0; i < anAmountOfChickens; i++){
            Chicken newChicken = new Chicken(farm);
            chickenService.save(newChicken);
        }
    }

    @Override
    public void addEggs(int farmId, int anAmountOfEggs) {
        Farm farm = farmRepository.findById(farmId).get();
        for (int i = 0; i < anAmountOfEggs; i++){
            Egg newEgg = new Egg(farm);
            eggService.save(newEgg);
        }
    }
}
