package org.cats.stock.controller;

import java.util.List;

import javax.validation.Valid;

import org.cats.stock.domain.Operation;
import org.cats.stock.domain.Receipt;
import org.cats.stock.exception.ReceiptControllerException;
import org.cats.stock.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/operations")
@Api(value = "/operations")
public class OperationController {

	@Autowired
	OperationService operationService;	


	@ApiOperation(value = "Create a new Operation record" )
	@RequestMapping( method = RequestMethod.POST)
	public Operation createOperation(@RequestBody @Valid final Operation operation) {
		return operationService.save(operation);
	}

	@ApiOperation(value = "View a specific Operation record by id" )
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Operation getOPerationById(@PathVariable Long id) {		
		return operationService.findById(id);
	}


	@ApiOperation(value = "View Operation record by name" )
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public Operation getOperationbyName(@PathVariable String name) {
		return operationService.findByName(name);
	}

	@ApiOperation(value = "Update an existing Operation record" )
	@RequestMapping(method = RequestMethod.PUT)
	public Operation updateOperation(@RequestBody @Valid final Operation operation) throws NotFoundException {

		return operationService.update(operation);
	}

	@ApiOperation(value = "Patch an existing Operation record.")
	@RequestMapping( value = "/operation/{id}",method = RequestMethod.PATCH , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	public Operation updateOperation(@PathVariable("id") Long id, @RequestBody ObjectNode operationDiff) throws NotFoundException {


		Operation operation = operationService.findById(id);

		if( operation == null )  {
			throw new NotFoundException("Operation with the id: " + id.toString()+ "not found.");
		}

		operation.updateFields(operationDiff);
	 ObjectMapper mapper= new ObjectMapper();
		try {
			System.out.println("update op"+ mapper.writeValueAsString(operation));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return operationService.update(operation);
	}

	@ApiOperation(value = "Delete an operation record" )
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteOperation(@PathVariable Long id) {

		operationService.delete(id);
	}

	@ApiOperation(value = "View all operation records" )
	@RequestMapping( method = RequestMethod.GET)
	public List<Operation> listOperations() {		
		return operationService.getList();
	}

	@ApiOperation(value = "View operation records by year" )
	@RequestMapping(value = "/year/{year}", method = RequestMethod.GET)
	public List<Operation> listOperationsByYear(@PathVariable String year) {		
		return operationService.getListbyYear(year);
	}

}
