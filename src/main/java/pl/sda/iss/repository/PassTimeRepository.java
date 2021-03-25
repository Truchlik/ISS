package pl.sda.iss.repository;

import pl.sda.iss.model.PassTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PassTimeRepository {

    private final EntityManager entityManager;

    public PassTimeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public PassTime save(PassTime passTime){
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(passTime);
            transaction.commit();
            return passTime;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        }
    }


}
