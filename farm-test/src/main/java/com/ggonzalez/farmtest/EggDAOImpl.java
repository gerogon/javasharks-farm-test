package com.ggonzalez.farmtest;

import com.ggonzalez.farmtest.entity.Egg;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EggDAOImpl implements EggDAO{

    private EntityManager entityManager;

    @Autowired
    public EggDAOImpl(EntityManager anEntityManager){
        entityManager = anEntityManager;
    }

    @Transactional
    @Override
    public void advanceOneDay() {
        TypedQuery<Egg> query = entityManager.createQuery("from Egg ", Egg.class);
        List<Egg> eggs = query.getResultList();

        for (Egg egg : eggs) {
            egg.setDays(egg.getDays() + 1);
            entityManager.merge(egg);
        }
    }

    @Override
    public Egg save(Egg anEgg) {
        return entityManager.merge(anEgg);
    }
}
