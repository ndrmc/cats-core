package org.cats.stock.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.cats.operation.controller.OperationController;
import org.cats.operation.domain.Operation;
import org.cats.operation.service.OperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javassist.NotFoundException;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OperationControllerTest {

	@Mock
	OperationService operationService;


	@InjectMocks
	OperationController operationController;



	private Operation stubServiceToReturnSavedOperation() {
		Operation operation =new Operation();
		operation.setName("test operation 1");
		operation.setYear("2017");

		when(operationService.findById(any(Long.class))).thenReturn(operation);
		when(operationService.save(any(Operation.class))).thenReturn(operation);
		try {
			when(operationService.update(any(Operation.class))).thenReturn(operation);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		when(operationService.findByName(any(String.class))).thenReturn(operation);

		return operation;
	}


	private Operation stubServiceToReturnNotFoundException() throws NotFoundException {
		Operation operation =new Operation();
		operation.setName("test operation 1");
		operation.setYear("2017");

		when(operationService.update(any(Operation.class))).thenThrow(new NotFoundException("Operation with id "+operation.getId()+"not found"));
		return operation;
	}

	private List<Operation> stubServiceToReturnOperationList(int size) {
		List<Operation> operations = new ArrayList<>();
		for(int i=0;i<size; i++){
			Operation operation = new Operation();
			operations.add(operation);
		}

		when(operationService.getList()).thenReturn(operations);
		when(operationService.getListbyYear(any(String.class))).thenReturn(operations);

		return operations;
	}

	@Test
	public void testCreateOperation() {
		Operation operation = stubServiceToReturnSavedOperation();

		Operation savedOperation=operationController.createOperation(operation);

		assertEquals(operation,savedOperation);
		verify(operationService,times(1)).save(operation);

	}



	@Test
	public void testGetOPerationById() {
		Operation savedOperation = stubServiceToReturnSavedOperation();
		Operation returnedOperation= operationController.getOPerationById(Long.parseLong("1"));

		assertEquals(savedOperation, returnedOperation);
		verify(operationService,times(1)).findById(Long.parseLong("1"));

	}

	@Test
	public void testGetOperationbyName() {
		Operation savedOperation = stubServiceToReturnSavedOperation();
		Operation returnedOperation= operationController.getOperationbyName("name");

		assertEquals(savedOperation, returnedOperation);
		verify(operationService,times(1)).findByName("name");
	}

	@Test
	public void testUpdateOperation() {
		Operation savedOperation = stubServiceToReturnSavedOperation();
		try {
			Operation returnedOperation = operationController.updateOperation(savedOperation);


			assertEquals(savedOperation, returnedOperation);
			verify(operationService,times(1)).update(savedOperation);
		} catch (NotFoundException e) {
			fail("Should not throw not found exception");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateOperationNotFoundException() {
		try {
			Operation savedOperation = stubServiceToReturnNotFoundException();

			operationController.updateOperation(savedOperation);
			fail("Should have thrown not found exception");
		} catch (NotFoundException e) {

		}		
	}


	@Test
	public void testDeleteOperation() {
		stubServiceToReturnSavedOperation();
		operationController.deleteOperation(Long.parseLong("1"));

		verify(operationService,times(1)).delete(Long.parseLong("1"));
	}

	@Test
	public void testListOperations() {
		stubServiceToReturnOperationList(3);
		List<Operation> returnedOperations= operationController.listOperations();		

		assertNotNull(returnedOperations);
		assertEquals(3, returnedOperations.size());
		verify(operationService,times(1)).getList();		

	}	

	@Test
	public void testListOperationsByYear() {
		stubServiceToReturnOperationList(5);
		List<Operation> returnedOperations= operationController.listOperationsByYear("2017");		

		assertNotNull(returnedOperations);
		assertEquals(5, returnedOperations.size());
		verify(operationService,times(1)).getListbyYear("2017");
	}

}
