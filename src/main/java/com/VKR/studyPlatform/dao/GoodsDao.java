package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class GoodsDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Good> getAll(){
        return entityManager.createNativeQuery("SELECT g.id, name, definition,"+ "\"bigInfo\""+", coalesce(bk.bookcount,0) as bookcount " +
                "FROM goods g " +
                "LEFT JOIN book_count bk " +
                "on g.id=bk.book", Good.class).getResultList();
        //return entityManager.createNativeQuery("SELECT g.id,g.name,g.definition,g." + "\"bigInfo\"" + ",CASE WHEN (bk.bookcount is null) THEN 0 ELSE bk.bookcount END as bookcount FROM goods g LEFT JOIN book_count bk on g.id=bk.book", Good.class).getResultList();
        //return entityManager.createQuery("from Good as Good left outer join book_count as book_count order by id", Good.class).getResultList();
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
