package org.cats.stock.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
import org.cats.stock.error.ReceiptControllerException;
import org.cats.stock.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alexander on 1/9/17.
 */

@RestController
@RequestMapping(value = "/receiptLines")
@Api(value = "receipts", description = "Manages hub receipt record line items.")
public class ReceiptLineController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptLineController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }


    @RequestMapping(value = "/receipt/{receiptId}", method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Returns all Receipt Line Items for a Receipt Document")
    public List<ReceiptLine> getReceiptLineItemsByReceiptId(@PathVariable("receiptId") Long receiptId) {

        Receipt receipt = receiptService.getReceiptById(receiptId);

        if( null == receipt )  {
            throw new ReceiptControllerException("Receipt document not found with the id: " + receiptId.toString());
        }

        return receiptService.getReceiptLinesForReceipt(receipt);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Returns all Receipt Line Items for a Receipt Document")
    public ReceiptLine getReceiptLineItemById(@PathVariable("id") Long id) {

        ReceiptLine receiptLine = receiptService.getReceiptLineById(id);

        if( null == receiptService )  {
            throw new ReceiptControllerException("Receipt document not found with the id: " + id.toString());
        }

        return  receiptLine;
    }

    @RequestMapping(value = "/{receiptId}", method = RequestMethod.POST , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Creates a new receipt line item for an existing receipt object.")
    public ReceiptLine createReceiptLineItem(@PathVariable("receiptId") Long receiptId, @Validated @RequestBody ReceiptLine receiptLineItem) {

        Receipt receipt = receiptService.getReceiptById(receiptId);

        if( null == receipt )  {
            throw new ReceiptControllerException("Receipt document not found with the id: " + receiptId.toString());
        }

        receiptLineItem.setReceipt(receipt);

        return receiptService.saveReceiptLineItem(receiptLineItem);
    }
    
    @RequestMapping(value = "/{receiptLineId}", method = RequestMethod.PATCH , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Updates a receipt line item with a given diff object.")
    public ReceiptLine updateReceiptLineItem(@PathVariable("receiptLineId") Long receiptLineId, @Validated @RequestBody ObjectNode receiptLineDiff) {

        ReceiptLine receiptLine = receiptService.getReceiptLineById(receiptLineId);

        if( null == receiptLine )  {
            throw new ReceiptControllerException("Receipt Line document not found with the id: " + receiptLineId.toString());
        }

        receiptLine.updateFields(receiptLineDiff);

        return receiptService.saveReceiptLineItem(receiptLine);
    
    }


    @RequestMapping( value = "/{id}",method = RequestMethod.DELETE )
    @ApiOperation(value = "Deletes a receipt line document.")
    public ResponseEntity<ReceiptLine> deleteReceiptLine(@PathVariable("id") Long id) {

        ReceiptLine receipt = receiptService.getReceiptLineById(id);

        if( null == receipt )  {
            throw new ReceiptControllerException("Receipt Line document not found with the id: " + id.toString());
        }

        receiptService.deleteReceiptLine(receipt);

        return new ResponseEntity<ReceiptLine>(HttpStatus.NO_CONTENT);
    }

}
