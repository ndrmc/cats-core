package org.cats.stock.service;

import java.util.List;
import java.util.function.Predicate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cats.stock.domain.Operation;
import org.cats.stock.domain.OperationRegion;
import org.cats.stock.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class OperationService {

	@Autowired
	OperationRepository operationRepository;

	@Autowired
	private OperationRegionRepository operationRegionRepository;



	@Transactional
	public Operation findById(@NotNull Long dispatchId) {
		return operationRepository.findOne(dispatchId);
	}

	@Transactional(readOnly = true)
	public Operation findByName(String name) {
		return operationRepository.findByName(name);
	}


	@Transactional
	public Operation save(@NotNull @Valid Operation operation) {

		List<OperationRegion> regions= operation.getOperationRegions();


		for (OperationRegion opRegion : regions) {
			opRegion.setOperation(operation);

		}
		operation.setOperationRegions(regions);

		return operationRepository.save(operation);


	}

	@Transactional
	public Operation update(@NotNull @Valid final Operation operation) throws NotFoundException {
		Operation existingOperation=operationRepository.findOne(operation.getId());
		if(existingOperation==null)
			throw new NotFoundException("Operation with id "+operation.getId()+"not found");

		List<OperationRegion> regions= operation.getOperationRegions();

		for (OperationRegion opRegion : regions) {
			opRegion.setOperation(operation);

		}
		operation.setOperationRegions(regions);

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


}
