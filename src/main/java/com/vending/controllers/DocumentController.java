package com.vending.controllers;

import com.vending.models.Asset;
import com.vending.models.Document;
import com.vending.models.DocumentType;
import com.vending.models.Model;
import com.vending.repository.AssetRepository;
import com.vending.repository.DocumentRepository;
import com.vending.repository.DocumentTypeRepository;
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
public class DocumentController {


    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private AssetRepository assetRepository;


    @GetMapping("/document")
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @GetMapping("/document/documenttype/{name}")
    List<Document> one(@PathVariable String name) {
        return documentRepository.findDocumentByDocumentTypeName(name);

    }

    @DeleteMapping("/document/{id}")
    @Transactional
    void deleteDocument(@PathVariable Long id) {
        documentRepository.deleteById(id);
    }


    @PostMapping("/document")
    @Transactional
    public Document createDocument(@RequestBody Document document) {
        if(documentRepository.findDocumentByCode(document.getCode()).isPresent())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Code already exists");

        else
            return documentRepository.save(document);


    }



    @PutMapping("/document")
    @Transactional
    public Document updateDocument(@RequestBody Document document) {

        Optional<DocumentType> documentType = documentTypeRepository.findDocumentTypeByCode(document.getDocumentType().getCode());
        Optional<Asset> asset = assetRepository.findAssetByCode(document.getAsset().getCode());



            return documentRepository.findDocumentByCode(document.getCode())
                    .map(newDocument -> {
                        newDocument.setCode(document.getCode());
                        newDocument.setName(document.getName());
                        newDocument.setDescr(document.getDescr());
                        newDocument.setDateDoc(document.getDateDoc());
                        newDocument.setDocumentType(documentType.get());
                        newDocument.setAsset(asset.get());
                        return documentRepository.save(newDocument);
                    })
                    .orElseGet(() -> {
                        document.setDocumentType(documentType.get());
                        document.setAsset(asset.get());
                        return documentRepository.save(document);
                    });


    }

}

