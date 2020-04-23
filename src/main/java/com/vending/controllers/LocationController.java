package com.vending.controllers;

import com.vending.models.Location;
import com.vending.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;



    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();

    }
    @GetMapping("/locations/{code}")
    Optional<Location> one(@PathVariable String code) {
        return locationRepository.findLocationByCode(code);

    }




    @PostMapping("/locations")
    @Transactional
    public Location createLocation(@RequestBody Location location) {
        return locationRepository.findLocationByCode(location.getCode())
                .map(newLocation -> {
                    newLocation.setCode(location.getCode());
                    newLocation.setName(location.getName());
                    newLocation.setDescr(location.getDescr());
                    newLocation.setAdress(location.getAdress());
                    newLocation.setDateFrom(location.getDateFrom());
                    newLocation.setDateTo(location.getDateTo());
                    newLocation.setActive(location.getActive());

                    return locationRepository.save(newLocation);
                })
                .orElseGet(() -> {
                    return locationRepository.save(location);
                });

    }

//    @PutMapping("/locations/{code}")
//    Location replaceLocation(@RequestBody Location location, @PathVariable String code) {
//
//        return locationRepository.findLocationByCode(code)
//                .map(newLocation -> {
//                    newLocation.setCode(location.getCode());
//                    newLocation.setName(location.getName());
//                    newLocation.setDescr(location.getDescr());
//                    newLocation.setAdress(location.getAdress());
//                    newLocation.setDateFrom(location.getDateFrom());
//                    newLocation.setDateTo(location.getDateTo());
//                    newLocation.setActive(location.getActive());
//
//                    return locationRepository.save(location);
//                })
//                .orElseGet(() -> {
//                    return locationRepository.save(location);
//                });
//    }



}
