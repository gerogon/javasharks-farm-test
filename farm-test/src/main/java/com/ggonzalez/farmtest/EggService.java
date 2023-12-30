package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Egg;

public interface EggService {

    void advanceOneDay();
    Egg save(Egg anEgg);

}
