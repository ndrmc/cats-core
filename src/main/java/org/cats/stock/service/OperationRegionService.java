package org.cats.stock.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cats.stock.domain.Operation;
import org.cats.stock.domain.OperationRegion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.cats.stock.repository.OperationRegionRepository;
import org.cats.stock.repository.OperationRepository;

@Service
public class OperationRegionService {
	
	@Autowired
	private OperationRegionRepository operationRegionRepository;
	
	@Autowired
	private OperationRepository operationRepository;

	@Transactional
	public Operation save(@NotNull @Valid OperationRegion operationRegion) {
		Operation operation = operationRepository.findOne(operationRegion.getOperationId());
		operationRegion.setOperation(operation);
		operation.getOperationRegions().add(operationRegion);
		return operationRepository.save(operation);

	}
	
	@Transactional
	public void delete(@NotNull Long id) {
		OperationRegion region=operationRegionRepository.findOne(id);
		if(region!=null){
			operationRegionRepository.delete(id);
		}
	}

	@Transactional(readOnly = true)
	public List<OperationRegion> getList() {
		return operationRegionRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Operation> getListbyOperationId(Long operationId) {
		return operationRegionRepository.findByOperationId(operationId);
	}

}
