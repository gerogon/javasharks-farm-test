package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.repository.EggRepository;
import com.ggonzalez.farmtest.entity.Egg;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EggServiceImpl implements EggService {

    private EggRepository eggRepository;

    @Autowired
    public EggServiceImpl(EggRepository anEggRepository){
        eggRepository = anEggRepository;
    }

    @Transactional
    @Override
    public Egg save(Egg anEgg) {
        return eggRepository.save(anEgg);
    }

    @Override
    public void incrementOneDayOfLife() {
        List<Egg> eggs = eggRepository.findAll();
        for (Egg egg : eggs){
            egg.incrementDays();
        }

        eggRepository.saveAll(eggs);
    }

    @Override
    public int turnOldEggsIntoChickens() {
        List<Egg> oldEggs = eggRepository.findByDays(3);
        eggRepository.deleteAll(oldEggs);
        return oldEggs.size();
    }

    @Override
    public void addEggs(int anAmountOfEggs) {
        for (int i = 0; i < anAmountOfEggs; i++){
            Egg newEgg = new Egg();
            eggRepository.save(newEgg);
        }
    }
}
