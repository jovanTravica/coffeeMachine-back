package com.vending.controllers;


import com.vending.models.Measurement;
import com.vending.models.Model;
import com.vending.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class MeasurementController {



    @Autowired
    private MeasurementRepository measurementRepository;


    @GetMapping("/measurements")
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }




    @DeleteMapping("/measurements/{id}")
    @Transactional
    void deleteMeasurement(@PathVariable Long id) {
        measurementRepository.deleteById(id);
    }

    @PostMapping("/measurements")
    @Transactional
    public Measurement createMeasurement(@RequestBody Measurement measurement) {
        if(measurementRepository.findMeasurementByCode(measurement.getCode()).isPresent())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Code already exists");

        else
            return measurementRepository.save(measurement);


    }


    @PutMapping("/measurements")
    @Transactional
    public Measurement updateMeasurement(@RequestBody Measurement measurement) {




            return measurementRepository.findMeasurementByCode(measurement.getCode())
                    .map(newMeasurement -> {
                        newMeasurement.setCode(measurement.getCode());
                        newMeasurement.setName(measurement.getName());
                        newMeasurement.setDescr(measurement.getDescr());
                        return measurementRepository.save(newMeasurement);
                    })
                    .orElseGet(() -> {
                        return measurementRepository.save(measurement);
                    });


    }

}

