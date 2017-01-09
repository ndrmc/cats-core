package org.cats.stock.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.cats.stock.domain.DispatchItem;
import org.cats.stock.repository.DispatchItemRepository;
import org.cats.stock.util.DispatchTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DispatchItemServiceTest {
	
	@Mock
	DispatchItemRepository repository;
	
	DispatchItemService service;
	

	@Before
	public void setUp() throws Exception {
		service = new DispatchItemService();
		service.setRepository(repository);
	}

	private DispatchItem stubRepoToReturnStoredDispatchItem() {
		final DispatchItem dispatchItem = DispatchTestUtil.createDispatchItem();
		when(repository.save(any(DispatchItem.class))).thenReturn(dispatchItem);
		
		when(repository.findOne(any(Long.class))).thenReturn(dispatchItem);
		return dispatchItem;
	}
	
	private void stubRepoToReturnDispatchItems(int size) {
		List<DispatchItem> dispatchItems = DispatchTestUtil.createDispatchItemList(size);
		when(repository.findAll()).thenReturn(dispatchItems);
		when(repository.findByDispatchId(any(Integer.class))).thenReturn(dispatchItems);
		when(repository.findByProjectId(any(Integer.class))).thenReturn(dispatchItems);
		
	}
	
	@Test
	public void testFindById() {
		DispatchItem savedItem=stubRepoToReturnStoredDispatchItem();
		DispatchItem returnedItem= service.findById(Long.parseLong("1"));

		assertEquals(savedItem, returnedItem);
		verify(repository,times(1)).findOne(Long.parseLong("1"));
	}

	

	@Test
	public void testSave() {
		stubRepoToReturnDispatchItems(5);

		List<DispatchItem> items= service.getListbyProject(Integer.parseInt("1"));

		assertEquals(5, items.size());
		verify(repository,times(1)).findByProjectId(Integer.parseInt("1"));
	}

	

	@Test
	public void testUpdate() {
		final DispatchItem savedDispatchItem = stubRepoToReturnStoredDispatchItem();

		DispatchItem updatedDispatchItem = null;
		try {

			updatedDispatchItem=service.update(savedDispatchItem);
			verify(repository, times(1)).save(savedDispatchItem);

		} catch (NotFoundException e) {
			fail("shouldn't throw not found exception");
		}
		assertEquals(savedDispatchItem, updatedDispatchItem);
	}

	@Test
	public void testDelete() {
		stubRepoToReturnStoredDispatchItem();
		service.delete(Long.parseLong("1"));
		
		verify(repository,times(1)).delete(Long.parseLong("1"));
	}

	@Test
	public void testGetList() {
		stubRepoToReturnDispatchItems(3);

		List<DispatchItem> items= service.getList();

		assertEquals(3, items.size());
		verify(repository,times(1)).findAll();
	}

	@Test
	public void testGetListbyDispatch() {
		stubRepoToReturnDispatchItems(5);

		List<DispatchItem> items= service.getListbyDispatch(Integer.parseInt("1"));

		assertEquals(5, items.size());
		verify(repository,times(1)).findByDispatchId(Integer.parseInt("1"));
	}

	@Test
	public void testGetListbyProject() {
		stubRepoToReturnDispatchItems(8);

		List<DispatchItem> items= service.getListbyProject(Integer.parseInt("1"));

		assertEquals(8, items.size());
		verify(repository,times(1)).findByProjectId(Integer.parseInt("1"));
	}

}
