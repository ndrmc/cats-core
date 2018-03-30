package org.cats.operation.controller;

import java.util.List;

import javax.validation.Valid;

import org.cats.operation.domain.Operation;
import org.cats.operation.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@Api(value = "operation")
public class OperationController {

	@Autowired
	OperationService operationService;	
	
	
	@ApiOperation(value = "Create a new Operation record" )
	@RequestMapping(value = "/operation", method = RequestMethod.POST)
	public Operation createOperation(@RequestBody @Valid final Operation operation) {
		return operationService.save(operation);
	}

	@ApiOperation(value = "View a specific Operation record by id" )
	@RequestMapping(value = "/operation/{id}", method = RequestMethod.GET)
	public Operation getOPerationById(@PathVariable Long id) {		
		return operationService.findById(id);
	}
	

	@ApiOperation(value = "View Operation record by name" )
	@RequestMapping(value = "/operation/name/{name}", method = RequestMethod.GET)
	public Operation getOperationbyName(@PathVariable String name) {
		return operationService.findByName(name);
	}
	
	@ApiOperation(value = "Update an existing Operation record" )
	@RequestMapping(value = "/operation", method = RequestMethod.PUT)
	public Operation updateOperation(@RequestBody @Valid final Operation operation) throws NotFoundException {
		
		return operationService.update(operation);
	}

	@ApiOperation(value = "Delete an operation record" )
	@RequestMapping(value = "/operation/{id}", method = RequestMethod.DELETE)
	public void deleteOperation(@PathVariable Long id) {
		
		operationService.delete(id);
	}

	@ApiOperation(value = "View all operation records" )
	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	public List<Operation> listOperations() {		
		return operationService.getList();
	}
	
	@ApiOperation(value = "View operation records by year" )
	@RequestMapping(value = "/operation/year/{year}", method = RequestMethod.GET)
	public List<Operation> listOperationsByYear(@PathVariable String year) {		
		return operationService.getListbyYear(year);
	}


	@ApiOperation(value="Fetch all remote operations from CATS main server")
	@RequestMapping(value = "/operation/all", method = RequestMethod.GET)
	public List<Operation> getRemoteOperations(){return operationService.fetchRemoteOperations();}
	
}
