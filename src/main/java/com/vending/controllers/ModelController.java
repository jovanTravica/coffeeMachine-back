package com.vending.controllers;

import com.vending.models.Model;
import com.vending.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;


    Long maxUserId = 0L;

    @PostConstruct
    private void postContruct (){

//        maxUserId = Optional.ofNullable(modelRepository.findMaxModelId()).orElse(0L);

    }

    private Long getNextId () {

        maxUserId = maxUserId + 1;
        return maxUserId ;
    }


    @GetMapping("/models")
    public List<Model> getAllUsers() {
        return modelRepository.findAll();

    }
    @GetMapping("/models/{code}")
    Optional<Model> one(@PathVariable String code) {
        return modelRepository.findModelByCode(code);

    }




    @PostMapping("/models")
    @Transactional
    public Model  createModel(@RequestBody Model model) {

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

//    @PutMapping("/models/{code}")
//    Model replaceFixedModel(@RequestBody Model model, @PathVariable String code) {
//
//        return modelRepository.findModelByCode(code)
//                .map(newModel -> {
//                    newModel.setCode(model.getCode());
//                    newModel.setName(model.getName());
//                    newModel.setDescr(model.getDescr());
//                    newModel.setYear(model.getYear());
//                    return modelRepository.save(model);
//                })
//                .orElseGet(() -> {
//                    return modelRepository.save(model);
//                });
//    }


