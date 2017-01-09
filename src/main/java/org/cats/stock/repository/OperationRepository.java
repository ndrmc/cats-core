package org.cats.stock.repository;

import java.util.List;

import org.cats.stock.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	Operation findByName(String name);
	List<Operation> findByYear(String year);	
}
