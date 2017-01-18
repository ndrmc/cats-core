package org.cats.accounting.repository;

import org.cats.accounting.domain.Posting;
import org.cats.accounting.domain.PostingType;
import org.cats.stock.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    Posting findByDocumentTypeAndDocumentIdAndPostingType(DocumentType documentType, Long documentId, PostingType postingType);
}
