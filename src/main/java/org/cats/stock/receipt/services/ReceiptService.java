package org.cats.stock.receipt.services;

import org.cats.stock.receipt.domain.Receipt;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */
public interface ReceiptService {

    public List<Receipt> getAllReceipts();
}
