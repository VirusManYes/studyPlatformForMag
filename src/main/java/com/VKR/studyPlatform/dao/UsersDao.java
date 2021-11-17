package com.VKR.studyPlatform.dao;

import com.VKR.studyPlatform.models.User;
import com.VKR.studyPlatform.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return entityManager.createQuery("from User order by id", User.class).getResultList();
    }

    public List<User> getAll(String sort){
        sort = " order by ".concat(sort);
        return entityManager.createQuery("from User".concat(sort), User.class).getResultList();
    }

    public User getUser(int id){
        return entityManager.find(User.class, id);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user){
        entityManager.persist(user);
    }
}
