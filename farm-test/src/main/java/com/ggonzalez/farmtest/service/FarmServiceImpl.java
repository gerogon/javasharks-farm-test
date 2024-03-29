package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.exception.FarmException;
import com.ggonzalez.farmtest.repository.FarmRepository;
import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.entity.Farm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public Farm createFarm(int money, int eggLimit, int chickenLimit, int eggValue, int chickenValue) {
        Farm farm = new Farm(money, eggLimit, chickenLimit, eggValue, chickenValue);
        return farmRepository.save(farm);
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
        int moneyAvailable = this.moneyAvailable(farmId);
        int chickenValue = this.chickenPrice(farmId);
        if (moneyAvailable < chickenValue) {
            throw new FarmException("You don't have enough money to buy a chicken! You need $" + chickenValue + " but only have $" + moneyAvailable);
        }
        long chickensInFarm = this.chickenStock();
        int chickenCapacity = this.chickenCapacity(farmId);
        if (chickensInFarm == chickenCapacity) {
            throw new FarmException("You can't buy any more chickens, the farm has reached the limit!");
        }
        Farm farm = farmRepository.findById(farmId).get();
        Chicken newChicken = new Chicken(farm);

        this.substractMoney(farmId, farm.getChickenPrice());
        return chickenService.save(newChicken);
    }

    @Override
    public Egg buyEgg(int farmId) {
        int moneyAvailable = this.moneyAvailable(farmId);
        int eggValue = this.eggPrice(farmId);
        if (moneyAvailable < eggValue) {
            throw new FarmException("You don't have enough money to buy an egg! You need $" + eggValue + " but only have $" + moneyAvailable);
        }

        long eggsInFarm = this.eggStock();
        int eggCapacity = this.eggCapacity(farmId);
        if (eggsInFarm == eggCapacity) {
            throw new FarmException("You can't buy any more eggs, the farm has reached the limit!");
        }

        Farm farm = farmRepository.findById(farmId).get();
        Egg newEgg = new Egg(farm);

        this.substractMoney(farmId, farm.getEggPrice());
        return eggService.save(newEgg);
    }

    @Override
    public void sellChicken(int farmId) {
        if (this.chickenStock() == 0) {
            throw new FarmException("You don't have any chickens to sell!");
        }
        int chickenPrice = farmRepository.findById(farmId).get().getChickenPrice();

        this.addMoney(farmId, chickenPrice);
        chickenService.removeChicken();
    }

    @Override
    public void sellEgg(int farmId) {
        if (this.eggStock() == 0) {
            throw new FarmException("You don't have any eggs to sell!");
        }
        int eggPrice = farmRepository.findById(farmId).get().getEggPrice();

        this.addMoney(farmId, eggPrice);
        eggService.removeEgg();
    }

    @Override
    public Map<String, Integer> advanceOneDay(int farmId) {

        Map<String, Integer> excess = new HashMap<>();

        chickenService.incrementOneDayOfLife();
        eggService.incrementOneDayOfLife();

        chickenService.killOldChickens();
        int newEggs = chickenService.eggsToAdd();
        int newChickens = eggService.turnOldEggsIntoChickens();

        int validAmountOfChickensToAdd = (int) Math.min(newChickens, this.chickenCapacity(farmId) - this.chickenStock());
        int validAmountOfEggsToAdd = (int) Math.min(newEggs, this.eggCapacity(farmId) - this.eggStock());

        this.addChickens(farmId, validAmountOfChickensToAdd);
        this.addEggs(farmId, validAmountOfEggsToAdd);

        excess.put("chickens", newChickens - validAmountOfChickensToAdd);
        excess.put("eggs", newEggs - validAmountOfEggsToAdd);

        return excess;
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

    @Override
    public int chickensToDieOnTheNextDay(){
        return chickenService.chickensToDieOnTheNextDay();
    }

    @Override
    public int eggsToBeLaidOnTheNextDay(){
        return chickenService.eggsToBeLaidOnTheNextDay();
    }
    @Override
    public int eggsToBeBrokenOnTheNextDay(){
        return eggService.eggsToBeBrokenOnTheNextDay();
    }
}
