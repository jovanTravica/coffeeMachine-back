package com.vending.repository;

import com.vending.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findDocumentByCode(String code);
    List<Document> findDocumentByDocumentTypeName (String name);
    void deleteById(Long id);

}
