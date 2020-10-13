package com.vending.repository;


import com.vending.models.Dijagram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DijagramRepository extends JpaRepository<Dijagram, Long> {


    List<Dijagram> findDijagramByAssetId(Long id);
}

