package com.vending.repository;

import com.vending.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findLocationByCode(String code);
//    Location findLocationByName(String name);
//
//    @Query("SELECT max(l.id) " +
//            "FROM Location as l ")
//    Long findMaxLocationId();
}
