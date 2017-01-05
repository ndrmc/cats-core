package org.cats.stock.receipt.services;

import org.cats.stock.receipt.domain.Receipt;
import org.cats.stock.receipt.error.ReceiptServiceException;
import org.cats.stock.receipt.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository ) {
        this.receiptRepository = receiptRepository;
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt getReceiptById(Long id) {
        return receiptRepository.findOne(id);
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
}
