package org.cats.stock.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.cats.stock.controller.OperationController;
import org.cats.stock.domain.Operation;
import org.cats.stock.repository.OperationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {
	
	@Mock
	OperationRepository operationRepository;


	@InjectMocks
	OperationService operationService;


	private Operation stubRepoToReturnSavedOperation() {
		Operation operation =new Operation();
		operation.setName("test operation 1");
		operation.setYear("2017");

		when(operationRepository.findOne(any(Long.class))).thenReturn(operation);
		when(operationRepository.save(any(Operation.class))).thenReturn(operation);		
		when(operationRepository.findByName(any(String.class))).thenReturn(operation);

		return operation;
	}


	private List<Operation> stubRepoToReturnOperationList(int size) {
		List<Operation> operations = new ArrayList<>();
		for(int i=0;i<size; i++){
			Operation operation = new Operation();
			operations.add(operation);
		}

		when(operationRepository.findAll()).thenReturn(operations);
		when(operationRepository.findByYear(any(String.class))).thenReturn(operations);

		return operations;
	}
	
	@Test
	public void testFindById() {
		Operation savedOperation = stubRepoToReturnSavedOperation();
		Operation returnedOperation= operationService.findById(Long.parseLong("1"));

		assertEquals(savedOperation, returnedOperation);
		verify(operationRepository,times(1)).findOne(Long.parseLong("1"));
	}

	@Test
	public void testFindByName() {
		Operation savedOperation = stubRepoToReturnSavedOperation();
		Operation returnedOperation= operationService.findByName("name");

		assertEquals(savedOperation, returnedOperation);
		verify(operationRepository,times(1)).findByName("name");
	}

	@Test
	public void testSave() {
		Operation operation = stubRepoToReturnSavedOperation();

		Operation savedOperation=operationService.save(operation);

		assertEquals(operation,savedOperation);
		verify(operationRepository,times(1)).save(operation);
	}

	@Test
	public void testUpdate() {
		Operation savedOperation = stubRepoToReturnSavedOperation();
	
			Operation returnedOperation = null;
			try {
				returnedOperation = operationService.update(savedOperation);
			} catch (NotFoundException e) {
				fail("should not throw not ound exception");
			}


			assertEquals(savedOperation, returnedOperation);
			verify(operationRepository,times(1)).save(savedOperation);
		
	}

	@Test
	public void testDelete() {
		stubRepoToReturnSavedOperation();
		operationService.delete(Long.parseLong("1"));

		verify(operationRepository,times(1)).delete(Long.parseLong("1"));
	}

	@Test
	public void testGetList() {
		stubRepoToReturnOperationList(3);
		List<Operation> returnedOperations= operationService.getList();		

		assertNotNull(returnedOperations);
		assertEquals(3, returnedOperations.size());
		verify(operationRepository,times(1)).findAll();	
	}

	@Test
	public void testGetListbyYear() {
		stubRepoToReturnOperationList(5);
		List<Operation> returnedOperations= operationService.getListbyYear("2017");		

		assertNotNull(returnedOperations);
		assertEquals(5, returnedOperations.size());
		verify(operationRepository,times(1)).findByYear("2017");
	}

}
