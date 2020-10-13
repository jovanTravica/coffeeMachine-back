package com.vending.controllers;

import com.vending.models.Dijagram;
import com.vending.models.Location;
import com.vending.models.Stanje;
import com.vending.repository.DijagramRepository;
import com.vending.repository.StanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class DijagramController {

    @Autowired
    DijagramRepository dijagramRepository;

    @GetMapping("/dijagram")
    public List<Dijagram> getAllDijagrams() {
        return dijagramRepository.findAll();

    }
}
