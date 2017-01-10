package org.cats.stock.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.cats.stock.domain.DispatchItem;
import org.cats.stock.service.DispatchItemService;
import org.cats.stock.util.DispatchTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DispatchItemControllerTest {

	@Mock
	DispatchItemService dispatchItemService;

	private DispatchItemController dispatchItemController;

	@Before
	public void setUp() throws Exception {
		dispatchItemController=new DispatchItemController();
		dispatchItemController.setDispatchItemService(dispatchItemService);
	}

	private void stubServiceToReturnDispatchItems(int size) {
		when(dispatchItemService.getList()).thenReturn(DispatchTestUtil.createDispatchItemList(size));
		when(dispatchItemService.getListbyDispatch(any(Integer.class))).thenReturn(DispatchTestUtil.createDispatchItemList(size));
		when(dispatchItemService.getListbyProject(any(Integer.class))).thenReturn(DispatchTestUtil.createDispatchItemList(size));
	}

	private DispatchItem stubServiceToReturnStoredDispatchItem() {
		final DispatchItem dispatchItem = DispatchTestUtil.createDispatchItem();
		when(dispatchItemService.save(any(DispatchItem.class))).thenReturn(dispatchItem);
		try {
			when(dispatchItemService.update(any(DispatchItem.class))).thenReturn(dispatchItem);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(dispatchItemService.findById(any(Long.class))).thenReturn(dispatchItem);
		return dispatchItem;
	}

	

	@Test
	public void testListDispatchItems() {
		stubServiceToReturnDispatchItems(3);
		List<DispatchItem> items= dispatchItemController.listDispatchItems();

		assertEquals(3,items.size());
		verify(dispatchItemService,times(1)).getList();


	}

	@Test
	public void testGetDispatchItemById() {
		DispatchItem savedItem=stubServiceToReturnStoredDispatchItem();
		DispatchItem returnedItem= dispatchItemController.getDispatchItemById(Long.parseLong("1"));

		assertEquals(savedItem, returnedItem);
		verify(dispatchItemService,times(1)).findById(Long.parseLong("1"));
	}

	@Test
	public void testListDispatchItemsbyDispatch() {
		stubServiceToReturnDispatchItems(3);

		List<DispatchItem> items= dispatchItemController.listDispatchItemsbyDispatch(Integer.parseInt("1"));

		assertEquals(3, items.size());
		verify(dispatchItemService,times(1)).getListbyDispatch(Integer.parseInt("1"));
	}

	@Test
	public void testGetDispatchItemsbyProject() {
		stubServiceToReturnDispatchItems(5);

		List<DispatchItem> items= dispatchItemController.getDispatchItemsbyProject(Integer.parseInt("1"));

		assertEquals(5, items.size());
		verify(dispatchItemService,times(1)).getListbyProject(Integer.parseInt("1"));
	}

	@Test
	public void testCreateDispatchItem() {
		DispatchItem itemToSave = stubServiceToReturnStoredDispatchItem();
		DispatchItem dispatchItem = DispatchTestUtil.createDispatchItem();
		DispatchItem savedItem= dispatchItemController.createDispatchItem(dispatchItem);

		assertEquals(itemToSave, savedItem);
		verify(dispatchItemService, times(1)).save(dispatchItem);
	}

	@Test
	public void testUpdateDispatchItem() {
		final DispatchItem savedDispatchItem = stubServiceToReturnStoredDispatchItem();

		DispatchItem updatedDispatchItem = null;
		try {

			updatedDispatchItem=dispatchItemController.updateDispatchItem(savedDispatchItem);
			verify(dispatchItemService, times(1)).update(savedDispatchItem);

		} catch (NotFoundException e) {
			fail("shouldn't throw not found exception");
		}
		assertEquals(savedDispatchItem, updatedDispatchItem);
	}

	@Test
	public void testDeleteDispatchItem() {
		dispatchItemController.deleteDispatchItem(Long.parseLong("1"));
		verify(dispatchItemService, times(1)).delete(Long.parseLong("1"));
	}

}
