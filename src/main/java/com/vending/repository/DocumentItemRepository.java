package com.vending.repository;

import com.vending.models.DocumentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentItemRepository extends JpaRepository<DocumentItem, Long> {

    Optional<DocumentItem> findDocumentItemByCode(String code);

    void deleteById(Long id);


    List<DocumentItem> findDocumentItemByDocumentId( Long id);
}
