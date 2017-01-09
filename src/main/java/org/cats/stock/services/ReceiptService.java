package org.cats.stock.services;

import org.cats.stock.domain.Receipt;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */
public interface ReceiptService {

    public List<Receipt> getAllReceipts();

    public Receipt getReceiptById(Long id);

    public Receipt saveReceipt( Receipt receipt);

    public Receipt updateReceipt(Receipt receipt);

    public void deleteReceipt( Receipt receipt);

}
