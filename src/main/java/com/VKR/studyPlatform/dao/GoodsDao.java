package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GoodsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Good> getAll(){
        return entityManager.createQuery("from Good order by id", Good.class).getResultList();
    }

    public List<Good> getAll(String sort){
        sort = " order by ".concat(sort);
        return entityManager.createQuery("from Good".concat(sort), Good.class).getResultList();
    }

    public Good getGood(int id){
        return entityManager.find(Good.class, id);
    }

    public void saveGood(Good good){
        entityManager.persist(good);
    }
}
