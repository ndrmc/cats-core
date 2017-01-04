package org.cats.stock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cats.stock.domain.Receipt;
import org.cats.stock.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "receipt", description = "Manages hub receipt (GRN) records")
@Controller
@RequestMapping(value = "receipt")
public class ReceiptController {

    @Autowired
    ReceiptService service;

    @ApiOperation(value = "Returns all GRNs available")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Receipt>> getGrns(){
        return new ResponseEntity<>(service.allReceipts(), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a GRN record identified by its Id")
    public ResponseEntity<Receipt> getGrn(@PathVariable("id") Long id){
        return new ResponseEntity<Receipt>(service.getGrn(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new GRN record")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody Receipt receipt){

//        service.save(new Receipt(
//                receipt.getGrnNo(),
//                receipt.getInvoiceNo(),
//                receipt.getPurchaseOrderNo()
//                ...
//        ));

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }





}
