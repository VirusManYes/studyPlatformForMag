package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.Reserve;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    //вывод текущих заказов. Надо также оставить старую версию со всеми заказами. Типа история резерва
    //можно запросить массив номеров заказов, потом их в запросе и отбором взять по номеру и каунт = 1
    public List<Reserve> getAll(String sort){
        sort = " order by ".concat(sort);
        return entityManager.createQuery("from Reserve " + sort, Reserve.class).getResultList();
    }

    public List getCurrentReserves(){
        Query preQuery = entityManager.createNativeQuery("DROP TABLE IF EXISTS reserve_tempor; CREATE TEMPORARY TABLE reserve_tempor "
                .concat("(numberBook integer); ")
                .concat("INSERT into reserve_tempor ")
                .concat("SELECT ")
                .concat(" r.number ")
                .concat("FROM reserve r ")
                .concat("group by r.book,r.number ")
                .concat("having sum(r.count)>0; "));
        preQuery.executeUpdate();
        preQuery = entityManager.createNativeQuery("SELECT * "
                .concat("FROM reserve r ")
                .concat("INNER JOIN reserve_tempor rt on r.number = rt.numberBook ")
                .concat("ORDER BY r.deadline; "), Reserve.class);
        return preQuery.getResultList();
    }

    public Reserve getReserve(int id){
        return entityManager.find(Reserve.class, id);
    }

    public void saveReserve(Reserve reserve){
        entityManager.persist(reserve);
    }

    private String getQueryText(String textFor){
        switch (textFor){
            case "currentReserve": return getQueryCurrentReserve();
            default: return "";
        }
    }

    private String getQueryCurrentReserve(){
        return "CREATE TEMPORARY TABLE reserve_tempor "
                .concat("(number integer); ")
                .concat("INSERT into reserve_tempor ")
                .concat("SELECT ")
                .concat(" r.number ")
                .concat("FROM reserve r ")
                .concat("group by r.book,r.number ")
                .concat("having sum(r.count)>0; ")
                .concat("SELECT * ")
                .concat("FROM reserve r ")
                .concat("INNER JOIN reserve_tempor rt on r.number = rt.number ")
                .concat("ORDER BY r.deadline; ")
                .concat("DROP TABLE reserve_tempor;");


    }
}
