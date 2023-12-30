package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Chicken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChickenServiceImpl implements ChickenService {

    private ChickenDAO chickenDAO;
    @Autowired
    public ChickenServiceImpl(ChickenDAO aChickenDAO){
        chickenDAO = aChickenDAO;
    }

    @Override
    public void advanceOneDay() {
        chickenDAO.advanceOneDay();
    }

    @Transactional
    @Override
    public Chicken save(Chicken aChicken) {
        return chickenDAO.save(aChicken);
    }
}
