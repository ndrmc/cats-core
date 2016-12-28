package org.cats.stock.repository;

import org.cats.stock.domain.ReceiptLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptLineItemRepository extends JpaRepository<ReceiptLine, Long> {
}
