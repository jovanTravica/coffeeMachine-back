package com.vending.controllers;

import com.vending.models.DocumentType;
import com.vending.models.Model;
import com.vending.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class DocumentTypeController {


    @Autowired
    private DocumentTypeRepository documentTypeRepository;


    @GetMapping("/documenttype")
    public List<DocumentType> getAllUsers() {
        return documentTypeRepository.findAll();

    }
    @GetMapping("/documenttype/{code}")
    Optional<DocumentType> one(@PathVariable String code) {
        return documentTypeRepository.findDocumentTypeByCode(code);

    }

    @DeleteMapping("/documenttype/{id}")
    @Transactional
    void deleteDocumentType(@PathVariable Long id) {
        documentTypeRepository.deleteById(id);
    }


    @PostMapping("/documenttype")
    @Transactional
    public DocumentType documentType(@RequestBody DocumentType documentType) {
        if(documentTypeRepository.findDocumentTypeByCode(documentType.getCode()).isPresent())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Code already exists");

        else
            return documentTypeRepository.save(documentType);


    }


    @PutMapping("/documenttype")
    @Transactional
    public DocumentType  createDocumentType(@RequestBody DocumentType documentType) {

        return documentTypeRepository.findDocumentTypeByCode(documentType.getCode())
                .map(newDocumentType -> {
                    newDocumentType.setCode(documentType.getCode());
                    newDocumentType.setName(documentType.getName());
                    newDocumentType.setDescr(documentType.getDescr());
                    return documentTypeRepository.save(newDocumentType);
                })
                .orElseGet(() -> {
                    return documentTypeRepository.save(documentType);
                });

    }


}

