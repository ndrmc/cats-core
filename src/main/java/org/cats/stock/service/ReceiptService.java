package org.cats.stock.service;

import org.cats.stock.domain.Receipt;
import org.cats.stock.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    public List<Receipt> allReceipts(){
        return receiptRepository.findAll();
    }

    public Receipt getGrn(Long id){
        return receiptRepository.findOne(id);
    }
}
