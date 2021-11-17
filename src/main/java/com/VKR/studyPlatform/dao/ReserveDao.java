package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.Reserve;
import com.VKR.studyPlatform.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReserveDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Reserve> getAll(){
        return entityManager.createQuery("from Reserve order by number", Reserve.class).getResultList();
    }

    public List<Reserve> getAll(String sort){
        sort = " order by ".concat(sort);
        return entityManager.createQuery("from Reserve".concat(sort), Reserve.class).getResultList();
    }

    public Reserve getReserve(int id){
        return entityManager.find(Reserve.class, id);
    }

    public void saveReserve(Reserve reserve){
        entityManager.persist(reserve);
    }
}
