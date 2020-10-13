package com.vending.repository;

import com.vending.models.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    Optional<DocumentType> findDocumentTypeByCode(String code);

    void deleteById(Long id);
}
