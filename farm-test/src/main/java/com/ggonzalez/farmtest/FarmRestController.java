package com.ggonzalez.farmtest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmRestController {

    private ChickenService chickenService;
    private EggService eggService;

    // private money; ????????????

    public FarmRestController(ChickenService aChickenService, EggService anEggService){
        chickenService = aChickenService;
        eggService = anEggService;
        // money = 100; ?????????
    }

    @PostMapping("/chickens")
    public Chicken buyChicken(@RequestBody Chicken aChicken){
        return chickenService.save(aChicken);
    }

    @PostMapping("/eggs")
    public Egg buyEgg(@RequestBody Egg anEgg){
        return eggService.save(anEgg);
    }

    @PutMapping("/days") // endpoint?
    public void advanceOneDay(){
        chickenService.advanceOneDay();
        //eggService.advanceOneDay();
    }
}
