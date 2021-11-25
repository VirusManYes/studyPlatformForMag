package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.ChangeStatus;
import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.Order;
import com.VKR.studyPlatform.models.Reserve;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> getAll(){
        return entityManager.createQuery("from Order order by number", Order.class).getResultList();
    }

    //вывод текущих заказов. Надо также оставить старую версию со всеми заказами. Типа история резерва
    //можно запросить массив номеров заказов, потом их в запросе и отбором взять по номеру и каунт = 1
    public List<Order> getAll(String sort){
        sort = " order by ".concat(sort);
        return entityManager.createQuery("from Order " + sort, Order.class).getResultList();
    }

    public List getCurrentOrders(){
        Query preQuery = entityManager.createNativeQuery("DROP TABLE IF EXISTS Order_tempor; CREATE TEMPORARY TABLE Order_tempor "
                .concat("(numberBook integer); ")
                .concat("INSERT into order_tempor ")
                .concat("SELECT ")
                .concat(" o.number ")
                .concat("FROM orders o ")
                .concat("group by o.book,o.number ")
                .concat("having sum(o.count)>0; "));
        preQuery.executeUpdate();
        preQuery = entityManager.createNativeQuery("SELECT * "
                .concat("FROM orders o ")
                .concat("INNER JOIN order_tempor ot on o.number = ot.numberBook ")
                .concat("ORDER BY o.deadline; "), Order.class);
        return preQuery.getResultList();
    }

    public List getCurrentOrders(int userId){
        Query preQuery = entityManager.createNativeQuery("DROP TABLE IF EXISTS Order_tempor; CREATE TEMPORARY TABLE Order_tempor "
                .concat("(numberBook integer); ")
                .concat("INSERT into order_tempor ")
                .concat("SELECT ")
                .concat(" o.number ")
                .concat("FROM orders o ")
                .concat("group by o.book,o.number ")
                .concat("having sum(o.count)>0; "));
        preQuery.executeUpdate();
        preQuery = entityManager.createNativeQuery("SELECT * "
                .concat("FROM orders o ")
                .concat("INNER JOIN order_tempor ot on o.number = ot.numberBook ")
                .concat("WHERE o.user=").concat(String.valueOf(userId))
                .concat("ORDER BY o.deadline; "), Order.class);
        return preQuery.getResultList();
    }

    public Order getOrder(int id){
        return entityManager.find(Order.class, id);
    }

    public void saveOrder(Order reserve){
        entityManager.persist(reserve);
    }

    public void changeCount(ChangeStatus changeStatus, Good book){
        Query preQuery = entityManager.createNativeQuery("select bc.bookcount from book_count bc where bc.book =?");
        preQuery.setParameter(1, book.getId());
        int bookCount = (int) preQuery.getSingleResult();

        if(changeStatus == ChangeStatus.PLUS){
            bookCount++;
        } else if(changeStatus == ChangeStatus.MINUS){
            bookCount--;
        }
        preQuery = entityManager.createNativeQuery("update book_count set bookcount = ? where book = ?");
        preQuery.setParameter(1, bookCount);
        preQuery.setParameter(2, book.getId());
        preQuery.executeUpdate();

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
