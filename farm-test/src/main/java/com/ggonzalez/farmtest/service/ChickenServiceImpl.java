package com.ggonzalez.farmtest.service;

import com.ggonzalez.farmtest.repository.ChickenRepository;
import com.ggonzalez.farmtest.entity.Chicken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChickenServiceImpl implements ChickenService {

    private ChickenRepository chickenRepository;
    @Autowired
    public ChickenServiceImpl(ChickenRepository aChickenRepository){
        chickenRepository = aChickenRepository;
    }

    @Transactional
    @Override
    public Chicken save(Chicken aChicken) {
        return chickenRepository.save(aChicken);
    }

    @Override
    public void incrementOneDayOfLife() {
        List<Chicken> chickens = chickenRepository.findAll();
        for (Chicken chicken : chickens){
            chicken.incrementDays();
        }

        chickenRepository.saveAll(chickens);
    }

    @Override
    public void killOldChickens() {
        List<Chicken> oldChickens = chickenRepository.findByDays(3);
        chickenRepository.deleteAll(oldChickens);
    }

    @Override
    public int eggsToAdd() {
        List<Chicken> chickensLayingEggs = chickenRepository.findByDays(2);
        return 2 * chickensLayingEggs.size();
    }

    @Override
    public void addChickens(int anAmountOfChickens) {
        for (int i = 0; i < anAmountOfChickens; i++){
            Chicken newChicken = new Chicken();
            chickenRepository.save(newChicken);
        }
    }

    @Override
    public long countChickens() {
        return chickenRepository.count();
    }

    @Override
    public void removeChicken() {
        Chicken chickenToRemove =  chickenRepository.findFirstByOrderByIdDesc();
        chickenRepository.delete(chickenToRemove);
    }
}
