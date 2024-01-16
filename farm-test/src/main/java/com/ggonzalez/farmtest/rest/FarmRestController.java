package com.ggonzalez.farmtest.rest;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.entity.FarmStatusReport;
import com.ggonzalez.farmtest.exception.FarmException;
import com.ggonzalez.farmtest.service.FarmService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FarmRestController {

    private FarmService farmService;
    int farmId;

    public FarmRestController(FarmService aFarmService){
        farmService = aFarmService;
        farmId = farmService.createFarm(100, 20, 10, 5, 15);
    }

    @PostMapping("/chickens")
    public Chicken buyChicken(){
        int moneyAvailable = farmService.moneyAvailable(farmId);
        int chickenValue = farmService.chickenPrice(farmId);
        if (moneyAvailable < chickenValue) {
            throw new FarmException("You don't have enough money to buy a chicken! You need $" + chickenValue + " but only have $" + moneyAvailable);
        }

        long chickensInFarm = farmService.chickenStock();
        int chickenCapacity = farmService.chickenCapacity(farmId);
        if (chickensInFarm == chickenCapacity) {
            throw new FarmException("You can't buy any more chickens, the farm has reached the limit!");
        }

        return farmService.buyChicken(farmId);
    }

    @PostMapping("/eggs")
    public Egg buyEgg(){
        int moneyAvailable = farmService.moneyAvailable(farmId);
        int eggValue = farmService.eggPrice(farmId);
        if (moneyAvailable < eggValue) {
            throw new FarmException("You don't have enough money to buy an egg! You need $" + eggValue + " but only have $" + moneyAvailable);
        }

        long eggsInFarm = farmService.eggStock();
        int eggCapacity = farmService.eggCapacity(farmId);
        if (eggsInFarm == eggCapacity) {
            throw new FarmException("You can't buy any more eggs, the farm has reached the limit!");
        }

        return farmService.buyEgg(farmId);
    }

    @DeleteMapping("/chickens")
    public void sellChicken(){
        if (farmService.chickenStock() == 0) {
            throw new FarmException("You don't have any chickens to sell!");
        }
        farmService.sellChicken(farmId);
    }

    @DeleteMapping("/eggs")
    public void sellEgg(){
        if (farmService.eggStock() == 0) {
            throw new FarmException("You don't have any eggs to sell!");
        }
        farmService.sellEgg(farmId);
    }

    @PutMapping("/days")
    public void advanceOneDay(){
        farmService.advanceOneDay(farmId);
    }

    @GetMapping("/statusReport")
    public FarmStatusReport getStatusReport(){
        int money = farmService.moneyAvailable(farmId);
        long chickens = farmService.chickenStock();
        long eggs = farmService.eggStock();
        int chickensToDieOnTheNextDay = farmService.chickensToDieOnTheNextDay();
        int eggsToBeBrokenOnTheNextDay = farmService.eggsToBeBrokenOnTheNextDay();
        int eggsToBeLaidOnTheNextDay = farmService.eggsToBeLaidOnTheNextDay();

        FarmStatusReport status = new FarmStatusReport(money, chickens, eggs, chickensToDieOnTheNextDay,
                                    eggsToBeBrokenOnTheNextDay, eggsToBeLaidOnTheNextDay);
        return status;
    }
}
