package org.cats.stock.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cats.stock.domain.Dispatch;
import org.cats.stock.repository.DispatchRepository;
import org.cats.util.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javassist.NotFoundException;

@Service
public class DispatchService {


	private DispatchRepository dispatchRepository; 

	@Autowired
	private URLBuilder urlBuilder;

	@Autowired
	RestTemplate restTemplate;


	@Autowired
	public DispatchService(DispatchRepository dispatchRepository) {
		this.dispatchRepository = dispatchRepository;
	}

	public URLBuilder getUrlBuilder() {
		return urlBuilder;
	}

	public void setUrlBuilder(URLBuilder urlBuilder) {
		this.urlBuilder = urlBuilder;
	}

	public DispatchRepository getRepository() {
		return dispatchRepository;
	}

	public void setRepository(DispatchRepository repository) {
		this.dispatchRepository = repository;
	}	

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
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

		List<Integer> fdpIds = restTemplate.getForObject(urlBuilder.getFdpIdsByRegion(regionId),new ArrayList<Integer>().getClass());
		return dispatchRepository.findByFdpIdIn(fdpIds);	

	} 

}