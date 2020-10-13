package com.vending.repository;

import com.vending.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Optional<Measurement> findMeasurementByCode(String code);

    void deleteById(Long id);
}

