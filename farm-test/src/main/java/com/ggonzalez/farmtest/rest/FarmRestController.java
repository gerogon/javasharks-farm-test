package com.ggonzalez.farmtest.rest;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.entity.Farm;
import com.ggonzalez.farmtest.entity.FarmStatusReport;
import com.ggonzalez.farmtest.exception.FarmException;
import com.ggonzalez.farmtest.exception.FarmExceptionHandler;
import com.ggonzalez.farmtest.service.FarmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FarmRestController {

    private FarmService farmService;
    int farmId;

    public FarmRestController(FarmService aFarmService){
        farmService = aFarmService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/farm")
    public String createFarm(Model model){
        if (farmId == 0){
            farmId = farmService.createFarm(100, 20, 10, 5, 15).getId();
        } else {
            model.addAttribute("error_message", "You already created the farm!");
        }
        return "farm";
    }

    @GetMapping("/farm")
    public String farm(){
        return "farm";
    }

    @PostMapping("/chickens")
    public String buyChicken(Model model){
        try {
            farmService.buyChicken(farmId);
            model.addAttribute("success_message", "You just bought a chicken!");
        } catch (FarmException exc) {
            model.addAttribute("error_message",exc.getMessage());
        }
        return "farm";
    }

    @PostMapping("/eggs")
    public String buyEgg(Model model) {
        try{
            farmService.buyEgg(farmId);
            model.addAttribute("success_message", "You just bought an egg!");
        } catch(FarmException exc){
            model.addAttribute("error_message",exc.getMessage());
        }
        return "farm";
    }

    @DeleteMapping("/chickens")
    public String sellChicken(Model model){
        try {
            farmService.sellChicken(farmId);
            model.addAttribute("success_message", "You just sold a chicken!");
        } catch (FarmException exc){
            model.addAttribute("error_message", exc.getMessage());
        }
        return "farm";
    }

    @DeleteMapping("/eggs")
    public String sellEgg(Model model){
        try{
            farmService.sellEgg(farmId);
            model.addAttribute("success_message", "You just sold an egg!");
        } catch (FarmException exc){
            model.addAttribute("error_message", exc.getMessage());
        }
        return "farm";
    }

    @PutMapping("/days")
    public String advanceOneDay(Model model){
        Map<String, Integer> discardedStock = new HashMap<>();
        discardedStock = farmService.advanceOneDay(farmId);

        model.addAttribute("timePassingMessage",
                "A day passed! " + discardedStock.get("chickens") + " chickens and " + discardedStock.get("eggs") +
                " eggs had to be discarded due to exceeding capacity");

        return "farm";
    }

    @GetMapping("/statusReport")
    public String getStatusReport(Model model){
        int money = farmService.moneyAvailable(farmId);
        long chickens = farmService.chickenStock();
        long eggs = farmService.eggStock();
        int chickensToDieOnTheNextDay = farmService.chickensToDieOnTheNextDay();
        int eggsToBeBrokenOnTheNextDay = farmService.eggsToBeBrokenOnTheNextDay();
        int eggsToBeLaidOnTheNextDay = farmService.eggsToBeLaidOnTheNextDay();

        FarmStatusReport status = new FarmStatusReport(money, chickens, eggs, chickensToDieOnTheNextDay,
                                    eggsToBeBrokenOnTheNextDay, eggsToBeLaidOnTheNextDay);
        model.addAttribute("status", status);

        return "statusReport";
    }
}
