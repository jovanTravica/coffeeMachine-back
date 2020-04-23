package com.vending.repository;

import com.vending.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

 Optional<Model> findModelByCode(String code);
   // Model findModelByName(String Name);

//    @Query("SELECT max(m.id) " +
//            "FROM model as m ")
//    Long findMaxModelId();
}
