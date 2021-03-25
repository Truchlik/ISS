package pl.sda.iss.repository;

import pl.sda.iss.model.Coordinates;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CoordinatesRepository {
    private final EntityManager entityManager;

    public CoordinatesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Coordinates save(Coordinates coordinates){
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(coordinates);
            transaction.commit();
            return coordinates;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        }
    }
}
