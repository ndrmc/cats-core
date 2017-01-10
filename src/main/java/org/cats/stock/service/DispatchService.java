package org.cats.stock.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.cats.CatsApplication;
import org.cats.stock.domain.Dispatch;
import org.cats.stock.repository.DispatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class DispatchService {

	@Autowired
	private DispatchRepository dispatchRepository;
	
	
	public DispatchRepository getRepository() {
		return dispatchRepository;
	}

	public void setRepository(DispatchRepository repository) {
		this.dispatchRepository = repository;
	}

	@Transactional
	public Dispatch findById(@NotNull Long dispatchId) {
		return dispatchRepository.findOne(dispatchId);
	}	

	@Transactional(readOnly = true)
	public Dispatch findByGin(String gin) {
		return dispatchRepository.findByGin(gin);
	}


	@Transactional
	public Dispatch save(@NotNull @Valid final Dispatch dispatch) {
		return dispatchRepository.save(dispatch);
	}

	@Transactional
	public Dispatch update(@NotNull @Valid final Dispatch dispatch) throws NotFoundException {
		Dispatch dispatched=dispatchRepository.findOne(dispatch.getId());
		if(dispatched==null)
			throw new NotFoundException("Dispatch with id "+dispatch.getId()+"not found");
		return dispatchRepository.save(dispatch);
	}

	@Transactional
	public void delete(@NotNull Long dispatchId) {
		Dispatch dispatched=dispatchRepository.findOne(dispatchId);
		if(dispatched!=null){
			dispatchRepository.delete(dispatchId);
		}
	}

	@Transactional(readOnly = true)
	public List<Dispatch> getList() {
		return dispatchRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Dispatch> getListbyOperation(Integer operationId) {
		return dispatchRepository.findByOperationId(operationId);
	}

	@Transactional(readOnly = true)
	public List<Dispatch> getListbyRequisition(String requisistionNo) {
		return dispatchRepository.findByRequisitionNo(requisistionNo);
	}
	
	@Transactional(readOnly = true)
	public List<Dispatch> getListbyRegion(Integer regionId) {
		
		HttpGet httpGet = new HttpGet(CatsApplication.getCatsV1URI());
		CloseableHttpClient httpclient = HttpClients.createDefault();

		
		CatsResponseHandler catsHandler = new CatsResponseHandler(List.class);
		
		 
		List<Integer> fdpIds = (List<Integer>) catsHandler.getResponse(httpclient, httpGet);
		return dispatchRepository.findByFdpIdIn(fdpIds);
		
		
		
	} 

}