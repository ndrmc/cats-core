package org.cats.stock.controller;

import static org.mockito.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.cats.stock.domain.Dispatch;
import org.cats.stock.service.DispatchService;
import org.cats.stock.util.DispatchTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DispatchControllerTest {

	@Mock
	private DispatchService dispatchService;

	private DispatchController dispatchController;

	@Before
	public void setUp() throws Exception {
		dispatchController = new DispatchController();
		dispatchController.setDispatchService(dispatchService);
	}

	private Dispatch stubServiceToReturnStoredDispatch() {
		final Dispatch dispatch = DispatchTestUtil.createDispatch();
		when(dispatchService.save(any(Dispatch.class))).thenReturn(dispatch);
		try {
			when(dispatchService.update(any(Dispatch.class))).thenReturn(dispatch);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(dispatchService.findById(any(Long.class))).thenReturn(dispatch);
		when(dispatchService.findByGin(any(String.class))).thenReturn(dispatch);
		return dispatch;
	}

	private void stubServiceToReturnDispatches(int howMany) {
		when(dispatchService.getList()).thenReturn(DispatchTestUtil.createDispatchList(howMany));
		when(dispatchService.getListbyOperation(any(Integer.class))).thenReturn(DispatchTestUtil.createDispatchList(howMany));
		when(dispatchService.getListbyRegion(any(Integer.class))).thenReturn(DispatchTestUtil.createDispatchList(howMany));
		when(dispatchService.getListbyRequisition(any(String.class))).thenReturn(DispatchTestUtil.createDispatchList(howMany));
	}

	@Test
	public void testCreateDispatch() {
		final Dispatch savedDispatch = stubServiceToReturnStoredDispatch();
		final Dispatch dispatch = DispatchTestUtil.createDispatch();
		Dispatch returnedDispatch = dispatchController.createDispatch(dispatch);

		verify(dispatchService, times(1)).save(dispatch);
		assertEquals(savedDispatch, returnedDispatch);
	}

	@Test
	public void testGetDispatchById() {
		final Dispatch savedDispatch = stubServiceToReturnStoredDispatch();
		Dispatch returnedDispatch = dispatchController.getDispatchById(Long.valueOf("1"));

		verify(dispatchService, times(1)).findById(Long.valueOf("1"));
		assertEquals(savedDispatch, returnedDispatch);
	}

	@Test
	public void testGetDispatchByGin() {

		final Dispatch savedDispatch = stubServiceToReturnStoredDispatch();
		Dispatch returnedDispatch = dispatchController.getDispatchbyGin("1");

		verify(dispatchService, times(1)).findByGin("1");
		assertEquals(savedDispatch, returnedDispatch);

	}

	@Test
	public void testUpdateDispatch() {

		final Dispatch savedDispatch = stubServiceToReturnStoredDispatch();
		
		Dispatch updatedDispatch = null;
		try {

			updatedDispatch=dispatchController.updateDispatch(savedDispatch);
			verify(dispatchService, times(1)).update(savedDispatch);

		} catch (NotFoundException e) {
			fail("shouldn't throw not found exception");
		}
		assertEquals(savedDispatch, updatedDispatch);
	}

	@Test
	public void testDeleteDispatch() {
		dispatchController.deleteDispatch(Long.parseLong("1"));
		verify(dispatchService, times(1)).delete(Long.parseLong("1"));
	}

	@Test
	public void testListDispatches() {
		stubServiceToReturnDispatches(10);
		Collection<Dispatch> dispatches = dispatchController.listDispatches();
		assertNotNull(dispatches);
		assertEquals(10, dispatches.size());

		verify(dispatchService, times(1)).getList();
	}

	@Test
	public void testListDispatchesbyOperation() {
		stubServiceToReturnDispatches(5);
		Collection<Dispatch> dispatches = dispatchController.listDispatchesbyOperation(1);
		assertNotNull(dispatches);
		assertEquals(5, dispatches.size());

		verify(dispatchService, times(1)).getListbyOperation(1);
	}

	@Test
	public void testListDispatchesbyRequsition() {
		stubServiceToReturnDispatches(3);
		Collection<Dispatch> dispatches = dispatchController.listDispatchesbyRequsition("1");
		assertNotNull(dispatches);
		assertEquals(3, dispatches.size());

		verify(dispatchService, times(1)).getListbyRequisition("1");
	}

	@Test
	public void testListDispatchesbyRegion() {
		stubServiceToReturnDispatches(6);
		Collection<Dispatch> dispatches = dispatchController.listDispatchesbyRegion(2);
		assertNotNull(dispatches);
		assertEquals(6, dispatches.size());

		verify(dispatchService, times(1)).getListbyRegion(2);
	}

}
