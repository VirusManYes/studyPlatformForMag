package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class GoodsDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Good> getAll(int offset){

        return entityManager.createNativeQuery("SELECT g.id, name, definition,"+ "\"bigInfo\""+", coalesce(bk.bookcount,0) as bookCount " +
                "FROM goods g " +
                "LEFT JOIN book_count bk " +
                "on g.id=bk.book " +
                "ORDER BY coalesce(bk.bookcount,0) DESC " +
                "OFFSET " + offset +
                " FETCH NEXT 3 ROWS ONLY ", Good.class).getResultList();
        }

    public int getCount(){
        return Integer.valueOf(entityManager.createNativeQuery("SELECT count(*) FROM goods").getSingleResult().toString());
    }

    public void deleteBook(Good book){
        entityManager.remove(book);
    }

    public List<Good> getAll(){
        return entityManager.createNativeQuery("SELECT g.id, name, definition,"+ "\"bigInfo\""+", coalesce(bk.bookcount,0) as bookCount " +
                "FROM goods g " +
                "LEFT JOIN book_count bk " +
                "on g.id=bk.book " +
                "ORDER BY coalesce(bk.bookcount,0) DESC ", Good.class).getResultList();
    }

    public List<Good> getAll(String sort){
        sort = " order by ".concat(sort);
        return entityManager.createQuery("from Good".concat(sort), Good.class).getResultList();
    }

    //сделать апдейт или инсерт
    public void changeBookCount(String bookId, String newCount){
         Query query = entityManager.createNativeQuery("UPDATE book_count set bookcount = ? where book = ?");
         query.setParameter(1, Integer.parseInt(newCount));
         query.setParameter(2, Integer.parseInt(bookId));
         query.executeUpdate();
    }

    public Good getGood(int id){
        return entityManager.find(Good.class, id);
    }

    public void saveGood(Good good){
        entityManager.persist(good);
    }
}
