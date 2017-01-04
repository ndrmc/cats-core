package org.cats.stock.receipt.controllers;

import io.swagger.annotations.Api;
import org.cats.stock.receipt.domain.Receipt;
import org.cats.stock.receipt.services.ReceiptService;
import org.cats.stock.receipt.services.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */

@RestController
@RequestMapping(value = "receipt")
@Api(value = "receipt", description = "Manages hub receipt (GRN) records")
public class ReceiptController {

    private ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RequestMapping( value = "/")
    public List<Receipt> getReceipts() {
        return receiptService.getAllReceipts();
    }


}
