package com.vending.controllers;

import com.vending.models.Model;
import com.vending.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;
    


    @GetMapping("/models")
    public List<Model> getAllUsers() {
        return modelRepository.findAll();

    }
    @GetMapping("/models/{code}")
    Optional<Model> one(@PathVariable String code) {
        return modelRepository.findModelByCode(code);

    }

    @DeleteMapping("/models/{id}")
    @Transactional
    void deleteModel(@PathVariable Long id) {
        modelRepository.deleteById(id);
    }


    @DeleteMapping("/models/{id}")
    @Transactional
    void deleteDocument(@PathVariable Long id) {
        modelRepository.deleteById(id);
    }

    @PostMapping("/models")
    @Transactional
    public Model  createModel(@RequestBody Model model) {
          if(modelRepository.findModelByCode(model.getCode()).isPresent())
              throw  new ResponseStatusException(HttpStatus.CONFLICT, "Code already exists");

              else
                    return modelRepository.save(model);


       }



    @PutMapping("/models")
    Model replaceFixedModel(@RequestBody Model model ){

        return modelRepository.findModelByCode(model.getCode())
                .map(newModel -> {
                    newModel.setCode(model.getCode());
                    newModel.setName(model.getName());
                    newModel.setDescr(model.getDescr());
                    newModel.setYear(model.getYear());
                    return modelRepository.save(newModel);
                })
                .orElseGet(() -> {
                    return modelRepository.save(model);
                });
    }

}

