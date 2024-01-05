package com.ggonzalez.farmtest.rest;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.entity.Farm;
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
        return farmService.buyChicken(aChicken);
    }

    @PostMapping("/eggs")
    public Egg buyEgg(@RequestBody Egg anEgg){
        return farmService.buyEgg(anEgg);
    }

    @DeleteMapping("/chickens")
    public void sellChicken(){
        farmService.sellChicken();
    }

    @DeleteMapping("/eggs")
    public void sellEgg(){
        farmService.sellEgg();
    }

    @PutMapping("/days") // endpoint?
    public void advanceOneDay(){
        farmService.advanceOneDay();
    }
}
