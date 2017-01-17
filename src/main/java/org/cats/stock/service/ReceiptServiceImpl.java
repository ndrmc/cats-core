package org.cats.stock.service;

import org.cats.accounting.service.PostingService;
import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
import org.cats.stock.enums.DocumentType;
import org.cats.stock.exception.ReceiptServiceException;
import org.cats.stock.repository.ReceiptLineItemRepository;
import org.cats.stock.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */

@Service
public class ReceiptServiceImpl  {

    private ReceiptRepository receiptRepository;
    private ReceiptLineItemRepository receiptLineItemRepository;
    private PostingService postingService;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository,
                              ReceiptLineItemRepository receiptLineItemRepository,
                              PostingService postingService
                              ) {
        this.receiptRepository = receiptRepository;
        this.receiptLineItemRepository = receiptLineItemRepository;
        this.postingService = postingService;
    }

    
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    
    public Receipt getReceiptById(Long id) {
        return receiptRepository.findOne(id);
    }

    
    public List<Receipt> getReceiptsByHubId(Integer hubId) {
        return receiptRepository.findByHubId(hubId);
    }

    
    public List<Receipt> getReceiptsByWarehouseId(Integer warehouseId) {
        return receiptRepository.findByWarehouseId(warehouseId);
    }

    
    public ReceiptLine getReceiptLineById(Long id) {
        return receiptLineItemRepository.findOne(id);
    }

    
    public Receipt saveReceipt(Receipt receipt) {
        receipt = receiptRepository.save(receipt);

        for (ReceiptLine receiptLine : receipt.getReceiptLines()) {
            receiptLineItemRepository.save(receiptLine);
        }


        if( !receipt.isDraft()) {
            postingService.post(receipt);
        }

        return receipt;
    }

    
    public Receipt updateReceipt(Receipt receipt) {

        if( !receiptRepository.exists(receipt.getId())) {
            throw new ReceiptServiceException("No receipt document exists for the id: " + receipt.getId().toString());
        }


        if(!receipt.isDraft()) {
            if( !postingService.postingExistsForDocument(DocumentType.RECEIPT, receipt.getId())) {
                postingService.post(receipt);
            }
            else {
                //Reverse and register a new transaction
            }
        }


        return receiptRepository.save(receipt);
    }

    
    public void deleteReceipt(Receipt receipt) {
        receiptRepository.delete(receipt);
    }


    
    public void deleteReceiptLine(ReceiptLine receiptLine) {
        receiptLineItemRepository.delete(receiptLine);
    }

    
    public List<ReceiptLine> getReceiptLinesForReceipt(Receipt receipt) {
        return receiptLineItemRepository.findAllByReceiptId(receipt.getId());
    }

    
    public ReceiptLine saveReceiptLineItem(ReceiptLine receiptLineItem) {
        return receiptLineItemRepository.save(receiptLineItem);
    }
}
