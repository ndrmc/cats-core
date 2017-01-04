package org.cats.stock.receipt.repository;

import org.cats.stock.receipt.domain.ReceiptLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptLineItemRepository extends JpaRepository<ReceiptLine, Long> {
}
