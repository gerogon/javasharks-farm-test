package com.ggonzalez.farmtest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChickenDAOImpl implements ChickenDAO {

    private EntityManager entityManager;

    @Autowired
    public ChickenDAOImpl(EntityManager anEntityManager){
        entityManager = anEntityManager;
    }

    @Override
    public Chicken save(Chicken aChicken) {

        return entityManager.merge(aChicken);
    }

    @Transactional
    @Override
    public void advanceOneDay() {
        TypedQuery<Chicken> query = entityManager.createQuery("from Chicken ", Chicken.class);
        List<Chicken> chickens = query.getResultList();

        for(Chicken chicken : chickens){
            chicken.setDays(chicken.getDays() + 1);
            entityManager.merge(chicken);
        }
    }

}
