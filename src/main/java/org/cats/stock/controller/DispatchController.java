package org.cats.stock.controller;

import java.util.List;
import javax.validation.Valid;

import org.cats.stock.domain.Dispatch;
import org.cats.stock.service.DispatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@Api(value = "dispatch", description = "Dispatch(GIN)" )
public class DispatchController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DispatchController.class);
	
	@Autowired
	private DispatchService dispatchService;
	

	public void setDispatchService(DispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	public DispatchService getDispatchService() {
		return dispatchService;
	}

	@ApiOperation(value = "Create a new Dispatch(GIN) record" )
	@RequestMapping(value = "/dispatch", method = RequestMethod.POST)
	public Dispatch createDispatch(@RequestBody @Valid final Dispatch dispatch) {
		LOGGER.debug("Create dispatch request", dispatch);
		return dispatchService.save(dispatch);
	}

	@ApiOperation(value = "View a specific Dispatch(GIN) record by id" )
	@RequestMapping(value = "/dispatch/{id}", method = RequestMethod.GET)
	public Dispatch getDispatchById(@PathVariable Long id) {
		LOGGER.debug("Get dispatch by id"+ id);
		return dispatchService.findById(id);
	}

	@ApiOperation(value = "View Dispatch(GIN) record by gin number" )
	@RequestMapping(value = "/dispatch/gin/{gin}", method = RequestMethod.GET)
	public Dispatch getDispatchbyGin(@PathVariable String gin) {
		LOGGER.debug("Get dispatch by gin");
		return dispatchService.findByGin(gin);
	}
	
	@ApiOperation(value = "Update an existing Dispatch(GIN) record" )
	@RequestMapping(value = "/dispatch", method = RequestMethod.PUT)
	public Dispatch updateDispatch(@RequestBody @Valid final Dispatch dispatch) throws NotFoundException {
		LOGGER.debug("Create dispatch request", dispatch);
		return dispatchService.update(dispatch);
	}

	@ApiOperation(value = "Delete a Dispatch(GIN) record" )
	@RequestMapping(value = "/dispatch/{id}", method = RequestMethod.DELETE)
	public void deleteDispatch(@PathVariable Long id) {
		LOGGER.debug("Delete dispatch request", id);
		dispatchService.delete(id);
	}

	@ApiOperation(value = "View all Dispatch(GIN) records" )
	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	public List<Dispatch> listDispatches() {
		LOGGER.debug("List all dispatches request");
		return dispatchService.getList();
	}
	
	@ApiOperation(value = "View all Dispatch(GIN) records by operation" )
	@RequestMapping(value = "/dispatch/operation/{operationId}", method = RequestMethod.GET)
	public List<Dispatch> listDispatchesbyOperation(@PathVariable Integer operationId) {
		LOGGER.debug("List all dispatches request");
		return dispatchService.getListbyOperation(operationId);
	}
	
	@ApiOperation(value = "View all Dispatch(GIN) records by requisition" )
	@RequestMapping(value = "/dispatch/requisition/{requisitionNo}", method = RequestMethod.GET)
	public List<Dispatch> listDispatchesbyRequsition(@PathVariable String requisitionNo) {
		LOGGER.debug("List all dispatches request");
		return dispatchService.getListbyRequisition(requisitionNo);
	}
	
	@ApiOperation(value = "View all Dispatch(GIN) records by region" )
	@RequestMapping(value = "/dispatch/region/{regionId}", method = RequestMethod.GET)
	public List<Dispatch> listDispatchesbyRegion(@PathVariable Integer regionId) {
		LOGGER.debug("List all dispatches request");
		return dispatchService.getListbyRegion(regionId);
	}

}
