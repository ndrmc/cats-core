package org.cats.stock.repository;

import java.util.List;

import org.cats.stock.domain.Operation;
import org.cats.stock.domain.OperationRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRegionRepository extends JpaRepository<OperationRegion, Long> {

	List<Operation> findByOperationId(Long operationId);

}
