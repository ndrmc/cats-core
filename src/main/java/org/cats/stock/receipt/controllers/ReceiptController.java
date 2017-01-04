package org.cats.stock.receipt.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cats.stock.receipt.domain.Receipt;
import org.cats.stock.receipt.services.ReceiptService;
import org.cats.stock.receipt.services.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */

@RestController
@RequestMapping(value = "/receipt")
@Api(value = "receipt", description = "Manages hub receipt (GRN) records")
public class ReceiptController {

    private ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RequestMapping(  method = RequestMethod.GET )
    @ApiOperation(value = "Returns all receipts")
    public List<Receipt> getReceipts() {
        return receiptService.getAllReceipts();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ApiOperation(value = "Finds a receipt document by its Id.")
    public Receipt getReceipt(@PathVariable("id") Long id ) {
        return receiptService.getReceiptById(id);
    }

    @RequestMapping( method = RequestMethod.POST )
    public Receipt createReceipt(@Validated @RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }


}
