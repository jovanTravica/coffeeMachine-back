package com.vending.controllers;

import com.vending.models.*;
import com.vending.repository.AssetRepository;
import com.vending.repository.DocumentItemRepository;
import com.vending.repository.DocumentRepository;
import com.vending.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class DocumentItemController {

    @Autowired
    private DocumentItemRepository documentItemRepository;


    @Autowired
    private DocumentRepository documentRepository;


    @Autowired
    private MeasurementRepository measurementRepository;
    


    @GetMapping("/documentitem/document/{id}")
    public List<DocumentItem> getAllDocuemntItems(@PathVariable Long id) {
        return documentItemRepository.findDocumentItemByDocumentId(id);
    }


    @DeleteMapping("/documentitem/{id}")
    @Transactional
    void deleteDocumentItem(@PathVariable Long id) {
        documentItemRepository.deleteById(id);
    }


    @PostMapping("/documentitem")
    @Transactional
    public DocumentItem documentItem(@RequestBody DocumentItem documentItem) {
        if(documentItemRepository.findDocumentItemByCode(documentItem.getCode()).isPresent())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Code already exists");

        else
            return documentItemRepository.save(documentItem);


    }


    @PutMapping("/documentitem")
    @Transactional
    public DocumentItem createDocumentItem(@RequestBody DocumentItem documentItem) {

        Optional<Document> document = documentRepository.findDocumentByCode(documentItem.getDocument().getCode());
        Optional<Measurement> measurement = measurementRepository.findMeasurementByCode(documentItem.getMeasurement().getCode());




            return documentItemRepository.findDocumentItemByCode(documentItem.getCode())
                    .map(newDocumentItem-> {
                        newDocumentItem.setCode(documentItem.getCode());
                        newDocumentItem.setName(documentItem.getName());
                        newDocumentItem.setDescr(documentItem.getDescr());
                        newDocumentItem.setAmount(documentItem.getAmount());
                        newDocumentItem.setQuantity(documentItem.getQuantity());
                        newDocumentItem.setGrossAmount(documentItem.getGrossAmount());
                        newDocumentItem.setDocument(document.get());
                        newDocumentItem.setMeasurement(measurement.get());
                        return documentItemRepository.save(newDocumentItem);
                    })
                    .orElseGet(() -> {
                        documentItem.setDocument(document.get());
                        documentItem.setMeasurement(measurement.get());
                        return documentItemRepository.save(documentItem);
                    });

    }


}
