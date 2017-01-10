package org.cats.stock.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cats.stock.domain.DispatchItem;
import org.cats.stock.repository.DispatchItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class DispatchItemService {

	@Autowired
	private DispatchItemRepository repository;
	
	
	public DispatchItemRepository getRepository() {
		return repository;
	}

	public void setRepository(DispatchItemRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DispatchItem findById(@NotNull Long dispatchItemId) {
		return repository.findOne(dispatchItemId);
	}	

	@Transactional
	public DispatchItem save(@NotNull @Valid final DispatchItem dispatchItem) {
		return repository.save(dispatchItem);
	}

	@Transactional
	public DispatchItem update(@NotNull @Valid final DispatchItem dispatchItem) throws NotFoundException {
		DispatchItem savedItem=repository.findOne(dispatchItem.getId());
		if(savedItem==null)
			throw new NotFoundException("Dispatch item with id "+dispatchItem.getId()+"not found");
		return repository.save(dispatchItem);
	}

	@Transactional
	public void delete(@NotNull Long dispatchItemId) {
		DispatchItem dispatchItem=repository.findOne(dispatchItemId);
		if(dispatchItem!=null){
			repository.delete(dispatchItemId);
		}
	}

	@Transactional(readOnly = true)
	public List<DispatchItem> getList() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<DispatchItem> getListbyDispatch(Integer dispatchId) {
		return repository.findByDispatchId(dispatchId);
	}

	@Transactional(readOnly = true)
	public List<DispatchItem> getListbyProject(Integer projectId) {
		return repository.findByProjectId(projectId);
	}	

}
