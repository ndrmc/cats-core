package org.cats.stock.controller;

import java.util.List;

import javax.validation.Valid;

import org.cats.stock.domain.Operation;
import org.cats.stock.domain.OperationRegion;
import org.cats.stock.service.OperationRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "operationRegion")
public class OperationRegionController {

	@Autowired
	OperationRegionService operationRegionService;	


	@ApiOperation(value = "Create a new OperationRegion (Add region to operation)" )
	@RequestMapping(value = "/operationRegion", method = RequestMethod.POST)
	public Operation createOperation(@RequestBody @Valid final OperationRegion operationRegion) {
		return operationRegionService.save(operationRegion);
	}	

	@ApiOperation(value = "Delete an OperationRegion (remove region from operation" )
	@RequestMapping(value = "/operationRegion/{id}", method = RequestMethod.DELETE)
	public void deleteOperation(@PathVariable Long id) {
		operationRegionService.delete(id);
	}


	@ApiOperation(value = "View OperationRegions by operation id" )
	@RequestMapping(value = "/operationRegion/operation/{operationId}", method = RequestMethod.GET)
	public List<Operation> listRegionsbyOperation(@PathVariable String operationId) {		
		return operationRegionService.getListbyOperationId(Long.parseLong(operationId));
	}

}
