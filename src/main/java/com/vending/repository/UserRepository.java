package com.vending.repository;

import com.vending.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

Optional<User> findUserByCode(String code);
    User findUserByUsername(String username);

    @Query("SELECT max(u.id) " +
            "FROM User as u ")
    Long findMaxUserId();


}
