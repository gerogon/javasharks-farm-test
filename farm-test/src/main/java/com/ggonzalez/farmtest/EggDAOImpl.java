package com.ggonzalez.farmtest;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EggDAOImpl implements EggDAO{

    private EntityManager entityManager;

    @Autowired
    public EggDAOImpl(EntityManager anEntityManager){
        entityManager = anEntityManager;
    }
    @Override
    public Egg save(Egg anEgg) {
        return entityManager.merge(anEgg);
    }
}
