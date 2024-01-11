package com.ggonzalez.farmtest.rest;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.exception.FarmException;
import com.ggonzalez.farmtest.service.FarmService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FarmRestController {

    private FarmService farmService;

    public FarmRestController(FarmService aFarmService){
        farmService = aFarmService;
    }

    @PostMapping("/chickens")
    public Chicken buyChicken(@RequestBody Chicken aChicken){
        if (farmService.moneyAvailable() < 15) {
            throw new FarmException("You don't have enough money to buy a chicken! You need $15 but only have $" + farmService.moneyAvailable());
        }
        return farmService.buyChicken(aChicken);
    }

    @PostMapping("/eggs")
    public Egg buyEgg(@RequestBody Egg anEgg){
        if (farmService.moneyAvailable() < 5) {
            throw new FarmException("You don't have enough money to buy an egg! You need $5 but only have $" + farmService.moneyAvailable());
        }
        return farmService.buyEgg(anEgg);
    }

    @DeleteMapping("/chickens")
    public void sellChicken(){
        if (farmService.chickenStock() == 0) {
            throw new FarmException("You don't have any chickens to sell!");
        }
        farmService.sellChicken();
    }

    @DeleteMapping("/eggs")
    public void sellEgg(){
        if (farmService.eggStock() == 0) {
            throw new FarmException("You don't have any eggs to sell!");
        }
        farmService.sellEgg();
    }

    @PutMapping("/days") // endpoint?
    public void advanceOneDay(){
        farmService.advanceOneDay();
    }
}
