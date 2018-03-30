package org.cats.operation.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cats.operation.domain.Operation;
import org.cats.operation.repository.OperationRepository;
import org.cats.util.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;
import org.springframework.web.client.RestTemplate;

@Service
public class OperationService {
	
	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private URLBuilder urlBuilder;

	@Transactional
	public Operation findById(@NotNull Long dispatchId) {
		return operationRepository.findOne(dispatchId);
	}	

	@Transactional(readOnly = true)
	public Operation findByName(String name) {
		return operationRepository.findByName(name);
	}


	@Transactional
	public Operation save(@NotNull @Valid final Operation operation) {
		return operationRepository.save(operation);
	}

	@Transactional
	public Operation update(@NotNull @Valid final Operation operation) throws NotFoundException {
		Operation dispatched=operationRepository.findOne(operation.getId());
		if(dispatched==null)
			throw new NotFoundException("Operation with id "+operation.getId()+"not found");
		return operationRepository.save(operation);
	}

	@Transactional
	public void delete(@NotNull Long dispatchId) {
		Operation dispatched=operationRepository.findOne(dispatchId);
		if(dispatched!=null){
			operationRepository.delete(dispatchId);
		}
	}

	@Transactional(readOnly = true)
	public List<Operation> getList() {
		return operationRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Operation> getListbyYear(String year) {
		return operationRepository.findByYear(year);
	}

	public List<Operation> fetchRemoteOperations(){
        ResponseEntity<List<Operation>> operationsResult  =
                restTemplate.exchange(urlBuilder.getOperations(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Operation>>() {
                        });
        List<Operation> operations = operationsResult.getBody();
        return operations;
	}

}
