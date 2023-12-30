package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Egg;

public interface EggDAO {

    void advanceOneDay();

    Egg save(Egg anEgg);

}
