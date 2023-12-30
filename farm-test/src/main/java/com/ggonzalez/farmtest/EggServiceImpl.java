package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Egg;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EggServiceImpl implements EggService {

    private EggDAO eggDAO;

    @Autowired
    public EggServiceImpl(EggDAO anEggDAO){
        eggDAO = anEggDAO;
    }

    public void advanceOneDay(){
        eggDAO.advanceOneDay();
    }

    @Transactional
    @Override
    public Egg save(Egg anEgg) {
        return eggDAO.save(anEgg);
    }
}
