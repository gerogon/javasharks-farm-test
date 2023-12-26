package com.ggonzalez.farmtest;

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

    @Transactional
    @Override
    public Egg save(Egg anEgg) {
        return eggDAO.save(anEgg);
    }
}
