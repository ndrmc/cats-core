package org.cats.stock.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.cats.stock.domain.Dispatch;
import org.cats.stock.repository.DispatchRepository;
import org.cats.stock.util.DispatchTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.http.client.MockClientHttpResponse;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DispatchServiceTest {

	@Mock
	private DispatchRepository dispatchRepository;

	private DispatchService dispatchService;
	
	@Mock 
	private CloseableHttpClient httpClinet;

	@Mock
	CloseableHttpResponse mockHttpResponse;
	
	 
	private static String fdp_ids_response= "{[1,2,3]}";

	@Before
	public void setUp() throws Exception {
		dispatchService = new DispatchService();	
		dispatchService.setRepository(dispatchRepository);
	}

	private Dispatch stubRepositoryToReturnExistingDispatch() {
		final Dispatch dispatch = DispatchTestUtil.createDispatch();
		
		when(dispatchRepository.findByGin(any(String.class))).thenReturn(dispatch);
		when(dispatchRepository.findOne(any(Long.class))).thenReturn(dispatch);
		return dispatch;
	}	

	private Dispatch stubRepositoryToReturnDispatchOnSave() {
        final Dispatch dispatch = DispatchTestUtil.createDispatch();
        when(dispatchRepository.save(any(Dispatch.class))).thenReturn(dispatch);
		return dispatch;
    }
	
	private void stubHttpClientResponse() {
		
		when(dispatchRepository.findByFdpIdIn(any())).thenReturn(DispatchTestUtil.createDispatchList(3));
		
		final HttpEntity mockHttpEntity = mock(HttpEntity.class);
		when(mockHttpResponse.getEntity()).thenReturn(mockHttpEntity);
		
		final ByteArrayInputStream responseStream = new ByteArrayInputStream(new String(fdp_ids_response).getBytes());
	    try {
			when(mockHttpEntity.getContent()).thenReturn(responseStream);
			when(httpClinet.execute(any())).thenReturn(mockHttpResponse);
		} catch (UnsupportedOperationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

	@Test
	public void testFindById() {
		Dispatch savedDispatch = stubRepositoryToReturnExistingDispatch();
		Dispatch returnedDispatch=dispatchService.findById(Long.parseLong("1"));
		
		verify(dispatchRepository,times(1)).findOne(Long.parseLong("1"));
		assertEquals(savedDispatch,returnedDispatch);
		
	}

	@Test
	public void testFindByGin() {
		Dispatch savedDispatch = stubRepositoryToReturnExistingDispatch();
		Dispatch returnedDispatch = dispatchService.findByGin(savedDispatch.getGin());
		
		verify(dispatchRepository,times(1)).findByGin(savedDispatch.getGin());
		assertEquals(savedDispatch,returnedDispatch);
	}

	@Test
	public void testSave() {
		Dispatch dispatchToSave = stubRepositoryToReturnDispatchOnSave();
		Dispatch dispatch = DispatchTestUtil.createDispatch();
		Dispatch savedDispatch= dispatchService.save(dispatch);
		
		verify(dispatchRepository, times(1)).save(dispatch);
		assertEquals(dispatchToSave,savedDispatch);
		
	}

	@Test
	public void testUpdate() {
		stubRepositoryToReturnExistingDispatch();
		Dispatch dispatch=stubRepositoryToReturnDispatchOnSave();
		
		Dispatch updatedDispatch = null;
		try {

			updatedDispatch=dispatchService.update(dispatch);
			verify(dispatchRepository, times(1)).save(dispatch);

		} catch (NotFoundException e) {
			fail("shouldn't throw not found exception");
		}
		assertEquals(dispatch, updatedDispatch);
	}

	@Test
	public void testDelete() {
		Dispatch dispatch= stubRepositoryToReturnExistingDispatch();
		
		dispatchService.delete(Long.parseLong("1"));
		verify(dispatchRepository, times(1)).delete(Long.parseLong("1"));
	}

	@Test
	public void testGetList() {
		stubRepositoryToReturnDispatches(10);
		Collection<Dispatch> dispatches = dispatchService.getList();
		assertNotNull(dispatches);
		assertEquals(10, dispatches.size());

		verify(dispatchRepository, times(1)).findAll();
	}

	private void stubRepositoryToReturnDispatches(int size) {
		when(dispatchRepository.findAll()).thenReturn(DispatchTestUtil.createDispatchList(size));
		when(dispatchRepository.findByOperationId(any(Integer.class))).thenReturn(DispatchTestUtil.createDispatchList(size));
		when(dispatchRepository.findByRequisitionNo(any(String.class))).thenReturn(DispatchTestUtil.createDispatchList(size));
		
		
	}

	@Test
	public void testGetListbyOperation() {
		stubRepositoryToReturnDispatches(5);
		Collection<Dispatch> dispatches = dispatchService.getListbyOperation(1);
		assertNotNull(dispatches);
		assertEquals(5, dispatches.size());

		verify(dispatchRepository, times(1)).findByOperationId(1);
	}

	@Test
	public void testGetListbyRequisition() {
		stubRepositoryToReturnDispatches(10);
		Collection<Dispatch> dispatches = dispatchService.getListbyRequisition("1");
		assertNotNull(dispatches);
		assertEquals(10, dispatches.size());

		verify(dispatchRepository, times(1)).findByRequisitionNo("1");
	}

	@Test
	public void testGetListbyRegion() {
		stubHttpClientResponse();
		List<Dispatch> dispatches = dispatchService.getListbyRegion(Integer.parseInt("1"));
		
		assertNotNull(dispatches);
		assertEquals(3, dispatches.size());
		verify(dispatchRepository, times(1)).findByFdpIdIn(any());
		
		try {
			assertEquals(EntityUtils.toString(mockHttpResponse.getEntity(), "UTF-8"),fdp_ids_response);
		} catch (ParseException | IOException e) {
			fail();
			e.printStackTrace();
		}
		
	}

}