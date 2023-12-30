package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Chicken;

public interface ChickenService {

    void advanceOneDay();
    Chicken save(Chicken aChicken);

}
