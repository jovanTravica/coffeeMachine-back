package com.vending.controllers;

import com.vending.models.Stanje;
import com.vending.repository.StanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class StanjeController {

    @Autowired
    StanjeRepository stanjeRepository;

    @GetMapping("/stanje/asset/{id}")
    public List<Stanje> getStanje(@PathVariable Long id) {

        return stanjeRepository.findStanjeByAssetId(id);
    }
}
