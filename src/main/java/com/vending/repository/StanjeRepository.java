package com.vending.repository;


import com.vending.models.Stanje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StanjeRepository extends JpaRepository<Stanje, Long> {


    List<Stanje> findStanjeByAssetId(Long id);
}
