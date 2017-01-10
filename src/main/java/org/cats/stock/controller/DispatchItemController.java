package org.cats.stock.controller;

import java.util.List;
import javax.validation.Valid;

import org.cats.stock.domain.Dispatch;
import org.cats.stock.domain.DispatchItem;
import org.cats.stock.service.DispatchItemService;
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
@Api(value = "dispatchItem", description = "Dispatch Items" )
public class DispatchItemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DispatchItemController.class);
	
	@Autowired
	private DispatchItemService dispatchService;
	

	public void setDispatchItemService(DispatchItemService dispatchItemService) {
		this.dispatchService = dispatchItemService;
	}

	public DispatchItemService getDispatchItemService() {
		return dispatchService;
	}

	@ApiOperation(value = "View all DispatchItem(GIN) records" )
	@RequestMapping(value = "/dispatchItem", method = RequestMethod.GET)
	public List<DispatchItem> listDispatchItems() {
		LOGGER.debug("List all dispatches request");
		return dispatchService.getList();
	}

	@ApiOperation(value = "View a specific dispatchItem record by id" )
	@RequestMapping(value = "/dispatchItem/{id}", method = RequestMethod.GET)
	public DispatchItem getDispatchItemById(@PathVariable Long id) {
		LOGGER.debug("Get dispatchItem by id"+ id);
		return dispatchService.findById(id);
	}
	
	
	@ApiOperation(value = "View all DispatchItem(GIN) records by dispatch id" )
	@RequestMapping(value = "/dispatchItem/dispatch/{dispatchId}", method = RequestMethod.GET)
	public List<DispatchItem> listDispatchItemsbyDispatch(@PathVariable Integer dispatchId) {
		LOGGER.debug("List all dispatches request");
		return dispatchService.getListbyDispatch(dispatchId);
	}

	@ApiOperation(value = "View dispatchItem record by project id" )
	@RequestMapping(value = "/dispatchItem/project/{projectId}", method = RequestMethod.GET)
	public List<DispatchItem> getDispatchItemsbyProject(@PathVariable Integer projectId) {
		LOGGER.debug("Get dispatchItem by projectId");
		return dispatchService.getListbyProject(projectId);
	}

	
	@ApiOperation(value = "Create a new dispatchItem record" )
	@RequestMapping(value = "/dispatchItem", method = RequestMethod.POST)
	public DispatchItem createDispatchItem(@RequestBody @Valid final DispatchItem dispatchItem) {
		LOGGER.debug("Create dispatchItem request", dispatchItem);
		return dispatchService.save(dispatchItem);
	}
	
	@ApiOperation(value = "Update an existing DispatchItem record" )
	@RequestMapping(value = "/dispatchItem", method = RequestMethod.PUT)
	public DispatchItem updateDispatchItem(@RequestBody @Valid final DispatchItem dispatchItem) throws NotFoundException {
		LOGGER.debug("Create dispatchItem request", dispatchItem);
		return dispatchService.update(dispatchItem);
	}

	@ApiOperation(value = "Delete a DispatchItem record" )
	@RequestMapping(value = "/dispatchItem/{id}", method = RequestMethod.DELETE)
	public void deleteDispatchItem(@PathVariable Long id) {
		LOGGER.debug("Delete dispatchItem request", id);
		dispatchService.delete(id);
	}	
	
}
