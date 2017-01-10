package org.cats.stock.services;

import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */
public interface ReceiptService {

    public List<Receipt> getAllReceipts();

    public Receipt getReceiptById(Long id);


    public List<Receipt> getReceiptsByHubId(Integer hubId);

    public List<Receipt> getReceiptsByStoreLocationId(Integer storeLocationId);

    public ReceiptLine getReceiptLineById(Long id);

    public Receipt saveReceipt( Receipt receipt);

    public Receipt updateReceipt(Receipt receipt);

    public void deleteReceipt( Receipt receipt);

    public void deleteReceiptLine( ReceiptLine receiptLine );

    public List<ReceiptLine> getReceiptLinesForReceipt( Receipt receipt);

    public ReceiptLine saveReceiptLineItem( ReceiptLine receiptLineItem );

}
