package com.ggonzalez.farmtest.repository;

import com.ggonzalez.farmtest.entity.Chicken;
import com.ggonzalez.farmtest.entity.Egg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EggRepository extends JpaRepository<Egg, Integer> {
    List<Egg> findByDays(int days);
    Egg findFirstByOrderByIdDesc();
}
