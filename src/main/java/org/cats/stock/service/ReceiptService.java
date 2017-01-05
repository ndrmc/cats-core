package org.cats.stock.service;

import org.cats.stock.domain.Receipt;
import org.cats.stock.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository repository;

    public List<Receipt> allReceipts(){
        return repository.findAll();
    }

    public Receipt getGrn(Long id){
        return repository.findOne(id);
    }
}
