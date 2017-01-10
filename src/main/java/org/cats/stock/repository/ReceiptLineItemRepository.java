package org.cats.stock.repository;

import org.cats.stock.domain.ReceiptLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptLineItemRepository extends JpaRepository<ReceiptLine, Long> {

    List<ReceiptLine> findAllByReceiptId( Long receiptId );
}
