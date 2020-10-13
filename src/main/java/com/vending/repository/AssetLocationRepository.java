package com.vending.repository;

import com.vending.models.AssetLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetLocationRepository extends JpaRepository<AssetLocation, Long> {

    Optional<AssetLocation> findAssetLocationByCode(String code);


}
