package org.cats.stock.repository;

import java.util.List;

import org.cats.stock.domain.DispatchItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchItemRepository extends JpaRepository<DispatchItem, Long> {
	List<DispatchItem> findByDispatchId(Integer dispatchId);
	List<DispatchItem> findByProjectId(Integer projectId);
}
