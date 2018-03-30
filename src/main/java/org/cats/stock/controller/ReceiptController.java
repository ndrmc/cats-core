package org.cats.stock.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cats.stock.domain.Receipt;
import org.cats.stock.exception.ReceiptControllerException;
import org.cats.stock.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alexander on 1/4/17.
 */

@CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept")
@RestController
@RequestMapping(value = "/receipts")
@Api(value = "receipts", description = "Manages hub receipt (GRN) records")
public class ReceiptController {

    private final ReceiptService receiptService;


    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RequestMapping(  method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Returns all receipts")
    public List<Receipt> getReceipts() {
        return receiptService.getAllReceipts();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Finds a receipt document by its Id.")
    public Receipt getReceipt(@PathVariable("id") Long id ) {
        return receiptService.getReceiptById(id);
    }

    @RequestMapping( value = "/hub/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Finds receipt documents by their Hub Id.")
    public List<Receipt> getReceiptsByHubId(@PathVariable("id") Integer hubId ) {
        return receiptService.getReceiptsByHubId(hubId);
    }

    @RequestMapping( value = "/warehouse/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Finds receipt documents by their Warehouse Id.")
    public List<Receipt> getReceiptsByWarehouseId(@PathVariable("id") Integer warehouseId ) {
        return receiptService.getReceiptsByWarehouseId(warehouseId);
    }

    @RequestMapping( method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Creates a new Receipt document.")
    public Receipt createReceipt(@Validated @RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

    @RequestMapping( value = "/{id}",method = RequestMethod.PATCH , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ApiOperation(value = "Updates a receipt document.")
    public Receipt updateReceipt(@PathVariable("id") Long id, @RequestBody ObjectNode receiptDiff) {


        Receipt receipt = receiptService.getReceiptById(id);

        if( null == receipt )  {
            throw new ReceiptControllerException("Receipt document not found with the id: " + id.toString());
        }

        receipt.updateFields(receiptDiff);

        return receiptService.updateReceipt(receipt);
    }

    @RequestMapping( value = "/{id}",method = RequestMethod.DELETE )
    @ApiOperation(value = "Deletes a receipt document.")
    public ResponseEntity<Receipt> deleteReceipt(@PathVariable("id") Long id) {

        Receipt receipt = receiptService.getReceiptById(id);

        if( null == receipt )  {
            throw new ReceiptControllerException("Receipt document not found with the id: " + id.toString());
        }


        receiptService.deleteReceipt(receipt);

        return new ResponseEntity<Receipt>(HttpStatus.NO_CONTENT);
    }


}
