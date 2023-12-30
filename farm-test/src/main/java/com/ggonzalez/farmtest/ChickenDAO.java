package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Chicken;

public interface ChickenDAO {

    void advanceOneDay();

    Chicken save(Chicken aChicken);

}
