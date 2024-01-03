package com.ggonzalez.farmtest.repository;

import com.ggonzalez.farmtest.entity.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChickenRepository extends JpaRepository<Chicken, Integer> {
    List<Chicken> findByDays(int days);
}
