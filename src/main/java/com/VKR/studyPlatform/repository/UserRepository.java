package com.VKR.studyPlatform.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.VKR.studyPlatform.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
