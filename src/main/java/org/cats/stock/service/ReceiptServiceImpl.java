package org.cats.stock.service;

import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
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
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;
    private ReceiptLineItemRepository receiptLineItemRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ReceiptLineItemRepository receiptLineItemRepository ) {
        this.receiptRepository = receiptRepository;
        this.receiptLineItemRepository = receiptLineItemRepository;
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt getReceiptById(Long id) {
        return receiptRepository.findOne(id);
    }

    @Override
    public List<Receipt> getReceiptsByHubId(Integer hubId) {
        return receiptRepository.findByHubId(hubId);
    }

    @Override
    public List<Receipt> getReceiptsByStoreLocationId(Integer storeLocationId) {
        return receiptRepository.findByStoreLocationId(storeLocationId);
    }

    @Override
    public ReceiptLine getReceiptLineById(Long id) {
        return receiptLineItemRepository.findOne(id);
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt updateReceipt(Receipt receipt) {

        if( !receiptRepository.exists(receipt.getId())) {
            throw new ReceiptServiceException("No receipt document exists for the id: " + receipt.getId().toString());
        }

        return receiptRepository.save(receipt);
    }

    @Override
    public void deleteReceipt(Receipt receipt) {
        receiptRepository.delete(receipt);
    }


    @Override
    public void deleteReceiptLine(ReceiptLine receiptLine) {
        receiptLineItemRepository.delete(receiptLine);
    }

    @Override
    public List<ReceiptLine> getReceiptLinesForReceipt(Receipt receipt) {
        return receiptLineItemRepository.findAllByReceiptId(receipt.getId());
    }

    @Override
    public ReceiptLine saveReceiptLineItem(ReceiptLine receiptLineItem) {
        return receiptLineItemRepository.save(receiptLineItem);
    }
}
